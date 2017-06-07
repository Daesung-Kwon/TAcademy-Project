package com.leisurekr.leisuresportskorea.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.shop_detail.PrepareIconList;

import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class FilterImageAdapter extends BaseAdapter {
    private Context context;
    private HashMap<Integer, Integer> interestMap;
    private static int MAX_GRID_COUNT = 12;
    private String[] tag;

    public FilterImageAdapter(Context context, String[] values) {
        this.context = context;
        tag = values;

        this.interestMap = new HashMap<>();
        for (int i=0; i < values.length; i++) {
            if (values[i] == "0") {
                interestMap.put(i, FilteringList.getInactiveIcoList().get(i));
            }else {
                interestMap.put(i, FilteringList.getActiveIcoList().get(i));
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.interest_grid_item, null);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            if (tag[position] == "0") {
                textView.setText(FilteringList.getInactiveSportsName(interestMap.get(position)));
            }else {
                textView.setText(FilteringList.getActiveSportsName(interestMap.get(position)));
            }

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(interestMap.get(position));

        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }

    @Override
    public int getCount() { return MAX_GRID_COUNT; }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
