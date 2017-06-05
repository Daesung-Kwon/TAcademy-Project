package com.leisurekr.leisuresportskorea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    LinearLayout activityImage;
    TextView title1;
    TextView title2;
    TextView title3;
    TextView price;

    LinearLayout datePopupBtn;
    TextView date;
    LinearLayout timePopupBtn;
    TextView time;

    TextView currentAdult;
    Button subAdult;
    TextView currentNumberAdult;
    Button addAdult;

    TextView currentChildren;
    Button subChildren;
    TextView currentNumberChildren;
    Button addChildren;

    TextView totalPrice;

    Button addToCartBtn;
    Button checkOutBtn;

    DatePicker datePicker;

    int adult = 1;
    int children = 0;
    int adultPrice = 0;
    int childrenPrice = 0;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        BookObject object = new BookObject();
        object.setData(R.drawable.pic_wakeboard, "01. Water Ski", "Beginner Lesson"
                , "Package", 50, 1, 50, 0, 5, 0);

        toolbar = (Toolbar) findViewById(R.id.book_toolbar);
        toolbar.setTitle("Book");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityImage = (LinearLayout) findViewById(R.id.book_activityimage);
        title1 = (TextView) findViewById(R.id.book_text1);
        title2 = (TextView) findViewById(R.id.book_text2);
        title3 = (TextView) findViewById(R.id.book_text3);
        price = (TextView) findViewById(R.id.book_price);

        datePopupBtn = (LinearLayout) findViewById(R.id.book_datepopup);
        date = (TextView) findViewById(R.id.book_date);
        timePopupBtn = (LinearLayout) findViewById(R.id.book_timepopup);
        time = (TextView) findViewById(R.id.book_time);

        currentAdult = (TextView) findViewById(R.id.book_currentadult);
        subAdult = (Button) findViewById(R.id.book_subadult);
        currentNumberAdult = (TextView) findViewById(R.id.book_currentnumber_adult);
        addAdult = (Button) findViewById(R.id.book_addadult);

        currentChildren = (TextView) findViewById(R.id.book_currentchildren);
        subChildren = (Button) findViewById(R.id.book_subchildren);
        currentNumberChildren = (TextView) findViewById(R.id.book_currentnumber_children);
        addChildren = (Button) findViewById(R.id.book_addchildren);

        totalPrice = (TextView) findViewById(R.id.book_totalprice);

        addToCartBtn = (Button) findViewById(R.id.book_addtocart);
        checkOutBtn = (Button) findViewById(R.id.book_checkout);

        activityImage.setBackgroundResource(object.getActivityImage());
        title1.setText(object.getShopName() + "'s");
        title2.setText(object.getText1());
        title3.setText(object.getText2());
        price.setText("$" + object.getPrice());

        adultPrice = object.getAdultPrice();
        childrenPrice = object.getChildrenPrice();

        currentAdult.setText("Adult 1 $" + adultPrice);
        currentChildren.setText("");

        totalPrice.setText("$" + total);

        subAdult.setOnClickListener(this);
        addAdult.setOnClickListener(this);
        subChildren.setOnClickListener(this);
        addChildren.setOnClickListener(this);
        datePopupBtn.setOnClickListener(this);
        timePopupBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String s = "";
        switch (v.getId()) {
            case R.id.book_addadult:
                adult++;
                if (adult == 0)
                    s = "";
                else
                    s = "Adult " + adult + " $" + (adult * adultPrice);

                currentAdult.setText(s);
                currentNumberAdult.setText(Integer.toString(adult));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_subadult:
                adult--;
                if (adult < 0) {
                    adult = 0;
                }
                if (adult == 0)
                    s = "";
                else
                    s = "Adult " + adult + " $" + (adult * adultPrice);
                currentAdult.setText(s);
                currentNumberAdult.setText(Integer.toString(adult));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_addchildren:
                children++;
                if (children == 0)
                    s = "";
                else
                    s = "Children " + children + " $" + (children * childrenPrice);
                currentChildren.setText(s);
                currentNumberChildren.setText(Integer.toString(children));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_subchildren:
                children--;
                if (children < 0) {
                    children = 0;
                }
                if (children == 0)
                    s = "";
                else
                    s = "Children " + children + " $" + (children * childrenPrice);
                currentChildren.setText(s);
                currentNumberChildren.setText(Integer.toString(children));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_datepopup:
                CalenderDialog cd = new CalenderDialog(BookActivity.this);
                cd.setDate(date,0);
                cd.show();
                break;
            case R.id.book_timepopup:
                TimeDialog td = new TimeDialog(BookActivity.this);
                td.setDate(time);
                td.show();
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
