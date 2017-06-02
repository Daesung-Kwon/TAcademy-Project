package com.leisurekr.leisuresportskorea;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.shop.FilterActivity;
import com.leisurekr.leisuresportskorea.shop.MapActivity;
import com.leisurekr.leisuresportskorea.ticket.TicketActivity;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;

    private View searchView;
    //private FloatingActionButton searchButton;
    boolean flag = false;
    CoordinatorLayout coordinatorLayout;
    PopupWindow popupWindow;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i("검색test", "클릭됨");
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                if (searchView.getVisibility() == View.GONE) {
                    Log.i("검색test1111", "false = > true");
                    searchView.setVisibility(View.VISIBLE);
                    //searchButton.setVisibility(View.VISIBLE);
                    //popupWindow.setAnimationStyle(-1);
                    //popupWindow.showAsDropDown(toolbar);
                    //popupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER,0,0);
                    flag = true;
                } else {
                    Log.i("검색test", "true = > false");
                    searchView.setVisibility(View.GONE);
                    //searchButton.setVisibility(View.GONE);
                    //popupWindow.dismiss();
                    flag = false;
                }
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(),TicketActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    Intent mapIntent;
    Intent filterIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);

       /* searchView = View.inflate(this,R.layout.search,null);
        popupWindow = new PopupWindow(searchView,1000,500,true);*/

        searchView = findViewById(R.id.search_view);
        //searchButton = (FloatingActionButton) findViewById(R.id.search_actionbtn);

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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Tab Layout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
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
                filterIntent = new Intent(MainActivity.this, FilterActivity.class);
                MainActivity.this.startActivity(filterIntent);

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
                    }
                    case R.id.fab1: {
                        break;
                    }
                    case R.id.fab2: {
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
                        if (isFabOpen == true) {
                            animateFAB();
                        }
                        break;
                    }
                    case 1: {
                        fab.show();
                        break;
                    }
                    case 2: {
                        fab.hide();
                        if (isFabOpen == true) {
                            animateFAB();
                        }
                        break;
                    }
                    default: {
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

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Transition exitTrans = new Explode(); // Fade(), Slide()

            Transition reenterTrans = new Explode(); // Fade(), Slide()


        }
    }
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
        } else {
            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }
}

