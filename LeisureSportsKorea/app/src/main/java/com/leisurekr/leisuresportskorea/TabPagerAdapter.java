package com.leisurekr.leisuresportskorea;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.leisurekr.leisuresportskorea.home.TabFragment1;
import com.leisurekr.leisuresportskorea.profile.TabFragment3;
import com.leisurekr.leisuresportskorea.shop.TabFragment2;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabPagerAdapter extends FragmentPagerAdapter {

    // 탭카운트 변수
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // 현재 탭 위치의 Tab Fragment 리턴
        switch (position) {
            case 0:
                TabFragment1 tabFragment1 = new TabFragment1();
                return tabFragment1;
            case 1:
                TabFragment2 tabFragment2 = new TabFragment2();
                return tabFragment2;
            case 2:
                TabFragment3 tabFragment3 = new TabFragment3();
                return tabFragment3;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
}
