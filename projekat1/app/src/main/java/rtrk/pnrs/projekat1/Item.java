package rtrk.pnrs.projekat1;

import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;

public class Item {

    public String city_name;
    public RadioButton check;

    public Item(String c_n) {
        city_name = c_n;
        //check.setSelected(false);

    }
    public String getName(){
        return city_name;
    }
}



