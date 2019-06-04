package rtrk.pnrs.projekat1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView city;
    private TextView date;
    Calendar time;
    String day;
    Spinner drop_down_list;
    private LinearLayout tempLayout, sunsetLayout, windLayout;
    private Button tempButton, sunsetButton, windButton;
    private TextView tmp, press, hum, sunRise, sunSet, windSpeed, windDir;

    public static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    public static String KEY = "&APPID=21ea253e17b57b6663d7867a95b1452a&units=metric";
    public String GET_INFO;
    private HTTPhelper httpHelper;
    ArrayAdapter<String> adapter;

    private String temper, pressure, humidity, sun1, sun2, wind1, wind2;
    String location;
    private String dan_n;
    private DatabaseHelper weather_helper;
    String data_time;
    private ImageButton refresh;
    private TextView update;
    private Button statistics;

   /* private Button stop_service;
    private ServiceHelper mService;
    boolean mBound = false;*/

    Data data;

   /* @Override
    protected void onStart() {
        super.onStart();

        Log.d("START SERVIS", "START SERVIS");
        Intent intent = new Intent(this, ServiceHelper.class);
        intent.putExtra("location", location);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mBound == true)
            unbindService(connection);
        mBound = false;
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        weather_helper = new DatabaseHelper(this);

        Data data1 = new Data("Ponedeljak", "17.05.2019.", "Novi Sad", "22", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data1);
        Data data2 = new Data("Utorak", "18.05.2019.", "Novi Sad", "21", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data2);
        Data data3 = new Data("Sreda", "19.05.2019.", "Novi Sad", "24", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data3);
        Data data4 = new Data("Cetvrtak", "20.05.2019.", "Novi Sad", "25", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data4);
        Data data5 = new Data("Petak", "21.05.2019.", "Novi Sad", "18", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data5);
        Data data6 = new Data("Subota", "22.05.2019.", "Novi Sad", "23", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data6);
        Data data7 = new Data("Nedelja", "23.05.2019.", "Novi Sad", "20", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data7);


        Data data11 = new Data("Ponedeljak", "17.05.2019.", "Moscow", "22", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data11);
        Data data22 = new Data("Utorak", "18.05.2019.", "Moscow", "21", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data22);
        Data data33 = new Data("Sreda", "19.05.2019.", "Moscow", "3", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data33);
        Data data44 = new Data("Cetvrtak", "20.05.2019.", "Moscow", "25", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data44);
        Data data55 = new Data("Petak", "21.05.2019.", "Moscow", "-68", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data55);
        Data data66 = new Data("Subota", "22.05.2019.", "Moscow", "9", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data66);
        Data data77 = new Data("Nedelja", "23.05.2019.", "Moscow", "20", "1024", "56", "12:13", "13:12", "65", "Sever");
        weather_helper.insert(data77);

        tempButton = findViewById(R.id.temperatureButton);
        sunsetButton = findViewById(R.id.sunButton);
        windButton = findViewById(R.id.windButton);

        tempButton.setOnClickListener(this);
        sunsetButton.setOnClickListener(this);
        windButton.setOnClickListener(this);

        tempLayout = findViewById(R.id.showTemperature);
        sunsetLayout = findViewById(R.id.showSunset);
        windLayout = findViewById(R.id.showWind);

        city = findViewById(R.id.textLocation);
        Bundle bundle = getIntent().getExtras();
        city.setText("Lokacija: " + bundle.get("city").toString());
        location = bundle.get("city").toString();


        date = findViewById(R.id.textDate);
        time = Calendar.getInstance();
        int dan;

        dan = time.get(Calendar.DAY_OF_WEEK);
        switch (dan) {
            case Calendar.MONDAY:
                day = "Ponedeljak";
                break;
            case Calendar.TUESDAY:
                day = "Utorak";
                break;
            case Calendar.WEDNESDAY:
                day = "Sreda";
                break;
            case Calendar.THURSDAY:
                day = "Cetvrtak";
                break;
            case Calendar.FRIDAY:
                day = "Petak";
                break;
            case Calendar.SATURDAY:
                day = "Subota";
                break;
            case Calendar.SUNDAY:
                day = "Nedelja";
                break;
            default:
                day = "";
        }



        drop_down_list = findViewById(R.id.drop_down);
        String[] drop_down_list_str = new String[]{"°C", "F"};
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, drop_down_list_str);
        drop_down_list.setAdapter(adapter);

        tempLayout.setVisibility(View.INVISIBLE);
        sunsetLayout.setVisibility(View.INVISIBLE);
        windLayout.setVisibility(View.INVISIBLE);

        tempButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
        sunsetButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
        windButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));

        refresh = findViewById(R.id.image_btn);
        update = findViewById(R.id.updated);
        refresh.setOnClickListener(this);

        statistics = findViewById(R.id.statButton);
        statistics.setOnClickListener(this);


        httpHelper = new HTTPhelper();
        GET_INFO = BASE_URL + bundle.get("city").toString() + KEY;
        Log.d("URL", GET_INFO);
        tmp = findViewById(R.id.temperaturaText);
        press = findViewById(R.id.pritisakText);
        hum = findViewById(R.id.vlaznostVazduhaText);
        sunRise = findViewById(R.id.izlazak);
        sunSet = findViewById(R.id.zalazak);
        windSpeed = findViewById(R.id.brzina);
        windDir = findViewById(R.id.pravac);

        SimpleDateFormat data_1 = new SimpleDateFormat("dd.MM.yyyy.");
        date.setText("Datum: " + data_1.format(time.getTime()));
        data_time = data_1.format(time.getTime());

        /*stop_service = findViewById(R.id.stop_service);
        stop_service.setOnClickListener(this);
*/




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonobject = httpHelper.getJSONObjectFromURL(GET_INFO);
                    JSONObject mainobject = jsonobject.getJSONObject("main");

                    final int temp = mainobject.getInt("temp");
                    final String pressure = mainobject.get("pressure").toString();
                    final String humidity = mainobject.get("humidity").toString();

                    JSONObject jsonobject1 = httpHelper.getJSONObjectFromURL(GET_INFO);
                    JSONObject sysobject = jsonobject1.getJSONObject("sys");

                    long sun = Long.valueOf(sysobject.get("sunrise").toString()) * 1000;
                    Date date1 = new Date(sun);

                    final String sunrise = new SimpleDateFormat("hh:mma", Locale.ENGLISH).format(date1);

                    long night = Long.valueOf(sysobject.get("sunset").toString()) * 1000;
                    Date date2 = new Date(night);
                    final String sunset = new SimpleDateFormat("hh:mma", Locale.ENGLISH).format(date2);

                    JSONObject jsonobject2 = httpHelper.getJSONObjectFromURL(GET_INFO);
                    JSONObject windobject = jsonobject2.getJSONObject("wind");

                    final String wind_speed = windobject.get("speed").toString();

                    double degree = windobject.getDouble("deg");
                    final String wind_direction = windConverter(degree);


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            int tempCelsius = temp;
                            int tempRound = (int) tempCelsius;
                            final String temperature = Integer.toString(tempRound);
                            //Temperature.setText("Temperatura: " + temperature + " °C");
                            //Pressuere.setText("Pritisak: " + pressure+ " hPA");
                            //Humidity.setText("Vlažnost vazduha: " + humidity + " %");

                            drop_down_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String selected = parent.getItemAtPosition(position).toString();
                                    if (selected.equals("°C")) {

                                        //Temperature.setText("Temperatura: " + temperature + " °C\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                    } else {
                                        /*double tempFarenhite = Double.parseDouble(temp);
                                        tempFarenhite = tempFarenhite * (9 / 5) + 32;
                                        int tempRound = (int) tempFarenhite;
                                        String temperature = Integer.toString(tempRound);*/
                                        //Temperature.setText("Temperatura: " + temperature + " °F\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                        //Sun_rise.setText("Izlazak sunca: " + sunrise);
                                        //Sun_set.setText("Zalazak sunca: " + sunset);
                                        //Wind_speed.setText("Brzina vetra: " + wind_speed + " m/s");
                                        //Wind_dir.setText("Pravac: " + wind_direction);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                    //Temperature.setText("Temperatura: " + temperature + " °C\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                }


                            });

                            time = Calendar.getInstance();
                            int dan;
                            String day;

                            dan = time.get(Calendar.DAY_OF_WEEK);
                            switch (dan) {
                                case Calendar.MONDAY:
                                    day = "Ponedeljak";
                                    break;
                                case Calendar.TUESDAY:
                                    day = "Utorak";
                                    break;
                                case Calendar.WEDNESDAY:
                                    day = "Sreda";
                                    break;
                                case Calendar.THURSDAY:
                                    day = "Cetvrtak";
                                    break;
                                case Calendar.FRIDAY:
                                    day = "Petak";
                                    break;
                                case Calendar.SATURDAY:
                                    day = "Subota";
                                    break;
                                case Calendar.SUNDAY:
                                    day = "Nedelja";
                                    break;
                                default:
                                    day = "";
                            }


                            data = new Data(day, data_time, location, Integer.toString(temp), pressure, humidity, sunset, sunrise, wind_speed, wind_direction);

                            //Data[] read_day = weather_helper.read_data_location(location);

                            weather_helper.insert_location(data);

                            Data[] data_read = weather_helper.read_all_data_loc(location);


                            int len = data_read.length - 1;

                            Log.d("ZA DATAAAAA", data_read[len].getDay());

                            update.setText("last updated \n" + data_read[len].getDate_time());


                        }
                    });


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onClick(View v) {
        final Data read = weather_helper.readData(location);

        switch (v.getId()) {
            case R.id.temperatureButton:
                tempLayout.setVisibility(View.VISIBLE);
                sunsetLayout.setVisibility(View.INVISIBLE);
                windLayout.setVisibility(View.INVISIBLE);
                tempButton.setBackgroundColor(getResources().getColor(R.color.babyPink));
                sunsetButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
                windButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));


                drop_down_list.setAdapter(adapter);

                drop_down_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        //double ret_val;
                        String selected = parent.getItemAtPosition(position).toString();
                        if (selected.equals("°C")) {
                            /*MyNDK ndk = new MyNDK();
                            int x = Integer.parseInt(read.getTemperature());
                            Log.d("Fhberghfbrvgre", String.valueOf(x));
                            int ret_val = ndk.convert(x, 0);*/
                            //Log.d("RRRRRRET VAAAAAl", String.valueOf(Double.parseDouble(read.getTemperature())));
                            //Log.d("RRRRRRET VAAAAAl", String.valueOf(ret_val));
                            tmp.setText("Temperatura: " + temper + " °C");
                            press.setText("Pritisak: " + read.getPressure() + " hPA");
                            hum.setText("Vlažnost vazduha: " + read.getHumidity() + " %");
                        } else {
                            /*MyNDK ndk = new MyNDK();
                            int ret_val = ndk.convert(Integer.parseInt(read.getTemperature()), 1);*/
                            //double tempFarenhite = Double.parseDouble(read.getTemperature());
                            //tempFarenhite = tempFarenhite * (9 / 5) + 32;
                            //int tempRound = (int) tempFarenhite;
                           // String temperature = Integer.toString(tempRound);
                            tmp.setText("Temperatura: " + temper + " F\nPritisak: " + read.getPressure() + " hPA" + "\nVlažnost vazduha: " + read.getHumidity() + " %");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        //tmp.setText("Temperatura: " + read.getTemperature() + " °C\nPritisak: " + read.getPressure() + " hPA" + "\nVlažnost vazduha: " + read.getHumidity() + " %");
                    }
                });


                break;

            case R.id.sunButton:
                tempLayout.setVisibility(View.INVISIBLE);
                sunsetLayout.setVisibility(View.VISIBLE);
                windLayout.setVisibility(View.INVISIBLE);
                tempButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
                sunsetButton.setBackgroundColor(getResources().getColor(R.color.babyPink));
                windButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));


                //update.setText("last updated " + read.getDate_time());


                sunRise.setText("Izlazak sunca: " + read.getSun_rise());
                sunSet.setText("Zalazak sunca: " + read.getSun_set());

                break;
            case R.id.windButton:
                tempLayout.setVisibility(View.INVISIBLE);
                sunsetLayout.setVisibility(View.INVISIBLE);
                windLayout.setVisibility(View.VISIBLE);
                tempButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
                sunsetButton.setBackgroundColor(getResources().getColor(R.color.babyBlue));
                windButton.setBackgroundColor(getResources().getColor(R.color.babyPink));


                windSpeed.setText("Brzina vetra: " + read.getWind_speed() + " m/s");
                windDir.setText("Pravac: " + read.getWind_direction());
                break;

            case R.id.statButton:

                Data day_send = weather_helper.readDay(day);

                Intent intent = new Intent(this, StatisticsActivity.class);
                intent.putExtra("edit1", location);
                intent.putExtra("edit2", (day_send.getDay()));
                intent.putExtra("edit3", (day_send.getDate_time()));
                this.startActivity(intent);

                break;

            case R.id.image_btn:

                refresh.setVisibility(View.INVISIBLE);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonobject = httpHelper.getJSONObjectFromURL(GET_INFO);
                            JSONObject mainobject = jsonobject.getJSONObject("main");

                            final int temp = mainobject.getInt("temp");
                            final String pressure = mainobject.get("pressure").toString();
                            final String humidity = mainobject.get("humidity").toString();

                            JSONObject jsonobject1 = httpHelper.getJSONObjectFromURL(GET_INFO);
                            JSONObject sysobject = jsonobject1.getJSONObject("sys");

                            long sun = Long.valueOf(sysobject.get("sunrise").toString()) * 1000;
                            Date date1 = new Date(sun);

                            final String sunrise = new SimpleDateFormat("hh:mma", Locale.ENGLISH).format(date1);

                            long night = Long.valueOf(sysobject.get("sunset").toString()) * 1000;
                            Date date2 = new Date(night);
                            final String sunset = new SimpleDateFormat("hh:mma", Locale.ENGLISH).format(date2);

                            JSONObject jsonobject2 = httpHelper.getJSONObjectFromURL(GET_INFO);
                            JSONObject windobject = jsonobject2.getJSONObject("wind");

                            final String wind_speed = windobject.get("speed").toString();

                            double degree = windobject.getDouble("deg");
                            final String wind_direction = windConverter(degree);


                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    int tempCelsius = temp;
                                    int tempRound = (int) tempCelsius;
                                    final String temperature = Integer.toString(tempRound);
                                    tmp.setText("Temperatura: " + temperature + " °C");
                                    press.setText("Pritisak: " + pressure + " hPA");
                                    hum.setText("Vlažnost vazduha: " + humidity + " %");
                                    sunRise.setText("Izlazak sunca: " + sunrise);
                                    sunSet.setText("Zalazak sunca: " + sunset);
                                    windSpeed.setText("Brzina vetra: " + wind_speed + " m/s");
                                    windDir.setText("Pravac: " + wind_direction);

                                    drop_down_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            String selected = parent.getItemAtPosition(position).toString();
                                            if (selected.equals("°C")) {
                                               /* MyNDK ndk = new MyNDK();
                                                int ret_val = ndk.convert(temp, 0);*/
                                                tmp.setText("Temperatura: " + temperature + " °C\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                            } else {
                                                /*double tempFarenhite = Double.parseDouble(temp);
                                                tempFarenhite = tempFarenhite * (9 / 5) + 32;
                                                int tempRound = (int) tempFarenhite;
                                                String temperature = Integer.toString(tempRound);*/
                                               /* MyNDK ndk = new MyNDK();
                                                int ret_val = ndk.convert(temp, 1);*/
                                                tmp.setText("Temperatura: " + temperature + " °F\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                                sunRise.setText("Izlazak sunca: " + sunrise);
                                                sunSet.setText("Zalazak sunca: " + sunset);
                                                windSpeed.setText("Brzina vetra: " + wind_speed + " m/s");
                                                windDir.setText("Pravac: " + wind_direction);
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {
                                            //tmp.setText("Temperatura: " + temperature + " °C\nPritisak: " + pressure + " hPA" + "\nVlažnost vazduha: " + humidity + " %");
                                        }


                                    });


                                    time = Calendar.getInstance();
                                    int dan;
                                    String day;
                                    dan = time.get(Calendar.DAY_OF_WEEK);
                                    switch (dan) {
                                        case Calendar.MONDAY:
                                            day = "Ponedeljak";
                                            break;
                                        case Calendar.TUESDAY:
                                            day = "Utorak";
                                            break;
                                        case Calendar.WEDNESDAY:
                                            day = "Sreda";
                                            break;
                                        case Calendar.THURSDAY:
                                            day = "Cetvrtak";
                                            break;
                                        case Calendar.FRIDAY:
                                            day = "Petak";
                                            break;
                                        case Calendar.SATURDAY:
                                            day = "Subota";
                                            break;
                                        case Calendar.SUNDAY:
                                            day = "Nedelja";
                                            break;
                                        default:
                                            day = "";
                                    }

                                    Data[] d = weather_helper.read_all_data_loc(location);
                                    Log.d("PROROROROROR", d[0].getLocation());
                                    int i;

                                    Data[] data_read = weather_helper.read_all_data_loc(location);

                                    int len = data_read.length - 1;

                                    for(i = 0; i < d.length; i++){
                                        if(day.equals(d[i].getDay()) == true){
                                            Log.d("PROREEEEEEOR", d[i].getDay());
                                            weather_helper.deleteData(d[i].getDate_time(),d[i].getLocation());
                                            break;
                                        }
                                    }

                                    weather_helper.deleteData(day,d[i].getLocation());
                                    data = new Data(day, data_time, location, Integer.toString(temp), pressure, humidity, sunset, sunrise, wind_speed, wind_direction);
                                    weather_helper.insert(data);

                                    update.setText("last updated \n" + data_read[len].getDate_time());

                                }
                            });


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;



        }
    }

    public String windConverter(double degree) {
        if (degree <= 22.5 && degree > 337.5) {
            return "Sever";
        }
        if (degree > 22.5 && degree <= 67.5) {
            return "Severo-istok";
        }
        if (degree > 67.5 && degree <= 112.5) {
            return "Istok";
        }
        if (degree > 112.5 && degree <= 157.5) {
            return "Jugo-istok";
        }
        if (degree > 157.5 && degree <= 202.5) {
            return "Jug";
        }
        if (degree > 202.5 && degree <= 247.5) {
            return "Jugo-zapad";
        }
        if (degree > 247.525 && degree <= 292.5) {
            return "Zapad";
        }
        return "Severo-zapad";

    }

  /*  public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServiceHelper.LocalBinder binder = (ServiceHelper.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };*/
}


