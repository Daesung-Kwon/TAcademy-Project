package com.leisurekr.leisuresportskorea.home;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by win7-28 on 2017-06-10.
 */

public class ActivityObject {

    int activityId;
    String activityName;
    String programName;
    int price;
    String programImage;
    int shopId;
    String shopName;

    public void setDate(JSONObject object){
        try {
            activityId = object.getInt("activityId");
            activityName = object.getString("activityName");
            programName = object.getString("programName");
            price = object.getInt("price");
            programImage = object.getString("programImage");
            shopId = object.getInt("shopId");
            shopName = object.getString("shopName");
            Log.e("activityObject", "activity 파싱 성공");
        }catch (JSONException e){
            Log.e("Activity JSON Error","activity 파싱 오류");

        }

    }
}
