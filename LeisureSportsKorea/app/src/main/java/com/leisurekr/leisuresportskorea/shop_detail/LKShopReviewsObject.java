package com.leisurekr.leisuresportskorea.shop_detail;

/**
 * Created by mobile on 2017. 6. 12..
 */

public class LKShopReviewsObject {
    public String sex;
    public String review;
    public Double rating;
    public String attachedImage;
    public String userName;
    public String date;
    public int count;

    public LKShopReviewsObject() {}
    public LKShopReviewsObject(String sex, String review, Double rating, String attachedImage,
                               String userName, String date, int count) {
        this.sex = sex;
        this.review = review;
        this.rating = rating;
        this.attachedImage = attachedImage;
        this.userName = userName;
        this.date = date;
        this.count = count;
    }
}
