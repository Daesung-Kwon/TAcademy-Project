package com.leisurekr.leisuresportskorea.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.LKApplication;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.shop_detail.ShopDetailActivity;

import static com.leisurekr.leisuresportskorea.home.TabFragment1.owner;

/**
 * Created by user on 2017-06-05.
 */

public class ShopImageObject {

    public  View mView;
    //public final RelativeLayout relativeLayout;
    public  ImageView mShopMainImage;
    public  LinearLayout dim;

    public  ImageView mShopCircleImage;
    public  TextView mShopName;
    public  TextView mShopLocation;
    public  TextView mShopRating;
    public ImageView heart;
    public ImageView share;

    ShopObject shopObject;


    public ShopImageObject(View mView) {
        this.mView = mView;
        //relativeLayout = (RelativeLayout) mView.findViewById(R.id.bestshop_main_releative);
        this.mShopMainImage = (ImageView) mView.findViewById(R.id.bestshop_main_image);
        this.dim = (LinearLayout) mView.findViewById(R.id.bestshop_dim);
        this.mShopCircleImage = (ImageView) mView.findViewById(R.id.bestshop_circle_image);
        this.mShopName = (TextView) mView.findViewById(R.id.bestshop_name_text);
        this.mShopLocation = (TextView) mView.findViewById(R.id.bestshop_location_text);
        this.mShopRating = (TextView) mView.findViewById(R.id.bestshop_rating_text);
        this.heart = (ImageView) mView.findViewById(R.id.favorite_item_icon_in_shop);
        this.share = (ImageView) mView.findViewById(R.id.share_item_icon_in_shop);
        this.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("heart in home","click");
                final FavorObject favorObject = new FavorObject();
                favorObject.setShopId(shopObject.id);
                favorObject.setUserId(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String result = OkHttpAPIHelperHandler.favorJSONInsert(favorObject);
                        owner.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("heart",result);
                                if(result.equals("success")) {
                                    if(heart.isSelected()) {
                                        heart.setImageResource(R.drawable.btn_heart_unpress);
                                        heart.setSelected(false);
                                    }else{
                                        heart.setImageResource(R.drawable.btn_heart_press);
                                        heart.setSelected(true);
                                    }
                                }
                            }
                        });
                    }
                });

            }
        });
    }

    public void setData(int shopMaonImage, String name, String location,String rate){
        mShopMainImage.setBackgroundResource(shopMaonImage);
        dim.setAlpha(0.9f);
        mShopCircleImage.setBackgroundResource(R.drawable.pic_shop1);
        mShopName.setText(name);
        mShopLocation.setText(location);
        mShopRating.setText(rate);
    }

    public void setData(final ShopObject object){
        shopObject = object;
        Glide.with(LKApplication.getLKApplication()).load(object.image).into(mShopMainImage);
        mShopMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShopDetailActivity.class);
                intent.putExtra("shopId", object.id); // shopInfo.shopId
                owner.startActivity(intent);
            }
        });
        dim.setAlpha(0.9f);
        mShopCircleImage.setBackgroundResource(R.drawable.pic_shop1);
        mShopName.setText(object.shopName);
        //mShopLocation.setText();
        mShopRating.setText(Double.toString(object.score));

        if(object.likes){
            heart.setImageResource(R.drawable.btn_heart_press);
            heart.setSelected(true);
        }else {
            heart.setImageResource(R.drawable.btn_heart_unpress);
            heart.setSelected(false);
        }
    }

    public void setOnClick(View.OnClickListener listener){
        mView.setOnClickListener(listener);
    }

}
