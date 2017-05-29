package com.leisurekr.leisuresportskorea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by mobile on 2017. 5. 23..
 */

public class TestArrayList {
    private static ArrayList<Integer> imageResources;
    static {
        imageResources = new ArrayList<Integer>();

        imageResources.add(R.drawable.girls_generation_all);
        imageResources.add(R.drawable.girls_generation_tifany);
        imageResources.add(R.drawable.girls_eneration_tifany);
    }

    private static HashMap<Integer, String> nameMaps;
    static {
        nameMaps = new HashMap<>();

        nameMaps.put(R.drawable.girls_generation_all, "소녀시대 멤버");
        nameMaps.put(R.drawable.girls_generation_tifany, "티파니");
        nameMaps.put(R.drawable.girls_eneration_tifany, "티파니2");
    }

    private static Random random = new Random(System.currentTimeMillis());

    public static void removeResource(int position) {
        imageResources.remove(position);
        //nameMaps.remove(imageResources.get(position));
    }

    public static ArrayList<Integer> getArrayList() {
        return imageResources;
    }

    public static ArrayList<Integer> getSuffleArrayList() {
        Collections.shuffle(imageResources, random);
        return imageResources;
    }
    public static String  getGirlGroupName(Integer key) {
        return nameMaps.get(key);
    }
}
