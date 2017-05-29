package com.leisurekr.leisuresportskorea;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.v4.view.ViewPager;
=======
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
>>>>>>> 57bc024f30948b18ede711fbd0d410f145c5e1bf
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


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

<<<<<<< HEAD
        // View Pager
        viewPager = (ViewPager) findViewById(R.id.map_viewpager);
=======
        toolbar = (Toolbar) findViewById(R.id.map_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Shop Location on Map");

        /*actionBar = (ActionBar) findViewById(R.id.appbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Shop Info. On Map");*/
>>>>>>> 57bc024f30948b18ede711fbd0d410f145c5e1bf

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
