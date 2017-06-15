package com.leisurekr.leisuresportskorea.shop_detail;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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
    public String shopImages;
    public boolean likes;
    public float score;
    public ArrayList<String> filterTag;

    public LKShopListObject() { }
    public LKShopListObject(String activityName, int price, int shopId,
                            String shopIcon, String shopName, String shopAddress1, String shopAddress2, String shopAddress3,
                            String shopImages, boolean likes, float score, ArrayList<String> filterTag) {
        this.activityName = activityName;
        this.price = price;
        this.shopId = shopId;
        this.shopIcon = shopIcon;
        this.shopName = shopName;
        this.shopAddress1 = shopAddress1;
        this.shopAddress2 = shopAddress2;
        this.shopAddress3 = shopAddress3;
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
        shopImages = in.readString();
        likes = in.readByte() != 0;
        score = in.readFloat();
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
        dest.writeString(shopImages);
        dest.writeByte((byte) (likes ? 1 : 0));
        dest.writeFloat(score);
        dest.writeStringList(filterTag);
    }
}
