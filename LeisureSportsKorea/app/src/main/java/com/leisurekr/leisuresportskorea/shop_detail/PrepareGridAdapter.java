package com.leisurekr.leisuresportskorea.shop_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class PrepareGridAdapter extends BaseAdapter {
    private Context context;
    private HashMap<Integer, Integer> prepareMap;
    private static int MAX_GRID_COUNT = 4;
    private String[] tag;

    public PrepareGridAdapter(Context context, String[] values) {
        this.context = context;
        tag = values;

        this.prepareMap = new HashMap<>();
        for (int i=0; i < values.length; i++) {
            if (values[i] == "0") {
                prepareMap.put(i, PrepareIconList.getInactiveIcoList().get(i));
            }else {
                prepareMap.put(i, PrepareIconList.getActiveIcoList().get(i));
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
                textView.setText(PrepareIconList.getInactiveName(prepareMap.get(position)));
            }else {
                textView.setText(PrepareIconList.getActiveName(prepareMap.get(position)));
            }

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(prepareMap.get(position));

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
