package com.leisurekr.leisuresportskorea;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.Toolbar;

/**
 * Created by mobile on 2017. 5. 26..
 */

public class MapActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.map_viewpager);

        toolbar = (Toolbar) findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Shop Location on Map");

        // Adapter For View Pager
        MapPagerAdapter mapPagerAdapter = new MapPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mapPagerAdapter);

        toolbar = (Toolbar) findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Shop Location on Map");
        toolbar.setNavigationIcon(R.drawable.common_google_signin_btn_icon_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
