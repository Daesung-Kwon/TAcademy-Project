package com.leisurekr.leisuresportskorea.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-05-26.
 */

public class ActivityImageObject {

    View view;
    ImageView imageView;
    LinearLayout dim;
    ImageView activityIcon;
    TextView activityName;
    TextView activityDescrpption1;
    TextView activityDescrpption2;
    TextView activityPrice;

    public ActivityImageObject(View view) {
        this.view = view;
        imageView = (ImageView) view.findViewById(R.id.activity_backimgae);
        dim = (LinearLayout) view.findViewById(R.id.activity_dim);
        activityIcon = (ImageView) view.findViewById(R.id.activity_icon);
        activityName = (TextView) view.findViewById(R.id.activity_name);
        activityDescrpption1 = (TextView) view.findViewById(R.id.activity_description1);
        activityDescrpption2 = (TextView) view.findViewById(R.id.activity_description2);
        activityPrice = (TextView) view.findViewById(R.id.activity_price);
    }
    public void setData(int resid, String name, String description1
            , String description2, int price){
        view.setBackgroundResource(resid);
        dim.setAlpha(0.3f);
        switch (name){
            case "Water Ski":
                activityIcon.setImageResource(R.drawable.icon_waterski);
                break;
            case "Fun Boat":
                activityIcon.setImageResource(R.drawable.icon_funboat);
                break;
        }

        activityName.setText(name);
        activityDescrpption1.setText(description1);
        activityDescrpption2.setText(description2);
        activityPrice.setText("$"+Integer.toString(price));
    }

    public void setData(ActivityObject object){
        Glide.with(LKApplication.getLKApplication()).load(object.programImage).into(imageView);
        dim.setAlpha(0.3f);
        switch (object.activityName){
            case "Water Ski":
                activityIcon.setImageResource(R.drawable.icon_waterski);
                break;
            case "Fun Boat":
                activityIcon.setImageResource(R.drawable.icon_funboat);
                break;
            case "Ski":
                activityIcon.setImageResource(R.drawable.icon_waterski);
                break;
        }

        activityName.setText(object.activityName);
        activityDescrpption1.setText(object.shopName+"'s ");
        activityDescrpption2.setText(object.programName);
        activityPrice.setText("$"+Integer.toString(object.price));
    }

    public void setOnClick(View.OnClickListener listener){
        view.setOnClickListener(listener);
    }
}
