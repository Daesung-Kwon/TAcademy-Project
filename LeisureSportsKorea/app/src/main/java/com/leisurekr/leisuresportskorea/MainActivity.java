package com.leisurekr.leisuresportskorea;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Toolbar toolbar;
    private SwipeRefreshLayout refreshLayout;
    private View searchView;
    private FloatingActionButton searchButton;
    boolean flag=false;
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

        Log.i("검색test","클릭됨");
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                if(searchView.getVisibility()==View.GONE) {
                    Log.i("검색test1111","false = > true");
                    searchView.setVisibility(View.VISIBLE);
                    searchButton.setVisibility(View.VISIBLE);
                    //popupWindow.setAnimationStyle(-1);
                    //popupWindow.showAsDropDown(toolbar);
                    //popupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER,0,0);
                    flag = true;
                }
                else {
                    Log.i("검색test","true = > false");
                    searchView.setVisibility(View.GONE);
                    searchButton.setVisibility(View.GONE);
                    //popupWindow.dismiss();
                    flag = false;
                }
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);

       /* searchView = View.inflate(this,R.layout.search,null);
        popupWindow = new PopupWindow(searchView,1000,500,true);*/

        searchView = findViewById(R.id.search_view);
        searchButton = (FloatingActionButton) findViewById(R.id.search_actionbtn);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        // Set Tab Selected Listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "re-tapped", Toast.LENGTH_SHORT).show();
            }
        });


    }


    Handler mHandler = new Handler(Looper.getMainLooper());
}
