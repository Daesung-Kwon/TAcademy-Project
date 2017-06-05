package com.leisurekr.leisuresportskorea.shop_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mobile on 2017. 5. 31..
 */

public class ShopDetailPagerAdapter extends FragmentPagerAdapter {

    public ShopDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ShopInfoFragment shopInfoFragment = new ShopInfoFragment();
                return shopInfoFragment;
            case 1:
                ShopProgramFragment shopProgramFragment = new ShopProgramFragment();
                return shopProgramFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}