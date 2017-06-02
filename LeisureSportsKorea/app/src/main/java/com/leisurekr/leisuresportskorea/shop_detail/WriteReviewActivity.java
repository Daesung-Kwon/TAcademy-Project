package com.leisurekr.leisuresportskorea.shop_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

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
}