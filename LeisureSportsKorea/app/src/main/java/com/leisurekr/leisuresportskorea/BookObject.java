package com.leisurekr.leisuresportskorea;

/**
 * Created by user on 2017-06-01.
 */

public class BookObject {
    private int activityImage;
    private String shopName;
    private String text1;
    private String text2;
    private int price;
    private int adult;
    private int adultPrice;
    private int children;
    private int childrenPrice;
    private int totalPrice;

    public void setData(int activityImage, String shopName, String text1, String text2
            , int price, int adult, int adultPrice, int children, int childrenPrice
            , int totalPrice){
        this.activityImage = activityImage;
        this.shopName = shopName;
        this.text1 = text1;
        this.text2 = text2;
        this.price = price;
        this.adult = adult;
        this.adultPrice = adultPrice;
        this.children = children;
        this.childrenPrice = childrenPrice;
        this.totalPrice = totalPrice;
    }

    public int getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(int activityImage) {
        this.activityImage = activityImage;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getChildrenPrice() {
        return childrenPrice;
    }

    public void setChildrenPrice(int childrenPrice) {
        this.childrenPrice = childrenPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
