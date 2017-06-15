package com.leisurekr.leisuresportskorea.shop;

import com.leisurekr.leisuresportskorea.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mobile on 2017. 6. 5..
 */

public class FilteringList {

    private static ArrayList<Integer> active;
    static {
        active = new ArrayList<Integer>();
        active.add(R.drawable.ic_atv_act);
        active.add(R.drawable.ic_bungee_jump_act);
        active.add(R.drawable.ic_fun_boat_act);
        active.add(R.drawable.ic_paintball_act);
        active.add(R.drawable.ic_paragliding_act);
        active.add(R.drawable.ic_rafting_act);
        active.add(R.drawable.ic_scuba_diving_act);
        active.add(R.drawable.ic_ski_act);
        active.add(R.drawable.ic_snowboard_act);
        active.add(R.drawable.ic_surfing_act);
        active.add(R.drawable.ic_wake_board_act);
        active.add(R.drawable.ic_water_ski_act);
    }

    private static HashMap<Integer, String> activeNameMap;
    static {
        activeNameMap = new HashMap<>();
        activeNameMap.put(R.drawable.ic_atv_act, "ATV");
        activeNameMap.put(R.drawable.ic_bungee_jump_act, "Bungee Jump");
        activeNameMap.put(R.drawable.ic_fun_boat_act, "Fun Boat");
        activeNameMap.put(R.drawable.ic_paintball_act, "Paintball");
        activeNameMap.put(R.drawable.ic_paragliding_act, "Paragliding");
        activeNameMap.put(R.drawable.ic_rafting_act, "Rafting");
        activeNameMap.put(R.drawable.ic_scuba_diving_act, "Scuba Diving");
        activeNameMap.put(R.drawable.ic_ski_act, "Ski");
        activeNameMap.put(R.drawable.ic_snowboard_act, "Snowboard");
        activeNameMap.put(R.drawable.ic_surfing_act, "Surfing");
        activeNameMap.put(R.drawable.ic_wake_board_act, "Wake Board");
        activeNameMap.put(R.drawable.ic_water_ski_act, "Water Ski");
    }

    public static ArrayList<Integer> getActiveIcoList() {
        return active;
    }

    public static String getActiveSportsName(Integer key) {
        return activeNameMap.get(key);
    }

    private static ArrayList<Integer> inactive;
    static {
        inactive = new ArrayList<Integer>();
        inactive.add(R.drawable.ic_atv_inact);
        inactive.add(R.drawable.ic_bungee_jump_inact);
        inactive.add(R.drawable.ic_fun_boat_inact);
        inactive.add(R.drawable.ic_paintball_inact);
        inactive.add(R.drawable.ic_paragliding_inact);
        inactive.add(R.drawable.ic_rafting_inact);
        inactive.add(R.drawable.ic_scuba_diving_inact);
        inactive.add(R.drawable.ic_ski_inact);
        inactive.add(R.drawable.ic_snowboard_inact);
        inactive.add(R.drawable.ic_surfing_inact);
        inactive.add(R.drawable.ic_wake_board_inact);
        inactive.add(R.drawable.ic_water_ski_inact);
    }

    private static HashMap<Integer, String> inactiveNameMap;
    static {
        inactiveNameMap = new HashMap<>();
        inactiveNameMap.put(R.drawable.ic_atv_inact, "ATV");
        inactiveNameMap.put(R.drawable.ic_bungee_jump_inact, "Bungee Jump");
        inactiveNameMap.put(R.drawable.ic_fun_boat_inact, "Fun Boat");
        inactiveNameMap.put(R.drawable.ic_paintball_inact, "Paintball");
        inactiveNameMap.put(R.drawable.ic_paragliding_inact, "Paragliding");
        inactiveNameMap.put(R.drawable.ic_rafting_inact, "Rafting");
        inactiveNameMap.put(R.drawable.ic_scuba_diving_inact, "Scuba Diving");
        inactiveNameMap.put(R.drawable.ic_ski_inact, "Ski");
        inactiveNameMap.put(R.drawable.ic_snowboard_inact, "Snowboard");
        inactiveNameMap.put(R.drawable.ic_surfing_inact, "Surfing");
        inactiveNameMap.put(R.drawable.ic_wake_board_inact, "Wake Board");
        inactiveNameMap.put(R.drawable.ic_water_ski_inact, "Water Ski");
    }

    public static ArrayList<Integer> getInactiveIcoList() {
        return inactive;
    }

    public static String getInactiveSportsName(Integer key) {
        return inactiveNameMap.get(key);
    }
}
