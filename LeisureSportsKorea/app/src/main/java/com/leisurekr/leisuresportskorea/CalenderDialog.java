package com.leisurekr.leisuresportskorea;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-06-02.
 */

public class CalenderDialog extends Dialog {

    final int DIALOG_TYPE1 = 0;
    final int DIALOG_TYPE2 = 1;

    TextView date;
    LinearLayout title;
    LinearLayout saveLayout;
    Button saveBtn;
    DatePicker datePicker;

    int type=0;

    public CalenderDialog(@NonNull Context context) {
        super(context);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_calender);

        datePicker = (DatePicker) findViewById(R.id.calender_pickerdate);
        title = (LinearLayout) findViewById(R.id.calender_title);
        saveLayout = (LinearLayout) findViewById(R.id.calender_savelayout);
        saveBtn = (Button) findViewById(R.id.calender_save);

        switch (type){
            case DIALOG_TYPE1:
                title.setVisibility(View.VISIBLE);
                saveLayout.setVisibility(View.VISIBLE);
                break;
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();

                date.setText(Integer.toString(year)
                        + "년 " + Integer.toString(month) + "월 " + Integer.toString(day) + "일");

                dismiss();

            }}
        );
    }

    public void setDate(TextView text,int i){
        this.date = text;
        this.type = i;
    }
}
