package com.leisurekr.leisuresportskorea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SwipeRefreshLayout refreshLayout;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    Intent mapIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        // FAB Animation Setting
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tap_homeimage_view));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tap_shopimage_view));
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tap_mypageimage_view));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Adapter For View Pager
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // FAB Listener
        fab.hide();
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : HHere, insert New Activity for Map
                mapIntent = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(mapIntent);

            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO : HHere, insert New Activity for Filter

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                switch (id) {
                    case R.id.fab: {
                        animateFAB();
                        break;
                    }case R.id.fab1: {
                        break;
                    }case R.id.fab2: {
                        break;
                    }
                }
            }
        });

        // Set Tab Selected Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabPosition = tab.getPosition();
                switch (tabPosition) {
                    case 0: {
                        fab.hide();
                        Log.i("FAB-Test", "Tap" + tabPosition);
                        break;
                    }case 1: {
                        fab.show();
                        Log.i("FAB-Test", "Tap" + tabPosition);
                        break;
                    }case 2: {
                        fab.hide();
                        Log.i("FAB-Test", "Tap" + tabPosition);
                        break;
                    }
                }
                viewPager.setCurrentItem(tabPosition);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshlayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TO-DO : Refreshing List

                        refreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode(); // Fade(), Slide()

            Transition reenterTrans = new Explode(); // Fade(), Slide()

            window.setExitTransition(exitTrans);
            window.setReenterTransition(reenterTrans);
            // window.setTransitionBackgroundFadeDuration(2000);
            window.setAllowEnterTransitionOverlap(true);
            window.setAllowReturnTransitionOverlap(true);
        }
    }
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    private final int MY_PERMISSION_REQUEST_LOCATION = 100;
    private void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "Shop Info. for Location", Toast.LENGTH_SHORT).show();
                }
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                MY_PERMISSION_REQUEST_LOCATION);
            } else {
                // always granted
            }
        }
    }
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 사용자가 퍼미션을 수락했을 경우...
                    // No Action
                } else {
                    // 사용자가 퍼미션을 거절했을 경우...
                    // SnackBar 추가하여 App 사용에 제한을 알려줌.
                    /*RelativeLayout layoutRoot = (RelativeLayout) findViewById(R.id.layout_root);
                    Snackbar sb = Snackbar.make(layoutRoot,
                            "App 사용에 제한이 있을 수 있습니다.", Snackbar.LENGTH_LONG);
                    sb.setAction("Re-Check", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkPermission();
                        }
                    });
                    sb.show();*/
                }
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isFabOpen = false;
            Log.d("Test-Daon", "close");
        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
            Log.d("Test-Daon", "open");
        }
    }
}
