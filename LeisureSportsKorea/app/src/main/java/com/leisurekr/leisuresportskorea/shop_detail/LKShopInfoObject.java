package com.leisurekr.leisuresportskorea.shop_detail;

import android.support.annotation.Nullable;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class LKShopInfoObject {
    public int id;
    public String name;
    public String image;
    public String about;
    public String howTo;
    public double latitude;
    public double longitude;
    public String address1;
    public String address2;
    public String address3;
    public String pNumber;
    public String email;
    public boolean isPickUp;
    public boolean isBasicEnglish;
    public boolean isBasicChinese;
    public boolean isLockerRoom;
    public boolean isShowerRoom;
    public boolean isParkingLot;
    public boolean isClothsForChange;
    public boolean isTowels;
    public boolean isSunBlock;
    public boolean isWashingKit;
    public String createdAt;
    public String updatedAt;
    public ArrayList<String> shopImages;
    public LKShopReviewsObject reviewsObject;

    public LKShopInfoObject(){}

    public LKShopInfoObject(int id, String name, String image, String about, String howTo,
                            double latitude, double longitude, String address1, String address2,
                            String address3, String pNumber, String email, boolean isPickUp,
                            boolean isBasicEnglish, boolean isBasicChinese, boolean isLockerRoom,
                            boolean isShowerRoom, boolean isParkingLot, boolean isClothsForChange,
                            boolean isTowels, boolean isSunBlock, boolean isWashingKit,
                            String createdAt, String updatedAt, ArrayList<String> shopImages,
                            LKShopReviewsObject reviewsObject) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.about = about;
        this.howTo = howTo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.pNumber = pNumber;
        this.email = email;
        this.isPickUp = isPickUp;
        this.isBasicEnglish = isBasicEnglish;
        this.isBasicChinese = isBasicChinese;
        this.isLockerRoom = isLockerRoom;
        this.isShowerRoom = isShowerRoom;
        this.isParkingLot = isParkingLot;
        this.isClothsForChange = isClothsForChange;
        this.isTowels = isTowels;
        this.isSunBlock = isSunBlock;
        this.isWashingKit = isWashingKit;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shopImages = shopImages;
        this.reviewsObject = reviewsObject;
    }
}
