package com.leisurekr.leisuresportskorea.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-05-26.
 */

public class ActivityImageObject {

    View view;
    LinearLayout backImage;
    LinearLayout dim;
    ImageView activityIcon;
    TextView activityName;
    TextView activityDescrpption1;
    TextView activityDescrpption2;
    TextView activityDescrpption3;
    TextView activityPrice;

    public ActivityImageObject(View view) {
        this.view = view;
        backImage = (LinearLayout) view.findViewById(R.id.activity_backimgae);
        dim = (LinearLayout) view.findViewById(R.id.activity_dim);
        activityIcon = (ImageView) view.findViewById(R.id.activity_icon);
        activityName = (TextView) view.findViewById(R.id.activity_name);
        activityDescrpption1 = (TextView) view.findViewById(R.id.activity_description1);
        activityDescrpption2 = (TextView) view.findViewById(R.id.activity_description2);
        activityDescrpption3 = (TextView) view.findViewById(R.id.activity_description3);
        activityPrice = (TextView) view.findViewById(R.id.activity_price);
    }

    public void setData(final ActivityObject object) {
        Glide.with(LKApplication.getLKApplication()).load(object.programImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new ViewTarget<LinearLayout,GlideDrawable>(backImage) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                backImage.setBackground(resource);
            }
        });
        dim.setAlpha(0.3f);

        switch (object.activityName) {
            case "Water Ski":
                activityIcon.setImageResource(R.drawable.ich_waterski);
                break;
            case "Fun Boat":
                activityIcon.setImageResource(R.drawable.ich_funboat);
                break;
            case "Ski":
                activityIcon.setImageResource(R.drawable.ich_ski);
                break;
            case "ATV":
                activityIcon.setImageResource(R.drawable.ich_atv);
                break;
            case "Bungee Jump":
                activityIcon.setImageResource(R.drawable.ich_bungee);
                break;
            case "Paintball":
                activityIcon.setImageResource(R.drawable.ich_paint);
                break;
            case "Paragliding":
                activityIcon.setImageResource(R.drawable.ich_para);
                break;
            case "Rafting":
                activityIcon.setImageResource(R.drawable.ich_rafting);
                break;
            case "Scuba Diving":
                activityIcon.setImageResource(R.drawable.ich_scub);
                break;
            case "Snowboard":
                activityIcon.setImageResource(R.drawable.ich_snowboard);
                break;
            case "Surfing":
                activityIcon.setImageResource(R.drawable.ich_surfing);
                break;
            case "Wakeboard":
                activityIcon.setImageResource(R.drawable.ich_wakeboard);
                break;

        }

        activityName.setText(object.activityName);
        activityDescrpption1.setText(object.shopName + "'s ");
        activityDescrpption2.setText(object.activityName);
        activityDescrpption3.setText(object.programName
                .substring(object.activityName.length() + 1));
        activityPrice.setText("$" + Integer.toString(object.price));
    }


    public void setOnClick(View.OnClickListener listener) {
        view.setOnClickListener(listener);
    }
}
