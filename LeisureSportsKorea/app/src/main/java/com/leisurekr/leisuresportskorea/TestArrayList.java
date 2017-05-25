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

        imageResources.add(R.drawable.girls_eneration_all);
        imageResources.add(R.drawable.girls_eneration_hyoyeon);
        imageResources.add(R.drawable.girls_eneration_jesica);
        imageResources.add(R.drawable.girls_eneration_seohyun);
        imageResources.add(R.drawable.girls_eneration_sunny);
        imageResources.add(R.drawable.girls_eneration_suyoung);
        imageResources.add(R.drawable.girls_eneration_taeyeon);
        imageResources.add(R.drawable.girls_eneration_tifany);
        imageResources.add(R.drawable.girls_eneration_yuri);
        imageResources.add(R.drawable.girls_eneration_yuna);
        imageResources.add(R.drawable.girls_generation_hyoyeon);
        imageResources.add(R.drawable.girls_generation_jesica);
        imageResources.add(R.drawable.girls_generation_seohyun);
        imageResources.add(R.drawable.girls_generation_sunny);
        imageResources.add(R.drawable.girls_generation_suyoung);
        imageResources.add(R.drawable.girls_generation_taeyeon);
        imageResources.add(R.drawable.girls_generation_tifany);
        imageResources.add(R.drawable.girls_generation_yuri);
        imageResources.add(R.drawable.girls_generation_yuna);
    }

    private static HashMap<Integer, String> nameMaps;
    static {
        nameMaps = new HashMap<>();

        nameMaps.put(R.drawable.girls_eneration_all, "소녀시대 멤버");
        nameMaps.put(R.drawable.girls_eneration_hyoyeon, "효연2");
        nameMaps.put(R.drawable.girls_eneration_jesica, "제시카2");
        nameMaps.put(R.drawable.girls_eneration_seohyun, "서현2");
        nameMaps.put(R.drawable.girls_eneration_sunny, "써니2");
        nameMaps.put(R.drawable.girls_eneration_suyoung, "수영2");
        nameMaps.put(R.drawable.girls_eneration_taeyeon, "태연2");
        nameMaps.put(R.drawable.girls_eneration_tifany, "티파티2");
        nameMaps.put(R.drawable.girls_eneration_yuri, "유리2");
        nameMaps.put(R.drawable.girls_eneration_yuna, "윤아2");
        nameMaps.put(R.drawable.girls_generation_hyoyeon, "효연2");
        nameMaps.put(R.drawable.girls_generation_jesica, "제시카2");
        nameMaps.put(R.drawable.girls_generation_seohyun, "서현2");
        nameMaps.put(R.drawable.girls_generation_sunny, "써니2");
        nameMaps.put(R.drawable.girls_generation_suyoung, "수영2");
        nameMaps.put(R.drawable.girls_generation_taeyeon, "태연2");
        nameMaps.put(R.drawable.girls_generation_tifany, "티파티2");
        nameMaps.put(R.drawable.girls_generation_yuri, "유리2");
        nameMaps.put(R.drawable.girls_generation_yuna, "윤아2");
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
