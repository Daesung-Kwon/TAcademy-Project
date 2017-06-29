package com.leisurekr.leisuresportskorea.shop;

/**
 * Created by mobile on 2017. 6. 12..
 */

public class ShopInfoOnMapObject {
    public long latitude;
    public long longitude;
    public String shopImage;
    public String shopName;
    public String shopLocation;
    public double shopRating;
    public boolean likes;

    public ShopInfoOnMapObject() { }
    public ShopInfoOnMapObject(long latitude, long longitude, String shopName, String shopImage,
                               String shopLocation, double shopRating, boolean likes) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.shopImage = shopImage;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.shopRating = shopRating;
        this.likes = likes;
    }
}
