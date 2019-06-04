package rtrk.pnrs.projekat1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rtrk.pnrs.projekat1.DetailsActivity;
import rtrk.pnrs.projekat1.R;

public class AdapterClass extends BaseAdapter {

    private RadioButton mRadioBtn = null;
    private Context mContext;
    private ArrayList<Item> mItem;

    public AdapterClass(Context context) {
        mContext = context;
        mItem = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        boolean inList = false;

        for (Item i : mItem) {
            if(i.city_name.equals(item.city_name) || item.city_name.equals("")){
                inList=true;
                break;
            }
        }

        if(!inList)
            mItem.add(item);
    }

    public void removeCity(int position) {
        mItem.remove(position);
        notifyDataSetChanged();
    }


    public boolean containsElement(Item element){
        boolean ind = false;
        for (Item el : mItem) {
            if (el.city_name.equals(element.city_name))
                ind = true;
        }
        return ind;
    }


    @Override
    public int getCount() {
        return mItem.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;
        try {
            rv = mItem.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return rv;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.element_row, null);
            ViewHolder holder = new ViewHolder();
            holder.check = (RadioButton) view.findViewById(R.id.check_list);
            holder.name = (TextView) view.findViewById(R.id.text_list);
            holder.choose = (Button) view.findViewById(R.id.izaberiBtn);
            view.setTag(holder);
        }

        Item item = (Item) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details_activity = new Intent(mContext, DetailsActivity.class);
                details_activity.putExtra("city",holder.name.getText());
                mContext.startActivity(details_activity);
                mRadioBtn = (RadioButton) v;
                mRadioBtn.setChecked(false);
            }
        });

        holder.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details_activity = new Intent(mContext, DetailsActivity.class);
                details_activity.putExtra("city",holder.name.getText());
                mContext.startActivity(details_activity);
            }
        });
        holder.name.setText(item.city_name);

        return view;
    }

    private class ViewHolder {
        public RadioButton check = null;
        public TextView name = null;
        public Button choose = null;
    }
}
