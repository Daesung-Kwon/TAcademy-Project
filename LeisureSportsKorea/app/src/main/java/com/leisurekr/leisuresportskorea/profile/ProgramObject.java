package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by win7-28 on 2017-06-11.
 */

public class ProgramObject implements Serializable {
    int id;
    String name;
    int price;
    String activityName;
    ShopObject shopObject;

    public void setData(JSONObject object){

        try {
            id = object.getInt("id");
            name = object.getString("name");
            //price = object.getInt("price");
            activityName = object.getString("actName");
            shopObject = new ShopObject();
            shopObject.setData(object.getJSONObject("shop"));

            Log.e("파싱성공","Program 파싱 성공");
        }catch (JSONException e){
            Log.e("파싱오류","Programe 파싱 오류",e);
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ShopObject getShopObject() {
        return shopObject;
    }

    public void setShopObject(ShopObject shopObject) {
        this.shopObject = shopObject;
    }
}
