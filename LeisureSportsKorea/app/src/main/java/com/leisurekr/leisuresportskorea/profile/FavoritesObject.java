package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by user on 2017-06-16.
 */

public class FavoritesObject {

    private int shopId;
    private String shopName;
    private String logoImage;
    private String address1;
    private String address2;
    private HashMap<String, Boolean>activities;
    private double score;
    private String shopImage;
    private Boolean likes;
    private int price;


    public void setData(JSONObject jsonObject){
        try {
            this.shopId = jsonObject.getInt("id");
            this.shopName = jsonObject.getString("name");
            this.logoImage = jsonObject.getString("logoImage");
            this.address1 = jsonObject.getString("address1");
            this.address2 = jsonObject.getString("address2");
            this.score = jsonObject.getDouble("score");
            this.shopImage = jsonObject.getString("shopImage");
            this.likes = jsonObject.getBoolean("likes");
            this.price = jsonObject.getInt("price");

            JSONObject jsonObject1 = jsonObject.getJSONObject("activityNames");
            activities = new HashMap<>();
            activities.put("ATV",jsonObject1.getBoolean("ATV"));
            activities.put("Bungee Jump",jsonObject1.getBoolean("Bungee Jump"));
            activities.put("Fun Boat",jsonObject1.getBoolean("Fun Boat"));
            activities.put("Paintball",jsonObject1.getBoolean("Paintball"));
            activities.put("Paragliding",jsonObject1.getBoolean("Paragliding"));
            activities.put("Rafting",jsonObject1.getBoolean("Rafting"));
            activities.put("Scuba Diving",jsonObject1.getBoolean("Scuba Diving"));
            activities.put("Ski",jsonObject1.getBoolean("Ski"));
            activities.put("Snowboard",jsonObject1.getBoolean("Snowboard"));
            activities.put("Surfing",jsonObject1.getBoolean("Surfing"));
            activities.put("Wakeboard",jsonObject1.getBoolean("Wakeboard"));
            activities.put("Water Ski",jsonObject1.getBoolean("Water Ski"));


            Log.e("파싱 성공","Favorites 에서 파싱성공");
        }catch(JSONException e){
            Log.e("파싱 오류","Favorites 에서 파싱오류",e);
        }
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public HashMap<String, Boolean> getActivities() {
        return activities;
    }

    public void setActivities(HashMap<String, Boolean> activities) {
        this.activities = activities;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public Boolean getLikes() {
        return likes;
    }

    public void setLikes(Boolean likes) {
        this.likes = likes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
