package com.leisurekr.leisuresportskorea.shop_detail;

/**
 * Created by mobile on 2017. 6. 12..
 */

public class LKShopReviewsObject {
    public String review;
    public Double rating;
    public String attachedImage;
    public String userName;
    public String data;

    public LKShopReviewsObject() {}
    public LKShopReviewsObject(String review, Double rating, String attachedImage, String userName, String data) {
        this.review = review;
        this.rating = rating;
        this.attachedImage = attachedImage;
        this.userName = userName;
        this.data = data;
    }
}
