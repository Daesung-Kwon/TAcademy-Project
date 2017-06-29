package com.leisurekr.leisuresportskorea.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class MapPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<MapCustomFragment> mFragmentList = new ArrayList<>();

    public MapPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.45f;
    }

    public void addFragment(MapCustomFragment fragment) {
        mFragmentList.add(fragment);
    }
}
