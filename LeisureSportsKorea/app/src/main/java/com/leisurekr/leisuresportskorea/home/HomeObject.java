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
    ArrayList<ActivityObject> activities = new ArrayList<>();
    ArrayList<ShopObject> shops = new ArrayList<>();
    JSONObject object;

    public void setData(JSONArray banner, JSONArray activity, JSONArray shop){
        if(banner!=null){
            int bannerLength = banner.length();
            int activityLength = activity.length();
            int shopLength = shop.length();
            for(int i=0; i<bannerLength; i++){
                try {
                    object = banner.getJSONObject(i);

                    BannerObject bannerObject = new BannerObject();
                    bannerObject.setDate(object);

                    banners.add(bannerObject);
                }catch (JSONException je) {
                    Log.e("home", "home에서 banner 파싱 중 에러발생", je);
                }
            }
            for(int i=0; i<activityLength; i++){
                try {
                    object = activity.getJSONObject(i);

                    ActivityObject activityObject = new ActivityObject();
                    activityObject.setDate(object);

                    activities.add(activityObject);
                }catch (JSONException je) {
                    Log.e("home", "home에서 activity 파싱 중 에러발생", je);
                }
            }
            for(int i=0; i<shopLength; i++){
                try {
                    object = shop.getJSONObject(i);

                    ShopObject shopObject = new ShopObject();
                    shopObject.setDate(object);

                    shops.add(shopObject);
                }catch (JSONException je) {
                    Log.e("home", "home에서 activity 파싱 중 에러발생", je);
                }
            }
            Log.e("home", "home 파싱 끝(성공 아닐수도)");
        }

    }

    public ArrayList<BannerObject> getBanners() {
        return banners;
    }

    public void setBanners(ArrayList<BannerObject> banners) {
        this.banners = banners;
    }
}
