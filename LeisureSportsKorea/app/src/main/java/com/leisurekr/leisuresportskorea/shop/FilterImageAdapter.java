package com.leisurekr.leisuresportskorea.shop;

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

public class FilterImageAdapter extends BaseAdapter {
    private Context context;
    private HashMap<Integer, Integer> interestMap;
    public int[] tag;
    ImageView iconImage;
    TextView iconName;
    LayoutInflater inflater;

    int MAX_SELECTED_COUNT = 4;
    int currentSelectedCount;

    public FilterImageAdapter(Context context, int[] values) {
        this.context = context;
        tag = values;
        currentSelectedCount = getCurrentSelectedCount(tag);
        this.interestMap = new HashMap<>();
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setValueMap();
    }

    public void setValueMap() {
        for (int i=0; i < tag.length; i++) {
            if (tag[i] == 0) {
                interestMap.put(i, FilteringList.getInactiveIcoList().get(i));
            }else {
                interestMap.put(i, FilteringList.getActiveIcoList().get(i));
            }
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.interest_grid_item2, null);
        }

        iconName = (TextView) convertView.findViewById(R.id.grid_item_label);
        iconImage = (ImageView) convertView.findViewById(R.id.grid_item_image);

        // 이미지 활성화
        setValueMap();
        refreshingItem(position);

        return convertView;
    }

    @Override
    public int getCount() { return tag.length; }

    @Override
    public Object getItem(int position) {
        return tag[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshingItem(int position) {
        switch (tag[position]) {
            case 0:
                iconName.setText(FilteringList.getInactiveSportsName(interestMap.get(position)));
                iconImage.setImageResource(FilteringList.getInactiveIcoList().get(position));
                break;
            case 1:
                iconName.setText(FilteringList.getActiveSportsName(interestMap.get(position)));
                iconImage.setImageResource(FilteringList.getActiveIcoList().get(position));
                break;
        }
    }

    public void updateSelectedTag(int position) {
        if (currentSelectedCount == MAX_SELECTED_COUNT) {
            if (tag[position] == 1) {
                tag[position] = 0;
                currentSelectedCount--;
                notifyDataSetChanged();
            }
        }else if (currentSelectedCount < MAX_SELECTED_COUNT) {
            if (tag[position] == 1) {
                tag[position] = 0;
                currentSelectedCount--;
                notifyDataSetChanged();
            }else {
                tag[position] = 1;
                currentSelectedCount++;
                notifyDataSetChanged();
            }
        }
    }

    public int getCurrentSelectedCount(int[] list) {
        int cnt = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == 1) {
                cnt++;
            }
        }
        return cnt;
    }
}
