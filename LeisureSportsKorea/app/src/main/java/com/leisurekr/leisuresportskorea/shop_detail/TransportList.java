package com.leisurekr.leisuresportskorea.shop_detail;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class TransportList {
    private static ArrayList<Integer> subway;
    static {
        subway = new ArrayList<>();
        subway.add(R.drawable.ic_sub_1);
        subway.add(R.drawable.ic_sub_2);
        subway.add(R.drawable.ic_sub_3);
        subway.add(R.drawable.ic_sub_4);
        subway.add(R.drawable.ic_sub_5);
        subway.add(R.drawable.ic_sub_6);
        subway.add(R.drawable.ic_sub_7);
        subway.add(R.drawable.ic_sub_8);
        subway.add(R.drawable.ic_sub_9);
    }

    public static ArrayList<Integer> getSubwayResource() { return subway; }
}
