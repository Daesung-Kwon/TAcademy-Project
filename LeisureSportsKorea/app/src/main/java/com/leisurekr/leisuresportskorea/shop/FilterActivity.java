package com.leisurekr.leisuresportskorea.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class FilterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridView;
    private FilterImageAdapter gridAdapter;
    private TextView saveBtn;
    private ImageView cancelBtn;

    int MAX_SELECTED_COUNT = 4;
    int currentSelectedCount;

    static final String[] interestsValues = new String[] {
            "1", "0", "1", "0",
            "0", "1", "0", "0",
            "0", "0", "0", "1",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        currentSelectedCount = getCurrentSelectedCount(interestsValues);

        gridView = (GridView) findViewById(R.id.filter_grid_view);
        gridAdapter = new FilterImageAdapter(this, interestsValues);
        gridView.setAdapter(gridAdapter);

        saveBtn = (TextView) findViewById(R.id.save_button);

        cancelBtn = (ImageView) findViewById(R.id.cancel_btn_on_filter);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

    public int getCurrentSelectedCount(String[] list) {
        int cnt = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] == "1") {
                cnt++;
            }
        }
        return cnt;
    }
}
