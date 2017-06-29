package com.leisurekr.leisuresportskorea.shop_detail;

/**
 * Created by mobile on 2017. 6. 2..
 */

public class ChildData {
    public int programId;
    public String programName;
    public int adultPrice;
    public int childPrice;
    public String activityDesc;
    public String activityImage;
    public int activityId;
    public String activityName;
    public int shopId;
    public int status;
    public String shopName;
    public String shopImage;

    public ChildData() {}
    public ChildData(String programName, int adultPrice, int childPrice, String activityDesc,
                     String activityImage, String activityName, int activityId, int shopId, int status, String shopName,
                     String shopImage) {
        this.programName = programName;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.activityDesc = activityDesc;
        this.activityImage = activityImage;
        this.activityName = activityName;
        this.activityId = activityId;
        this.shopId = shopId;
        this.status = status;
        this.shopName = shopName;
        this.shopImage = shopImage;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getActivityPrice() {
        return adultPrice;
    }

    public void setActivityPrice(int activityPrice) {
        this.adultPrice = activityPrice;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }
}
