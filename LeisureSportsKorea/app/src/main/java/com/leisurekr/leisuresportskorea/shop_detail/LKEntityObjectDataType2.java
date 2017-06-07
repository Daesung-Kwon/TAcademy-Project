package com.leisurekr.leisuresportskorea.shop_detail;

import android.support.annotation.Nullable;

import org.json.JSONArray;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class LKEntityObjectDataType2 {
    public int id;
    public String name;
    public String image;
    public String about;
    public String howTo;
    public String prepare;
    public String address;
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
    public JSONArray shopImages;

    public LKEntityObjectDataType2(){}
    public LKEntityObjectDataType2(int id, String name, String image, String about, String howTo,
                                   String prepare, @Nullable String address, boolean isPickUp, boolean isBasicEnglish,
                                   boolean isBasicChinese, boolean isLockerRoom, boolean isShowerRoom,
                                   boolean isParkingLot, boolean isClothsForChange, boolean isTowels,
                                   boolean isSunBlock, boolean isWashingKit, String createdAt,
                                   String updatedAt, JSONArray shopImages) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.about = about;
        this.howTo = howTo;
        this.prepare = prepare;
        this.address = address;
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
    }
}
