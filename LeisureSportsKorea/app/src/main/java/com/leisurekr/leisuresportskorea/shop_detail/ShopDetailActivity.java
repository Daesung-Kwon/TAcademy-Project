package com.leisurekr.leisuresportskorea.shop_detail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.Window;

import com.leisurekr.leisuresportskorea.R;

public class ShopDetailActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;
    Intent intent;
    int shopId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        intent = getIntent();
        shopId = intent.getIntExtra("shopId", -1);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.shop_detail_toolbar);
        toolbar.setTitle("Shop Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.shop_detail_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Shop Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Program Info"));
        tabLayout.setTabTextColors(Color.parseColor("#550c0a"), Color.parseColor("#ffffff"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.shop_detail_viewpager);
        setupViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set Tab Selected Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                viewPager.setCurrentItem(tabPosition);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode(); // Fade(), Slide()
            Transition reenterTrans = new Explode(); // Fade(), Slide()
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ShopDetailPagerAdapter shopDetailPagerAdapter = new ShopDetailPagerAdapter(getSupportFragmentManager());
        shopDetailPagerAdapter.addFragment(ShopInfoFragment.newInstance(shopId));
        shopDetailPagerAdapter.addFragment(ShopProgramFragment.newInstance(shopId));

        viewPager.setAdapter(shopDetailPagerAdapter);
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