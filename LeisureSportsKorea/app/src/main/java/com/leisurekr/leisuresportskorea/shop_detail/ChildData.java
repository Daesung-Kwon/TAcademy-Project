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
    public int shopId;
    public int status;

    public ChildData() {}
    public ChildData(String programName, int adultPrice, int childPrice, String activityDesc,
                     String activityImage, int activityId, int shopId, int status) {
        this.programName = programName;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.activityDesc = activityDesc;
        this.activityImage = activityImage;
        this.activityId = activityId;
        this.shopId = shopId;
        this.status = status;
    }

    public String getActivityName() {
        return programName;
    }

    public void setActivityName(String activityName) {
        this.programName = activityName;
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
}
