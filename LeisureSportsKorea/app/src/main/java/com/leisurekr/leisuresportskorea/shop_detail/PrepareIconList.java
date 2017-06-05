package com.leisurekr.leisuresportskorea.shop_detail;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class PrepareIconList {
    private static ArrayList<Integer> active;
    static {
        active = new ArrayList<Integer>();
        active.add(R.drawable.ico_cloths_active);
        active.add(R.drawable.ico_towel_active);
        active.add(R.drawable.ico_sun_active);
        active.add(R.drawable.ico_washing_active);
    }

    private static HashMap<Integer, String> activeNameMap;
    static {
        activeNameMap = new HashMap<>();
        activeNameMap.put(R.drawable.ico_cloths_active, "cloths for\nchagne");
        activeNameMap.put(R.drawable.ico_towel_active, "Towels");
        activeNameMap.put(R.drawable.ico_sun_active, "Sun block");
        activeNameMap.put(R.drawable.ico_washing_active, "Washing");
    }

    public static ArrayList<Integer> getActiveIcoList() {
        return active;
    }

    public static String getActiveName(Integer key) {
        return activeNameMap.get(key);
    }

    private static ArrayList<Integer> inactive;
    static {
        inactive = new ArrayList<Integer>();
        inactive.add(R.drawable.ico_cloths_inactive);
        inactive.add(R.drawable.ico_towel_inactive);
        inactive.add(R.drawable.ico_sun_inactive);
        inactive.add(R.drawable.ico_washing_inactive);
    }

    private static HashMap<Integer, String> inactiveNameMap;
    static {
        inactiveNameMap = new HashMap<>();
        inactiveNameMap.put(R.drawable.ico_cloths_inactive, "cloths for\nchagne");
        inactiveNameMap.put(R.drawable.ico_towel_inactive, "Towels");
        inactiveNameMap.put(R.drawable.ico_sun_inactive, "Sun block");
        inactiveNameMap.put(R.drawable.ico_washing_inactive, "Washing");
    }

    public static ArrayList<Integer> getInactiveIcoList() {
        return inactive;
    }

    public static String getInactiveName(Integer key) {
        return inactiveNameMap.get(key);
    }
}
