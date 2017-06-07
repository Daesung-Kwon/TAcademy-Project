package com.leisurekr.leisuresportskorea.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by mobile on 2017. 5. 29..
 */

public class FilterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private GridView gridView;

    static final String[] interestsValues = new String[] {
            "1", "0", "1", "0",
            "0", "1", "0", "0",
            "0", "0", "0", "1",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = (Toolbar) findViewById(R.id.filter_toolbar);
        toolbar.setTitle("Interests");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new FilterImageAdapter(this, interestsValues));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
}
