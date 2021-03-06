package com.leisurekr.leisuresportskorea.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2017-06-07.
 */

public class BannerObject {
    int id;
    String name;
    String image;

    public void setDate(JSONObject object){
        try {
            this.id=object.getInt("id");
            this.name=object.getString("shopName");
            JSONArray jsonArray = object.getJSONArray("shopImages");
            //this.image=jsonArray.getJSONObject(0).getString("image");
            this.image=jsonArray.getJSONObject(0).getString("thumbnail");

            Log.e("bannerobject", "banner 파싱 성공");
        }catch (JSONException e){
            Log.e("BennerObject JSON Error","banner 파싱 오류");
        }

    }
}
