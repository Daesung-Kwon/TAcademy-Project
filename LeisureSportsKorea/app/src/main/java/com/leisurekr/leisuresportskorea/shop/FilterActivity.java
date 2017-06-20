package com.leisurekr.leisuresportskorea.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView gridView;
    private FilterImageAdapter gridAdapter;
    private TextView saveBtn;
    private TextView popularityToggleBtn;
    private TextView ratingsToggleBtn;
    private TextView latestToggleBtn;
    private ImageView cancelBtn;

    int colorToggleOn = Color.parseColor("#d32f2f");
    int colorToggleOff = Color.parseColor("#838383");

    static int[] interestsValues = new int[] {
            1, 1, 1, 1,
            0, 0, 0, 0,
            0, 0, 0, 0
    };

    static String[] toggleValues = new String[] {
            "1", "0", "0"
    };
    int TOGGLE_MAX_COUNT = toggleValues.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        gridView            = (GridView) findViewById(R.id.filter_grid_view);
        cancelBtn           = (ImageView) findViewById(R.id.cancel_btn_on_filter);
        saveBtn             = (TextView) findViewById(R.id.save_button);
        popularityToggleBtn = (TextView) findViewById(R.id.popularity_in_filter);
        ratingsToggleBtn    = (TextView) findViewById(R.id.ratings_in_filter);
        latestToggleBtn     = (TextView) findViewById(R.id.latest_in_filter);

        gridAdapter = new FilterImageAdapter(this, interestsValues);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridAdapter.updateSelectedTag(position);
            }
        });

        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        popularityToggleBtn.setOnClickListener(this);
        ratingsToggleBtn.setOnClickListener(this);
        latestToggleBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn_on_filter:
                onBackPressed();
                break;
            case R.id.save_button:
                finish();

                break;
            case R.id.popularity_in_filter:
                popularityToggleBtn.setBackgroundColor(colorToggleOn);
                ratingsToggleBtn.setBackgroundColor(colorToggleOff);
                latestToggleBtn.setBackgroundColor(colorToggleOff);
                setToggleValues(0);
                break;
            case R.id.ratings_in_filter:
                popularityToggleBtn.setBackgroundColor(colorToggleOff);
                ratingsToggleBtn.setBackgroundColor(colorToggleOn);
                latestToggleBtn.setBackgroundColor(colorToggleOff);
                setToggleValues(1);
                break;
            case R.id.latest_in_filter:
                popularityToggleBtn.setBackgroundColor(colorToggleOff);
                ratingsToggleBtn.setBackgroundColor(colorToggleOff);
                latestToggleBtn.setBackgroundColor(colorToggleOn);
                setToggleValues(2);
                break;
        }
    }

    public void setToggleValues(int position) {
        for (int i = 0; i < TOGGLE_MAX_COUNT; i++) {
            if (i == position ) {
                toggleValues[i] = "1";
            }else {
                toggleValues[i] = "0";
            }
        }
    }
}
