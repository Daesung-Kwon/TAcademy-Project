package com.leisurekr.leisuresportskorea.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.R;

/**
 * Created by user on 2017-06-05.
 */

public class ShopImageObject {

    public final View mView;
    public final LinearLayout mShopMainImage;
    public final LinearLayout dim;

    public final ImageView mShopCircleImage;
    public final TextView mShopName;
    public final TextView mShopLocation;
    public final TextView mShopRating;

    public ShopImageObject(View mView) {
        this.mView = mView;
        this.mShopMainImage = (LinearLayout) mView.findViewById(R.id.bestshop_main_image);
        this.dim = (LinearLayout) mView.findViewById(R.id.bestshop_dim);
        this.mShopCircleImage = (ImageView) mView.findViewById(R.id.bestshop_circle_image);
        this.mShopName = (TextView) mView.findViewById(R.id.bestshop_name_text);
        this.mShopLocation = (TextView) mView.findViewById(R.id.bestshop_location_text);
        this.mShopRating = (TextView) mView.findViewById(R.id.bestshop_rating_text);
    }

    public void setData(int shopMaonImage, String name, String location,String rate){
        mShopMainImage.setBackgroundResource(shopMaonImage);
        dim.setAlpha(0.9f);
        mShopCircleImage.setBackgroundResource(R.drawable.pic_shop1);
        mShopName.setText(name);
        mShopLocation.setText(location);
        mShopRating.setText(rate);
    }

}
