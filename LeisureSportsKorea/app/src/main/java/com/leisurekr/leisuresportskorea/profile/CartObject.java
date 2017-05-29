package com.leisurekr.leisuresportskorea.profile;

/**
 * Created by user on 2017-05-29.
 */

public class CartObject {

    private String shopname;
    private int activityImage;
    private String activityName;
    private String date;
    private String time;
    private String people;
    private int price;

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public void setActivityImage(int activityImage) {
        this.activityImage = activityImage;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShopname() {

        return shopname;
    }

    public int getActivityImage() {
        return activityImage;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPeople() {
        return people;
    }

    public int getPrice() {
        return price;
    }

    public void setData(String shopname, int activityImage, String activityName, String date
            , String time, String people, int price)
    {
        this.shopname = shopname;
        this.activityImage = activityImage;
        this.activityName = activityName;
        this.date = date;
        this.time = time;
        this.people = people;
        this.price = price;
    }
}
