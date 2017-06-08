package com.leisurekr.leisuresportskorea.home;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2017-06-07.
 */

public class HomeObject {
    ArrayList<BannerObject> banners = new ArrayList<>();
    JSONObject object;

    public void setData(JSONArray banner, JSONArray activity, JSONArray shop){
        if(banner!=null){
            int bannerLength = banner.length();
            for(int i=0; i<bannerLength; i++){
                try {
                    object = banner.getJSONObject(i);

                    BannerObject bannerObject = new BannerObject();
                    bannerObject.setDate(object);

                    banners.add(bannerObject);
                    Log.e("RequestAllList", object.getJSONArray("shopImages").toString());
                    Log.e("RequestAllList", banner.toString());
                }catch (JSONException je) {
                    Log.e("RequestAllList", "JSON파싱 중 에러발생", je);
                }
            }
        }

    }

    public ArrayList<BannerObject> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<BannerObject> banners) {
        this.banners = banners;
    }
}
