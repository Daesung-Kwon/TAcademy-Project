package com.leisurekr.leisuresportskorea;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-06-02.
 */

public class TimeDialog extends Dialog implements View.OnClickListener
{

    LinearLayout title;
    ImageView close;

    Button time1;
    Button time2;
    Button time3;
    Button time4;
    Button time5;
    Button time6;
    Button time7;
    Button time8;
    Button time9;
    Button time10;
    Button time11;
    Button time12;

    Button clickedButton=null;

    String timetemp="";

    LinearLayout saveLayout;
    Button save;

    TextView time;
    String choiceTime;

    public TimeDialog(@NonNull Context context)  {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_time);

        title = (LinearLayout) findViewById(R.id.time_title);
        close = (ImageView) findViewById(R.id.time_close);

        time1 = (Button) findViewById(R.id.time_time1);
        time2 = (Button) findViewById(R.id.time_time2);
        time3 = (Button) findViewById(R.id.time_time3);
        time4 = (Button) findViewById(R.id.time_time4);
        time5 = (Button) findViewById(R.id.time_time5);
        time6 = (Button) findViewById(R.id.time_time6);
        time7 = (Button) findViewById(R.id.time_time7);
        time8 = (Button) findViewById(R.id.time_time8);
        time9 = (Button) findViewById(R.id.time_time9);
        time10 = (Button) findViewById(R.id.time_time10);
        time11 = (Button) findViewById(R.id.time_time11);
        time12 = (Button) findViewById(R.id.time_time12);

        time1.setOnClickListener(this);
        time2.setOnClickListener(this);
        time3.setOnClickListener(this);
        time4.setOnClickListener(this);
        time5.setOnClickListener(this);
        time6.setOnClickListener(this);
        time7.setOnClickListener(this);
        time8.setOnClickListener(this);
        time9.setOnClickListener(this);
        time10.setOnClickListener(this);
        time11.setOnClickListener(this);
        time12.setOnClickListener(this);

        switch (time.getText().toString()) {
            case "08:00":
                time1.setAlpha(0.5f);
                time1.setTextColor(Color.RED);
                clickedButton = time1;
                break;
            case "09:00":
                time2.setAlpha(0.5f);
                time2.setTextColor(Color.RED);
                clickedButton = time2;
                break;
            case "10:00":
                time3.setAlpha(0.5f);
                time3.setTextColor(Color.RED);
                clickedButton = time3;
                break;
            case "11:00":
                time4.setAlpha(0.5f);
                time4.setTextColor(Color.RED);
                clickedButton = time4;
                break;
            case "12:00":
                time5.setAlpha(0.5f);
                time5.setTextColor(Color.RED);
                clickedButton = time5;
                break;
            case "13:00":
                time6.setAlpha(0.5f);
                time6.setTextColor(Color.RED);
                clickedButton = time6;
                break;
            case "14:00":
                time7.setAlpha(0.5f);
                time7.setTextColor(Color.RED);
                clickedButton = time7;
                break;
            case "15:00":
                time8.setAlpha(0.5f);
                time8.setTextColor(Color.RED);
                clickedButton = time8;
                break;
            case "16:00":
                time9.setAlpha(0.5f);
                time9.setTextColor(Color.RED);
                clickedButton = time9;
                break;
            case "17:00":
                time10.setAlpha(0.5f);
                time10.setTextColor(Color.RED);
                clickedButton = time10;
                break;
            case "18:00":
                time11.setAlpha(0.5f);
                time11.setTextColor(Color.RED);
                clickedButton = time11;
                break;
            case "19:00":
                time12.setAlpha(0.5f);
                time12.setTextColor(Color.RED);
                clickedButton = time12;
                break;
        }



        saveLayout = (LinearLayout) findViewById(R.id.time_savelayout);
        save = (Button) findViewById(R.id.time_save);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText(timetemp);
                dismiss();
            }
        });


    }

    public void setDate(TextView text){
        this.time = text;
    }

    @Override
    public void onClick(View v) {
        if(clickedButton!=null){
            clickedButton.setAlpha(1f);
            clickedButton.setTextColor(Color.BLACK);
        }
        clickedButton = (Button)v;
        clickedButton.setAlpha(0.5f);
        clickedButton.setTextColor(Color.RED);
        switch (v.getId()){
            case R.id.time_time1:
                timetemp = "08:00";
                break;
            case R.id.time_time2:
                timetemp = "09:00";
                break;
            case R.id.time_time3:
                timetemp = "10:00";
                break;
            case R.id.time_time4:
                timetemp = "11:00";
                break;
            case R.id.time_time5:
                timetemp = "12:00";
                break;
            case R.id.time_time6:
                timetemp = "13:00";
                break;
            case R.id.time_time7:
                timetemp = "14:00";
                break;
            case R.id.time_time8:
                timetemp = "15:00";
                break;
            case R.id.time_time9:
                timetemp = "16:00";
                break;
            case R.id.time_time10:
                timetemp = "17:00";
                break;
            case R.id.time_time11:
                timetemp = "18:00";
                break;
            case R.id.time_time12:
                timetemp = "19:00";
                break;
        }

    }
}
