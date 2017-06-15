package com.leisurekr.leisuresportskorea.shop_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class LKShopListObject implements Parcelable{
    public String activityName;
    public int price;
    public int shopId;
    public String shopIcon;
    public String shopName;
    public String shopAddress1;
    public String shopAddress2;
    public String shopAddress3;
    public double latitude;
    public double longitude;
    public String shopImages;
    public boolean likes;
    public double score;
    public ArrayList<String> filterTag;

    public LKShopListObject() { }
    public LKShopListObject(String activityName, int price, int shopId, String shopIcon, String shopName,
                            String shopAddress1, String shopAddress2, String shopAddress3, double latitude, double longitude,
                            String shopImages, boolean likes, double score, ArrayList<String> filterTag) {
        this.activityName = activityName;
        this.price = price;
        this.shopId = shopId;
        this.shopIcon = shopIcon;
        this.shopName = shopName;
        this.shopAddress1 = shopAddress1;
        this.shopAddress2 = shopAddress2;
        this.shopAddress3 = shopAddress3;
        this.latitude = latitude;
        this.longitude = longitude;
        this.shopImages = shopImages;
        this.likes = likes;
        this.score = score;
        this.filterTag.addAll(filterTag);
    }

    protected LKShopListObject(Parcel in) {
        activityName = in.readString();
        price = in.readInt();
        shopId = in.readInt();
        shopIcon = in.readString();
        shopName = in.readString();
        shopAddress1 = in.readString();
        shopAddress2 = in.readString();
        shopAddress3 = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        shopImages = in.readString();
        likes = in.readByte() != 0;
        score = in.readDouble();
        filterTag = in.createStringArrayList();
    }

    public static final Creator<LKShopListObject> CREATOR = new Creator<LKShopListObject>() {
        @Override
        public LKShopListObject createFromParcel(Parcel in) {
            return new LKShopListObject(in);
        }

        @Override
        public LKShopListObject[] newArray(int size) {
            return new LKShopListObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activityName);
        dest.writeInt(price);
        dest.writeInt(shopId);
        dest.writeString(shopIcon);
        dest.writeString(shopName);
        dest.writeString(shopAddress1);
        dest.writeString(shopAddress2);
        dest.writeString(shopAddress3);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(shopImages);
        dest.writeByte((byte) (likes ? 1 : 0));
        dest.writeDouble(score);
        dest.writeStringList(filterTag);
    }
}
