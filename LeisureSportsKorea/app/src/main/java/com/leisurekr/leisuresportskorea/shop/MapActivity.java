package com.leisurekr.leisuresportskorea.shop;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.leisurekr.leisuresportskorea.R;

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
        toolbar.setTitle("Shop Location on Map");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Adapter For View Pager
        MapPagerAdapter mapPagerAdapter = new MapPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mapPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
