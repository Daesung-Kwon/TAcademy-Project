package com.leisurekr.leisuresportskorea.profile;

import java.io.Serializable;

/**
 * Created by user on 2017-05-29.
 */

public class CartObject implements Serializable{

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

    public void setData(int activityImage, String shopName, String text1, String text2,String date
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
}