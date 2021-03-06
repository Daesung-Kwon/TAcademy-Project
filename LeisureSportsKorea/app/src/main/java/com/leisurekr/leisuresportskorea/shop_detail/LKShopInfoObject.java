package com.leisurekr.leisuresportskorea.shop_detail;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class LKShopInfoObject {
    public int id;
    public String name;
    public String image;
    public String about;
    public String howTo1;
    public String howTo2;
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
    public double score;
    public boolean likes;
    public String refundPoliy;
    public ArrayList<String> shopImages;
    public ArrayList<Boolean> shopActivityTag;
    public LKShopReviewsObject reviewsObject;

    public LKShopInfoObject(){}

    public LKShopInfoObject(int id, String name, String image, String about, String howTo1, String howTo2,
                            double latitude, double longitude, String address1, String address2,
                            String address3, String pNumber, String email, boolean isPickUp,
                            boolean isBasicEnglish, boolean isBasicChinese, boolean isLockerRoom,
                            boolean isShowerRoom, boolean isParkingLot, boolean isClothsForChange,
                            boolean isTowels, boolean isSunBlock, boolean isWashingKit,
                            String createdAt, String updatedAt, double score, boolean likes, String refundPoliy,
                            ArrayList<String> shopImages, ArrayList<Boolean> shopActivityTag,
                            LKShopReviewsObject reviewsObject) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.about = about;
        this.howTo1 = howTo1;
        this.howTo2 = howTo2;
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
        this.score = score;
        this.likes = likes;
        this.refundPoliy = refundPoliy;
        this.shopImages = shopImages;
        this.shopActivityTag = shopActivityTag;
        this.reviewsObject = reviewsObject;
    }
}
