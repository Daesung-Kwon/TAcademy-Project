package com.leisurekr.leisuresportskorea.shop_detail;

/**
 * Created by mobile on 2017. 6. 2..
 */

public class ChildData {
    private String activityName;
    private String activityPrice;
    private String activityDesc;


    public ChildData(String name, String price, String desc) {
        this.activityName = name;
        this.activityPrice= price;
        this.activityDesc= desc;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(String activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }
}
