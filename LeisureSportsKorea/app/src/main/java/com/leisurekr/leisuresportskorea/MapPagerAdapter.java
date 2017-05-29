package com.leisurekr.leisuresportskorea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leisurekr.leisuresportskorea.MapCustomFragment;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class MapPagerAdapter extends FragmentPagerAdapter {

    public MapPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MapCustomFragment mapCustomFragment = new MapCustomFragment();
        return mapCustomFragment;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
