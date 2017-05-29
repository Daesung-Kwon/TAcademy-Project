package com.leisurekr.leisuresportskorea.home;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.home.CircleAnimIndicator;
import com.leisurekr.leisuresportskorea.home.GridAdapter;
import com.leisurekr.leisuresportskorea.home.ImageAdapter;

/**
 * Created by mobile on 2017. 5. 11..
 */

public class TabFragment1 extends android.support.v4.app.Fragment {

    ViewPager viewPager;
    CircleAnimIndicator circleAnimIndicator;
    GridView gridView;
    boolean flag=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        viewPager = (ViewPager)v.findViewById(R.id.home_main_viewpagerlayout);
        circleAnimIndicator = (CircleAnimIndicator) v.findViewById(R.id.circleAnimIndicator);

        ImageAdapter adapter = new ImageAdapter(getContext());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        initIndicaotor();

        gridView = (GridView)v.findViewById(R.id.tab_fragment1_favorites_gridview);
        gridView.setAdapter(new GridAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView)view;
                Drawable temp = imageView.getDrawable();
                Drawable temp1 = getActivity().getResources().getDrawable(R.drawable.bba);

                Bitmap tmpBitmap = ((BitmapDrawable)temp).getBitmap();
                Bitmap tmpBitmap1 = ((BitmapDrawable)temp1).getBitmap();

                if(tmpBitmap.equals(tmpBitmap1))
                    imageView.setImageResource(R.mipmap.ic_launcher);
                else
                    imageView.setImageResource(R.drawable.bba);
            }
        });
        //gridView.setDrawSelectorOnTop(true);
        //gridView.setSelector(getResources().getDrawable(R.drawable.favorites_selector));

        return v;
    }

    private void initIndicaotor(){

        //원사이의 간격
        circleAnimIndicator.setItemMargin(15);
        //애니메이션 속도
        circleAnimIndicator.setAnimDuration(300);
        //indecator 생성
        circleAnimIndicator.createDotPanel(4, R.mipmap.ic_launcher , R.mipmap.ic_launcher);
    }


    /**
     * ViewPager 전환시 호출되는 메서드
     */
    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            circleAnimIndicator.selectDot(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
