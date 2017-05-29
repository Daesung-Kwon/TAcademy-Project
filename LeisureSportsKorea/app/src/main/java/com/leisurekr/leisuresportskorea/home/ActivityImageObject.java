package com.leisurekr.leisuresportskorea.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-05-26.
 */

public class ActivityImageObject {

    View view;
    ImageView activityIcon;
    TextView activityName;
    TextView activityDescrpption1;
    TextView activityDescrpption2;
    TextView activityPrice;

    public ActivityImageObject(View view) {
        this.view = view;
        activityIcon = (ImageView) view.findViewById(R.id.activity_icon);
        activityName = (TextView) view.findViewById(R.id.activity_name);
        activityDescrpption1 = (TextView) view.findViewById(R.id.activity_description1);
        activityDescrpption2 = (TextView) view.findViewById(R.id.activity_description2);
        activityPrice = (TextView) view.findViewById(R.id.activity_price);
    }
    public void setData(int resid,int iconResid, String name, String description1
            , String description2, int price){
        view.setBackgroundResource(resid);
        activityIcon.setImageResource(iconResid);
        activityName.setText(name);
        activityDescrpption1.setText(description1);
        activityDescrpption2.setText(description2);
        activityPrice.setText("$"+Integer.toString(price));
    }


}
