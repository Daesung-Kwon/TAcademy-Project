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

public class CategoryGridAdapter extends BaseAdapter {
    private Context context;
    private HashMap<Integer, Integer> categoryMap;
    private static int MAX_GRID_COUNT = 4;

    public CategoryGridAdapter(Context context, String[] values) {
        this.context = context;

        this.categoryMap = new HashMap<>();
        int cnt=0;
        for (int i=0; i < values.length; i++) {
            if (values[i] == "1") {
                categoryMap.put(cnt, SportsCategoryList.getNormalIcoList().get(i));
                cnt++;
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
            textView.setText(SportsCategoryList.getSportsName(categoryMap.get(position)));

            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(categoryMap.get(position));

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
