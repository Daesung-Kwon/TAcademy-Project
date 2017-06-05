package com.leisurekr.leisuresportskorea.shop_detail;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class ServiceFacilityList {
    private static ArrayList<Integer> active;
    static {
        active = new ArrayList<Integer>();
        active.add(R.drawable.ico_pickup_active);
        active.add(R.drawable.ico_english_active);
        active.add(R.drawable.ico_chine_active);
        active.add(R.drawable.ico_locker_active);
        active.add(R.drawable.ico_shower_active);
        active.add(R.drawable.ico_parking_active);
    }

    private static HashMap<Integer, String> activeNameMap;
    static {
        activeNameMap = new HashMap<>();
        activeNameMap.put(R.drawable.ico_pickup_active, "Pick up");
        activeNameMap.put(R.drawable.ico_english_active, "Basic\nEnglish");
        activeNameMap.put(R.drawable.ico_chine_active, "Basic\nChinese");
        activeNameMap.put(R.drawable.ico_locker_active, "Locker\nRoom");
        activeNameMap.put(R.drawable.ico_shower_active, "Shower\nRoom");
        activeNameMap.put(R.drawable.ico_parking_active, "Prking lot\n(Charged)");
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
        inactive.add(R.drawable.ico_pickup_inactive);
        inactive.add(R.drawable.ico_english_inactive);
        inactive.add(R.drawable.ico_chine_inactive);
        inactive.add(R.drawable.ico_locker_inactive);
        inactive.add(R.drawable.ico_shower_inactive);
        inactive.add(R.drawable.ico_parking_inactive);
    }

    private static HashMap<Integer, String> inactiveNameMap;
    static {
        inactiveNameMap = new HashMap<>();
        inactiveNameMap.put(R.drawable.ico_pickup_inactive, "Pick up");
        inactiveNameMap.put(R.drawable.ico_english_inactive, "Basic\nEnglish");
        inactiveNameMap.put(R.drawable.ico_chine_inactive, "Basic\nChinese");
        inactiveNameMap.put(R.drawable.ico_locker_inactive, "Locker\nRoom");
        inactiveNameMap.put(R.drawable.ico_shower_inactive, "Shower\nRoom");
        inactiveNameMap.put(R.drawable.ico_parking_inactive, "Prking lot\n(Charged)");
    }

    public static ArrayList<Integer> getInactiveIcoList() {
        return inactive;
    }

    public static String getInactiveName(Integer key) {
        return inactiveNameMap.get(key);
    }
}
