package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by win7-28 on 2017-06-11.
 */

public class ShopObject implements Serializable {
    int id;
    String name;
    String location1;
    String location2;
    String location3;
    String image;

    public void setData(JSONObject jsonObject) {
        try {
            id = jsonObject.getInt("id");
            name = jsonObject.getString("name");
            image = jsonObject.getString("shopImage");
            location1 = jsonObject.getString("address1");
            location2 = jsonObject.getString("address2");
            location3 = jsonObject.getString("address3");
            Log.e("파싱성공", "shop 파싱 성공");
        } catch (JSONException e) {
            Log.e("파싱오류", "shop 파싱 오류");
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

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getLocation3() {
        return location3;
    }

    public void setLocation3(String location3) {
        this.location3 = location3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
