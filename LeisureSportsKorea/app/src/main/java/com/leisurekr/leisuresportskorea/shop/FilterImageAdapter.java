package com.leisurekr.leisuresportskorea.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    ImageView imageView;

    int MAX_SELECTED_COUNT = 4;
    int currentSelectedCount;

    public FilterImageAdapter(Context context, String[] values) {
        this.context = context;
        tag = values;

        this.interestMap = new HashMap<>();
        initValues();
    }

    public void initValues() {
        for (int i=0; i < tag.length; i++) {
            if (tag[i] == "0") {
                interestMap.put(i, FilteringList.getInactiveIcoList().get(i));
            }else {
                interestMap.put(i, FilteringList.getActiveIcoList().get(i));
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.interest_grid_item2, null);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            if (tag[position] == "0") {
                textView.setText(FilteringList.getInactiveSportsName(interestMap.get(position)));
            }else {
                textView.setText(FilteringList.getActiveSportsName(interestMap.get(position)));
            }

            imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(interestMap.get(position));

            final int p = position;
            currentSelectedCount = getCurrentSelectedCount(tag);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentSelectedCount == 4) {
                        if (tag[p] == "1") {
                            imageView.setImageResource(FilteringList.getInactiveIcoList().get(p));
                            tag[p] = "0";
                            currentSelectedCount--;
                        }
                    }else if (currentSelectedCount < 4) {
                        if (tag[p] == "1") {
                            imageView.setImageResource(FilteringList.getInactiveIcoList().get(p));
                            tag[p] = "0";
                            currentSelectedCount--;
                        }else {
                            imageView.setImageResource(FilteringList.getActiveIcoList().get(p));
                            tag[p] = "1";
                            currentSelectedCount++;
                        }
                    }
                }
            });

        } else {
            gridView = (View) convertView;
        }
        notifyDataSetChanged();
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

    public void setChangeIconStatus(int position) {
        if (tag[position] == "0") {
            this.interestMap.remove(position);
            tag[position] = "1";
        }else {
            this.interestMap.remove(position);
            tag[position] = "0";
        }
    }

    public int getCurrentSelectedCount(String[] list) {
        int cnt = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == "1") {
                cnt++;
            }
        }
        return cnt;
    }
}
