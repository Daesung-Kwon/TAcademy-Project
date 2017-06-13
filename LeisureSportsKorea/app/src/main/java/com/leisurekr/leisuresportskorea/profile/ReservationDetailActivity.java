package com.leisurekr.leisuresportskorea.profile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.shop_detail.WriteReviewActivity;


public class ReservationDetailActivity extends AppCompatActivity {

    ReservationObject reservation;
    ProgramObject programObject;
    ShopObject shopObject;

    LinearLayout rootLayout;
    LinearLayout dimLayout;

    ImageView backImage;
    RelativeLayout topImage;
    //ImageView backImage;
    //ImageView topImage;
    TextView text1;
    TextView text2;
    TextView text3;
    ImageView activityIamge;

    TextView dateText;
    TextView date;
    TextView timeText;
    TextView time;
    TextView priceText;
    TextView price;
    TextView peopleText;
    TextView people;
    TextView locationText;
    TextView location1;
    TextView location2;

    LinearLayout approvedBtnLayout;
    LinearLayout finishedBtnLayout;
    LinearLayout canceledBtnLayout;
    Button cancel;
    Button detail;
    Button review;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_reservationdetail_activity);

        Intent intent = getIntent();
        reservation = (ReservationObject) intent.getSerializableExtra("reservation");

        toolbar = (Toolbar) findViewById(R.id.profile_reservationdetail_toolbar);
        toolbar.setTitle(reservation.getProgress()+" Reservation");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rootLayout = (LinearLayout) findViewById(R.id.reservation_detail_rootlayout);
        dimLayout = (LinearLayout) findViewById(R.id.reservation_detail_dim);


        backImage = (ImageView) findViewById(R.id.reservation_detail_backimage);
        topImage = (RelativeLayout) findViewById(R.id.reservation_detail_topimage);
        text1 = (TextView) findViewById(R.id.reservation_detail_text1);
        text2 = (TextView) findViewById(R.id.reservation_detail_text2);
        text3 = (TextView) findViewById(R.id.reservation_detail_text3);
        activityIamge = (ImageView) findViewById(R.id.reservation_detail_activityimage);

        dateText = (TextView) findViewById(R.id.reservation_detail_datetext);
        date = (TextView) findViewById(R.id.reservation_detail_date);
        timeText = (TextView) findViewById(R.id.reservation_detail_timetext);
        time = (TextView) findViewById(R.id.reservation_detail_time);
        priceText = (TextView) findViewById(R.id.reservation_detail_pricetext);
        price = (TextView) findViewById(R.id.reservation_detail_price);
        peopleText = (TextView) findViewById(R.id.reservation_detail_peopletext);
        people = (TextView) findViewById(R.id.reservation_detail_people);
        locationText = (TextView) findViewById(R.id.reservation_detail_locationtext);
        location1 = (TextView) findViewById(R.id.reservation_detail_location1);
        location2 = (TextView) findViewById(R.id.reservation_detail_location2);

        approvedBtnLayout = (LinearLayout) findViewById(R.id.reservation_detail_approvedbtnlayout);
        finishedBtnLayout = (LinearLayout) findViewById(R.id.reservation_detail_finishedbtnlayout);
        canceledBtnLayout = (LinearLayout) findViewById(R.id.reservation_detail_cancelbtnlayout);

        cancel = (Button) findViewById(R.id.reservation_detail_cancelbtn);
        detail = (Button) findViewById(R.id.reservation_detail_detailbtn);
        review = (Button) findViewById(R.id.reservation_detail_reviewbtn);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReservationDetailActivity.this, WriteReviewActivity.class);
                intent.putExtra("id",shopObject.id);
                startActivity(intent);
            }
        });

        //backImage.setImageResource(reservation.getBackImage());

        if(reservation.programObject!=null) {
            programObject = reservation.programObject;
            shopObject = programObject.shopObject;
            text1.setText(shopObject.name + "'s");
            text2.setText(programObject.activityName);
            text3.setText(programObject.name);
            Glide.with(ReservationDetailActivity.this).load(shopObject.image).into(backImage);


        switch (programObject.activityName){
            case "Water Ski":
                activityIamge.setImageResource(R.drawable.ic_waterski);
                break;
            case "Fun Boat":
                activityIamge.setImageResource(R.drawable.ic_funboat);
                break;
            case "Ski":
                activityIamge.setImageResource(R.drawable.ic_waterski);
                break;
        }

        date.setText(reservation.getDate());
        time.setText(reservation.getTime());
        price.setText("$"+Integer.toString(programObject.getPrice()));

        String s=" ";
        int adult = reservation.getAdult();
        int children = reservation.getChildren();
        if (adult != 0 && children != 0) {
            s = "Adult " + Integer.toString(adult
            ) + ",  Children " + Integer.toString(children);
        } else if (adult == 0 && children != 0) {
            s = "Children " + Integer.toString(children);
        } else if (children == 0 && adult != 0) {
            s = "Adult " + Integer.toString(adult);
        }
        people.setText(s);

        location1.setText(shopObject.getLocation3()+",");
        location2.setText(shopObject.getLocation2()+", "+shopObject.getLocation1());

        setLayout(reservation.getProgress());
        }
    }

    public void setLayout(String mode){
        switch (mode){
            case "Approved":
                dimLayout.setBackgroundColor(Color.BLACK);
                dimLayout.setAlpha(0.3f);
                finishedBtnLayout.setVisibility(View.GONE);
                canceledBtnLayout.setVisibility(View.GONE);
                break;
            case "Finished":
                text1.setAlpha(0.3f);
                text2.setAlpha(0.3f);
                text3.setAlpha(0.3f);
                date.setAlpha(0.3f);
                dateText.setAlpha(0.3f);
                time.setAlpha(0.3f);
                timeText.setAlpha(0.3f);
                price.setAlpha(0.3f);
                priceText.setAlpha(0.3f);
                people.setAlpha(0.3f);
                peopleText.setAlpha(0.3f);
                locationText.setAlpha(0.3f);
                location1.setAlpha(0.3f);
                location2.setAlpha(0.3f);
                activityIamge.setAlpha(0.3f);
                approvedBtnLayout.setVisibility(View.GONE);
                canceledBtnLayout.setVisibility(View.GONE);
                dimLayout.setBackgroundColor(Color.BLACK);
                dimLayout.setAlpha(0.5f);
                //text1.setTextColor(getResources().getColor(R.color.colorCartBackMedium));
                break;
            case "Canceled":
                approvedBtnLayout.setVisibility(View.GONE);
                finishedBtnLayout.setVisibility(View.GONE);

                rootLayout.setBackgroundColor(Color.BLACK);
                rootLayout.setAlpha(0.5f);
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

}
