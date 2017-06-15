package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by user on 2017-05-29.
 */

public class CartObject implements Serializable{

    private int id;
    private String shopName;
    private int activityImage;
    private String text1;
    private String text2;
    private String date;
    private String time;
    private int adult;
    private int children;
    private int price;
    private int adultPrice;
    private int childrenPrice;
    private int totalPrice;
    private boolean isPicked;

    ProgramObject programObject;

    public void setData(int activityImage, String shopName, String text1, String text2, String date
            , String time, int adult, int adultPrice, int children, int childrenPrice,int price,int totalPrice
            ) {

        this.activityImage = activityImage;
        this.shopName = shopName;
        this.text1 = text1;
        this.text2 = text2;
        this.date = date;
        this.time = time;
        this.price = price;
        this.adult = adult;
        this.adultPrice = adultPrice;
        this.children = children;
        this.childrenPrice = childrenPrice;
        this.totalPrice = totalPrice;
    }

    public void setData(JSONObject jsonObject)
    {
        try {
            id = jsonObject.getInt("id");
            this.adult = jsonObject.getInt("adult");
            this.children = jsonObject.getInt("child");
            this.date = jsonObject.getString("strDate");
            time = jsonObject.getString("strTime");

            programObject = new ProgramObject();
            programObject.setData(jsonObject.getJSONObject("program"));

            adultPrice=jsonObject.getJSONObject("program").getInt("adultPrice");
            childrenPrice=jsonObject.getJSONObject("program").getInt("childPrice");

            if(adult < 0){
                adult = 0;
            }
            if(children < 0){
                children = 0;
            }

            Log.e("파싱성공","cart 파싱 성공");
        }catch (JSONException e){
            Log.e("파싱 오류","cart Parsing 오류");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShopname(String shopname) {
        this.shopName = shopname;
    }

    public void setActivityImage(int activityImage) {
        this.activityImage = activityImage;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public void setChildrenPrice(int childrenPrice) {
        this.childrenPrice = childrenPrice;
    }


    public String getShopname() {
        return shopName;
    }

    public int getActivityImage() {
        return activityImage;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getAdult() {
        return adult;
    }

    public int getChildren() {
        return children;
    }

    public int getPrice() {
        return price;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public int getChildrenPrice() {
        return childrenPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ProgramObject getProgramObject() {
        return programObject;
    }

    public void setProgramObject(ProgramObject programObject) {
        this.programObject = programObject;
    }

    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }
}
