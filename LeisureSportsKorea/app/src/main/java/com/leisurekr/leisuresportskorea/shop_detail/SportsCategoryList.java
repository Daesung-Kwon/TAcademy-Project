package com.leisurekr.leisuresportskorea.shop_detail;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class SportsCategoryList {

    private static ArrayList<Integer> normal;
    static {
        normal = new ArrayList<Integer>();
        normal.add(R.drawable.ico_waterski);
        normal.add(R.drawable.ico_surfing);
        normal.add(R.drawable.ico_wake_board);
        normal.add(R.drawable.ico_rafting);
        normal.add(R.drawable.ico_bungee);
        normal.add(R.drawable.ico_para);
        normal.add(R.drawable.ico_funboat);
        normal.add(R.drawable.ico_skin);
        normal.add(R.drawable.ico_snowboard);
        normal.add(R.drawable.ico_ski);
        normal.add(R.drawable.ico_paint);
        normal.add(R.drawable.ico_atv);
    }

    private static HashMap<Integer, String> nameMap;
    static {
        nameMap = new HashMap<>();
        nameMap.put(R.drawable.ico_waterski, "Water ski");
        nameMap.put(R.drawable.ico_surfing, "Surging");
        nameMap.put(R.drawable.ico_wake_board, "Wake board");
        nameMap.put(R.drawable.ico_rafting, "Rafting");
        nameMap.put(R.drawable.ico_bungee, "Bungee Jump");
        nameMap.put(R.drawable.ico_para, "Paragliding");
        nameMap.put(R.drawable.ico_funboat, "Fun boat");
        nameMap.put(R.drawable.ico_skin, "Scuba diving");
        nameMap.put(R.drawable.ico_snowboard, "Snowboard");
        nameMap.put(R.drawable.ico_ski, "Ski");
        nameMap.put(R.drawable.ico_paint, "Paintball");
        nameMap.put(R.drawable.ico_atv, "ATV");
    }

    public static ArrayList<Integer> getNormalIcoList() {
        return normal;
    }

    public static String getSportsName(Integer key) {
        return nameMap.get(key);
    }
}
