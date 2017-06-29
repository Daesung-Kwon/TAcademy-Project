package com.leisurekr.leisuresportskorea.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by win7-28 on 2017-06-10.
 */

public class ShopObject {
    String shopName;
    int rank;
    String logoImage;
    int id;
    String image;
    double score;

    public void setDate(JSONObject object){
        try {
            shopName = object.getString("shopName");
            rank = object.getInt("rank");
            logoImage = object.getString("logoImage");
            id = object.getInt("id");
            JSONArray jsonArray = object.getJSONArray("shopImages");
            image = jsonArray.getString(0);
            score = object.getDouble("score");

            Log.e("파싱 성공", "shop 파싱 성공");
        }catch (JSONException e){
            Log.e("JSON Error","shop 파싱 오류",e);
        }

    }

}