package com.leisurekr.leisuresportskorea;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-06-02.
 */

public class CalenderDialog extends Dialog {

    final int DIALOG_TYPE1 = 0;
    final int DIALOG_TYPE2 = 1;

    TextView date;
    ImageView close;
    LinearLayout title;
    LinearLayout saveLayout;
    Button saveBtn;
    DatePicker datePicker;

    String dateString;

    public CalenderDialog(@NonNull Context context) {
        super(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_calender);

        close = (ImageView) findViewById(R.id.calender_close);
        datePicker = (DatePicker) findViewById(R.id.calender_pickerdate);
        datePicker.setDrawingCacheBackgroundColor(Color.RED);
        title = (LinearLayout) findViewById(R.id.calender_title);
        saveLayout = (LinearLayout) findViewById(R.id.calender_savelayout);
        saveBtn = (Button) findViewById(R.id.calender_save);
        title.setVisibility(View.VISIBLE);
        saveLayout.setVisibility(View.VISIBLE);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                dateString=year+"-"+month+"-"+day;
                switch (month){
                    case 1:
                        date.setText("January "+day+", "+year);
                        break;
                    case 2:
                        date.setText("Febuary "+day+", "+year);
                        break;
                    case 3:
                        date.setText("March "+day+", "+year);
                        break;
                    case 4:
                        date.setText("April "+day+", "+year);
                        break;
                    case 5:
                        date.setText("May "+day+", "+year);
                        break;
                    case 6:
                        date.setText("Jun "+day+", "+year);
                        break;
                    case 7:
                        date.setText("July "+day+", "+year);
                        break;
                    case 8:
                        date.setText("August "+day+", "+year);
                        break;
                    case 9:
                        date.setText("September "+day+", "+year);
                        break;
                    case 10:
                        date.setText("October "+day+", "+year);
                        break;
                    case 11:
                        date.setText("Novenber "+day+", "+year);
                        break;
                    case 12:
                        date.setText("December "+day+", "+year);
                        break;
                }
                dismiss();
            }}
        );
    }

    public void setDate(TextView text,String dateString){
        this.date = text;
        this.dateString = dateString;
    }
}
