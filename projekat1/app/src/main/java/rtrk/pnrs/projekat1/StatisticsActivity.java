package rtrk.pnrs.projekat1;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.icu.lang.UProperty.INT_START;

public class StatisticsActivity extends AppCompatActivity implements View.OnClickListener {

    private String date_time;
    private String location;
    private String dan;
    private TextView Location;
    private DatabaseHelper weatherhelper;
    private TextView tPon,tUto, tSre, tCet,tPet,tSub,tNed ,tMin,tMax,tIzn,tIsp;
    private ImageButton ibHlad,ibTopl;
    private Cursor cursor;
    Data[] d;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics2);

        Location = findViewById(R.id.LocationStatistics);
        Bundle bundleLocation = getIntent().getExtras();
        Location.setText(bundleLocation.get("edit1").toString());
        location = bundleLocation.get("edit1").toString();
        Log.d("LOCATION", location);

        Bundle bundle1 = getIntent().getExtras();
        dan = bundle1.get("edit2").toString();

        Bundle bundle2 = getIntent().getExtras();
        date_time = bundle2.get("edit3").toString();

        weatherhelper = new DatabaseHelper(this);

        tPon = (TextView) findViewById(R.id.pon);
        tUto = (TextView) findViewById(R.id.uto);
        tSre = (TextView) findViewById(R.id.sre);
        tCet = (TextView) findViewById(R.id.cet);
        tPet = (TextView) findViewById(R.id.pet);
        tSub = (TextView) findViewById(R.id.sub);
        tNed = (TextView) findViewById(R.id.ned);

        tMin=(TextView)findViewById(R.id.add_min_temp);
        tMax=(TextView)findViewById(R.id.add_max_temp);
        tIzn=(TextView)findViewById(R.id.listMax);
        tIsp=(TextView)findViewById(R.id.listMin);

        tIzn.setVisibility(View.INVISIBLE);
        tIsp.setVisibility(View.INVISIBLE);
        ibHlad=(ImageButton)findViewById(R.id.snow_stat);
        ibTopl=(ImageButton)findViewById(R.id.sun_stat);

        ibHlad.setOnClickListener(this);
        ibTopl.setOnClickListener(this);


        Calendar time=Calendar.getInstance();
        SimpleDateFormat data=new SimpleDateFormat("dd.MM.yyyy.");
        date_time = data.format(time.getTime());
        Log.d("DATEEEEEEEEEEEEEE", date_time);




        d = weatherhelper.read_all_data_loc(location);
        int i,i1;

        String d_w;
        String pom ="";

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Ponedeljak")) {
                pom = "    " + d[i].getDay() + "                    " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tPon.setText(pom);
                break;
            } else {
                continue;
            }
        }


        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Utorak")) {
                pom = "    " + d[i].getDay() + "                    " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tUto.setText(pom);
                break;
            } else {
                continue;
            }
        }

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Sreda")) {
                pom = "    " + d[i].getDay() + "                 " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                SpannableStringBuilder str = new SpannableStringBuilder(pom);
                str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,  pom.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tSre.setText(str);
                break;
            } else {
                continue;
            }
        }

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Cetvrtak")) {
                pom = "    " + d[i].getDay() + "                    " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tCet.setText(pom);
                break;
            } else {
                continue;
            }
        }

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Petak")) {
                pom = "    " + d[i].getDay() + "                    " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tPet.setText(pom);
                break;
            } else {
                continue;
            }
        }

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Subota")) {
                pom = "    " + d[i].getDay() + "                   " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tSub.setText(pom);
                break;
            } else {
                continue;
            }
        }

        for(i = 0; i < d.length; i++) {
            d_w = d[i].getDay();
            if (d_w.equals("Nedelja")) {
                pom = "    " + d[i].getDay() + "                  " + d[i].getTemperature() + "      " + d[i].getPressure() + "      " + d[i].getHumidity() + "\n\n";
                tNed.setText(pom);
                break;
            } else {
                continue;
            }
        }





        //min(cursor);

        min();
        max();


    }



    public int getDayoFWeek(String date){

        int dayOfWeek;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date d = format.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(d); // yourdate is an object of type Date

            dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            Log.d("dan",String.valueOf(dayOfWeek));
            System.out.println(date);
            return dayOfWeek;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String dayOfWeek(int d){
        if(d==1){
            return getString(R.string.ned);
        }else if(d==2){
            return getString(R.string.pon);
        }else if(d==3){
            return getString(R.string.uto);
        }else if(d==4){
            return getString(R.string.sre);
        }else if(d==5){
            return getString(R.string.cet);
        }else if(d==6){
            return getString(R.string.pet);
        }else
            return getString(R.string.sub);
    }
   /* public void put(String day,Cursor cursor){
        if(day.equals(getString(R.string.pon))){
            tPon.setText(getString(R.string.pon)+"                       "+cursor.getDouble(3)+"            "+cursor.getDouble(4)+"             "+cursor.getDouble(5));
        }else if(day.equals(getString(R.string.uto))){
            tUto.setText(getString(R.string.uto)+"                       "+cursor.getDouble(3)+"          "+cursor.getDouble(4)+"             "+cursor.getDouble(5));
        }else if(day.equals(getString(R.string.sre))){
            tSre.setText(getString(R.string.sre)+"                       "+cursor.getDouble(3)+"         "+cursor.getDouble(4)+"            "+cursor.getDouble(5));
        }else if(day.equals(getString(R.string.cet))){
            tCet.setText(getString(R.string.cet)+"                       "+cursor.getDouble(3)+"           "+cursor.getDouble(4)+"              "+cursor.getDouble(5));
        }else if(day.equals(getString(R.string.pet))){
            tPet.setText(getString(R.string.pet)+"                       "+cursor.getDouble(3)+"           "+cursor.getDouble(4)+"              "+cursor.getDouble(5));
        }else if(day.equals(getString(R.string.sub))){
            tSub.setText(getString(R.string.sub)+"                       "+cursor.getDouble(3)+"          "+cursor.getDouble(4)+"             "+cursor.getDouble(5));
        }else{
            tNed.setText(getString(R.string.ned)+"                       "+cursor.getDouble(3)+"            "+cursor.getDouble(4)+"              "+cursor.getDouble(5));
        }
    }*/

    public void min(){
        double min=1000;
        Data[] d = weatherhelper.read_all_data_loc(location);
        int i;
        for(i = 0; i < d.length; i++) {

            if(Integer.parseInt(d[i].getTemperature()) < min){
                min = Integer.parseInt(d[i].getTemperature());
            }
        }

        String pom ="";
        for(i = 0; i < d.length; i++) {
            if(Integer.parseInt(d[i].getTemperature()) == min){
                pom = pom + (d[i].getDay() + " " + d[i].getTemperature()) + "\n";
            }
        }

        tMin.setText(pom);

        /*for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if(cursor.getDouble(3)<min){
                min=cursor.getDouble(3);
            }
        }
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if(cursor.getDouble(3)==min){
                String date=cursor.getString(1);;
                int value=getDayoFWeek(date);
                String day=dayOfWeek(value);
                String temp=String.valueOf(cursor.getDouble(3));
                tMin.append(day +" "+ temp+"\n");
            }
        }*/

    }

    public void max(){
        double max=0;
        Data[] d = weatherhelper.read_all_data_loc(location);
        int i;
        for(i = 0; i < d.length; i++) {

            if(Integer.parseInt(d[i].getTemperature()) > max){
                max = Integer.parseInt(d[i].getTemperature());
            }
        }

        String pom ="";
        for(i = 0; i < d.length; i++) {
            if(Integer.parseInt(d[i].getTemperature()) == max){
                pom = pom + (d[i].getDay() + " " + d[i].getTemperature()) + "\n";
            }
        }

        tMax.setText(pom);
    }


    @Override
    public void onClick(View v) {
        Data[] d = weatherhelper.read_all_data_loc(location);
        int i;
        switch (v.getId()) {
            case R.id.snow_stat:
                tIzn.setText("");
                tIzn.setVisibility(View.INVISIBLE);
                tIsp.setVisibility(View.VISIBLE);


                String pom = "";
                for(i = 0; i < d.length; i++) {
                    if(Integer.parseInt(d[i].getTemperature()) < 10){
                       pom = pom + d[i].getDay() + " " + d[i].getTemperature() + "\n";
                    }
                }
                Log.d("MIIIIN", pom);
                tIsp.setText(pom);
                break;
            case R.id.sun_stat:
                tIsp.setText("");
                tIsp.setVisibility(View.INVISIBLE);
                tIzn.setVisibility(View.VISIBLE);

                String pom1 = "";
                for(i = 0; i < d.length; i++) {
                    if(Integer.parseInt(d[i].getTemperature()) > 10){
                        pom1 = pom1 + d[i].getDay() + " " + d[i].getTemperature() + "\n";
                    }
                }
                Log.d("MAAAX", pom1);
                tIzn.setText(pom1);

            break;
        }
    }



}
