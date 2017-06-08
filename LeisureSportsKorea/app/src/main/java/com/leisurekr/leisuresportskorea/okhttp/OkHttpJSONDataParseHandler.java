package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.home.HomeObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKEntityObjectDataType2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mobile on 2017. 5. 24..
 */

public class OkHttpJSONDataParseHandler {

    public static HomeObject getJSONHomeList(
            JSONObject buf) {
        HomeObject object= new HomeObject();
        JSONObject jsonObject=null;
        JSONArray banner = null;
        JSONArray activity = null;
        JSONArray shop = null;
        if(buf != null){
            try {
                jsonObject = buf;
                banner = jsonObject.getJSONArray("banner");
                activity = jsonObject.getJSONArray("recommand");
                shop = jsonObject.getJSONArray("BestShop");
                object.setData(banner,activity,shop);
            }catch (JSONException je) {
                Log.e("RequestAllList", "JSON파싱 중 에러발생", je);
            }
        }
        return object;
    }

    public static ArrayList<LKEntityObjectDataType2> getJSONTestList(
            JSONArray buf) {

        ArrayList<LKEntityObjectDataType2> jsonAllList = null;
        JSONArray jsonArray = null;
        try {
            jsonAllList = new ArrayList<LKEntityObjectDataType2>();
            jsonArray = buf;
            int jsonObjSize = jsonArray.length();
            for (int i = 0; i < jsonObjSize; i++) {

                LKEntityObjectDataType2 entity = new LKEntityObjectDataType2();

                JSONObject jData = jsonArray.getJSONObject(i);

                entity.id = jData.getInt("id");
                entity.name = jData.getString("name");
                entity.image = jData.getString("image");
                entity.about = jData.getString("about");
                entity.howTo = jData.getString("howto");
                entity.prepare = jData.getString("prepare");
                entity.address = jData.getString("address");
                entity.isPickUp = jData.getBoolean("pickUp");
                entity.isBasicEnglish = jData.getBoolean("basicEnglish");
                entity.isBasicChinese = jData.getBoolean("basicChinese");
                entity.isLockerRoom = jData.getBoolean("lockerRoom");
                entity.isShowerRoom = jData.getBoolean("showerRoom");
                entity.isParkingLot = jData.getBoolean("parkingLot");
                entity.isClothsForChange = jData.getBoolean("clothsForChange");
                entity.isTowels = jData.getBoolean("towels");
                entity.isSunBlock = jData.getBoolean("sunBloack");
                entity.isWashingKit = jData.getBoolean("washingKit");
                entity.createdAt = jData.getString("createdAt");
                entity.updatedAt = jData.getString("updatedAt");
                entity.shopImages = jData.getJSONArray("shopImages");

                jsonAllList.add(entity);
            }
        } catch (JSONException je) {
            Log.e("RequestAllList", "JSON파싱 중 에러발생", je);
        }
        return jsonAllList;
    }
}
