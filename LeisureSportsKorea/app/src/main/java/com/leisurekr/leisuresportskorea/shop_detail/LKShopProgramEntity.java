package com.leisurekr.leisuresportskorea.shop_detail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 6. 9..
 */

public class LKShopProgramEntity {
    public int price;
    public int shopId;
    public String shopIcon;
    public String shopName;
    public String shopAddress;
    public String shopImages;
    public boolean likes;
    public float score;

    public LKShopProgramEntity() {}

    public LKShopProgramEntity(int price, int shopId, String shopIcon, String shopName, String shopAddress,
                               String shopImages, boolean likes, float score) {
        this.price = price;
        this.shopId = shopId;
        this.shopIcon = shopIcon;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopImages = shopImages;
        this.likes = likes;
        this.score = score;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopImages() {
        return shopImages;
    }

    public void setShopImages(String shopImages) {
        this.shopImages = shopImages;
    }

    public boolean isLikes() {
        return likes;
    }

    public void setLikes(boolean likes) {
        this.likes = likes;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
