package rtrk.pnrs.projekat1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        private Button prikazi;
        private EditText city;
        ListView list;
        AdapterClass adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new AdapterClass(this);
        adapter.addItem(new Item(getString(R.string.bg)));
        adapter.addItem(new Item(getString(R.string.rome)));
        adapter.addItem(new Item(getString(R.string.london)));


        list = (ListView) findViewById(R.id.list_cities);
        list.setAdapter(adapter);

        prikazi = (Button) findViewById(R.id.prikaziButton);
        city = (EditText) findViewById(R.id.editLokacija);

        prikazi.setOnClickListener(this);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeCity(position);
                return true;
            }
        });

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.prikaziButton:
                Item element = new Item(city.getText().toString());
                if(!city.getText().toString().isEmpty()){
                    if(adapter.containsElement(element)){
                        Toast.makeText(this, getString(R.string.vecpostoji),Toast.LENGTH_SHORT).show();
                    }else {
                        adapter.addItem(element);

                    }
                    city.setText("");
                }else{
                    Toast.makeText(this, getString(R.string.prazno),Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }

    }
    }

