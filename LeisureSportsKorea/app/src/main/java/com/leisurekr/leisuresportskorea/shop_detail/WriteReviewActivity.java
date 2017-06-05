package com.leisurekr.leisuresportskorea.shop_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by mobile on 2017. 6. 1..
 */

public class WriteReviewActivity extends AppCompatActivity {
    Toolbar toolbar;

    private RatingBar ratingBar;
    private TextView changedRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_rating);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.write_review_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ratingBar = (RatingBar) findViewById(R.id.write_rating_bar);
        changedRatingValue = (TextView) findViewById(R.id.input_rating);
        addListenerOnRatingBar();
    }

    public void addListenerOnRatingBar() {

        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                changedRatingValue.setText(String.valueOf(rating));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
            case R.id.save_action:

        }
        return super.onOptionsItemSelected(item);
    }
}