package com.leisurekr.leisuresportskorea;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-06-02.
 */

public class TimeDialog extends Dialog implements View.OnClickListener
{

    LinearLayout title;

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
        setContentView(R.layout.dialog_time);

        title = (LinearLayout) findViewById(R.id.time_title);

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

        saveLayout = (LinearLayout) findViewById(R.id.time_savelayout);
        save = (Button) findViewById(R.id.time_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("08:00");
                dismiss();
            }
        });


    }

    public void setDate(TextView text){
        this.time = text;
    }

    @Override
    public void onClick(View v) {

    }
}
