package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.shop_detail.ChildData;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopInfoObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopProgramEntity;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopReviewsObject;
import com.leisurekr.leisuresportskorea.shop_detail.ParentData;
import com.leisurekr.leisuresportskorea.home.HomeObject;
import com.leisurekr.leisuresportskorea.profile.CartObject;
import com.leisurekr.leisuresportskorea.profile.ReservationObject;
import com.leisurekr.leisuresportskorea.ticket.TicketObject;

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
        HomeObject object = new HomeObject();
        JSONObject jsonObject = null;
        JSONArray banner = null;
        JSONArray activity = null;
        JSONArray shop = null;
        if (buf != null) {
            try {
                jsonObject = buf;
                banner = jsonObject.getJSONArray("banner");
                activity = jsonObject.getJSONArray("recommend");
                shop = jsonObject.getJSONArray("bestShop");
                object.setData(banner, activity, shop);
            } catch (JSONException je) {
                Log.e("RequestAllList", "Home JSON파싱 중 에러발생", je);
            }
        }
        return object;
    }

    public static ArrayList<ReservationObject> getJSONReservationList(
            JSONObject buf) {
        ArrayList<ReservationObject> reservationArrayList = new ArrayList<>();
        ReservationObject object = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        if (buf != null) {
            try {
                jsonObject = buf;
                jsonArray = jsonObject.getJSONArray("data");
                int arrayLength = jsonArray.length();
                for (int i = 0; i < arrayLength; i++) {
                    object = new ReservationObject();
                    object.setData(jsonArray.getJSONObject(i));
                    reservationArrayList.add(object);

                }

            } catch (JSONException je) {
                Log.e("RequestAllList", "Reservation JSON파싱 중 에러발생", je);
            }
        }
        return reservationArrayList;
    }

    public static ArrayList<TicketObject> getJSONTicketList(
            JSONObject buf) {
        ArrayList<TicketObject> ticketArrayList = new ArrayList<>();
        TicketObject object = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        if (buf != null) {
            try {
                jsonObject = buf;
                jsonArray = jsonObject.getJSONArray("data");
                int arrayLength = jsonArray.length();
                for (int i = 0; i < arrayLength; i++) {
                    object = new TicketObject();
                    object.setData(jsonArray.getJSONObject(i));
                    ticketArrayList.add(object);

                }

            } catch (JSONException je) {
                Log.e("RequestAllList", "ticket JSON파싱 중 에러발생", je);
            }
        }
        return ticketArrayList;
    }

    public static ArrayList<CartObject> getJSONCartList(
            JSONObject buf) {
        ArrayList<CartObject> reservationArrayList = new ArrayList<>();
        CartObject object = null;
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;

        if (buf != null) {
            try {
                jsonObject = buf;
                jsonArray = jsonObject.getJSONArray("data");
                int arrayLength = jsonArray.length();
                for (int i = 0; i < arrayLength; i++) {
                    object = new CartObject();
                    object.setData(jsonArray.getJSONObject(i));
                    reservationArrayList.add(object);

                }

            } catch (JSONException je) {
                Log.e("RequestAllList", "cart JSON파싱 중 에러발생", je);
            }
        }
        return reservationArrayList;
    }

    public static ArrayList<ParentData> getJSONShopProgram(
            JSONObject buf) {

        ArrayList<ParentData> jsonAllList = null;
        ParentData entity = null;
        JSONObject jsonObject0 = null;
        JSONObject jsonObject1 = null;
        JSONArray jsonArray0 = null;
        JSONArray jsonArray1 = null;
        JSONArray jsonArray2 = null;

        try {
            jsonAllList = new ArrayList<ParentData>();
            jsonObject0 = buf.getJSONObject("data");

            if (!jsonObject0.isNull("best")) {
                jsonArray0 = jsonObject0.getJSONArray("best");
                entity = new ParentData();
                entity.child = new ArrayList<>();
                entity.category = "Best Programs";

                int bestCnt = jsonArray0.length();
                for (int i = 0; i < bestCnt; i++) {
                    ChildData childData = new ChildData();

                    childData.programId = jsonArray0.getJSONObject(i).getInt("programId");
                    childData.programName = jsonArray0.getJSONObject(i).getString("programName");
                    childData.adultPrice = jsonArray0.getJSONObject(i).getInt("adultPrice");
                    childData.childPrice = jsonArray0.getJSONObject(i).getInt("childPrice");
                    childData.activityImage = jsonArray0.getJSONObject(i).getString("image");
                    childData.activityDesc = jsonArray0.getJSONObject(i).getString("description");
                    childData.activityId = jsonArray0.getJSONObject(i).getInt("activityId");
                    childData.shopId = jsonArray0.getJSONObject(i).getInt("shopId");
                    childData.status = jsonArray0.getJSONObject(i).getInt("status");

                    entity.child.add(childData);
                }
                jsonAllList.add(entity);
            }

            if (!jsonObject0.isNull("package")) {
                jsonArray1 = jsonObject0.getJSONArray("package");
                entity = new ParentData();
                entity.child = new ArrayList<>();
                entity.category = "Package Programs";

                int bestCnt = jsonArray1.length();
                for (int i = 0; i < bestCnt; i++) {
                    ChildData childData = new ChildData();

                    childData.programId = jsonArray1.getJSONObject(i).getInt("programId");
                    childData.programName = jsonArray1.getJSONObject(i).getString("programName");
                    childData.adultPrice = jsonArray1.getJSONObject(i).getInt("adultPrice");
                    childData.childPrice = jsonArray1.getJSONObject(i).getInt("childPrice");
                    childData.activityImage = jsonArray1.getJSONObject(i).getString("image");
                    childData.activityDesc = jsonArray1.getJSONObject(i).getString("description");
                    childData.activityId = jsonArray1.getJSONObject(i).getInt("activityId");
                    childData.shopId = jsonArray1.getJSONObject(i).getInt("shopId");
                    childData.status = jsonArray1.getJSONObject(i).getInt("status");

                    entity.child.add(childData);
                }
                jsonAllList.add(entity);
            }

            if (!jsonObject0.isNull("individual")) {
                jsonArray2 = jsonObject0.getJSONArray("individual");
                entity = new ParentData();
                entity.child = new ArrayList<>();
                entity.category = "Individual Programs";

                int bestCnt = jsonArray2.length();
                for (int i = 0; i < bestCnt; i++) {
                    ChildData childData = new ChildData();

                    childData.programId = jsonArray2.getJSONObject(i).getInt("programId");
                    childData.programName = jsonArray2.getJSONObject(i).getString("programName");
                    childData.adultPrice = jsonArray2.getJSONObject(i).getInt("adultPrice");
                    childData.childPrice = jsonArray2.getJSONObject(i).getInt("childPrice");
                    childData.activityImage = jsonArray2.getJSONObject(i).getString("image");
                    childData.activityDesc = jsonArray2.getJSONObject(i).getString("description");
                    childData.activityId = jsonArray2.getJSONObject(i).getInt("activityId");
                    childData.shopId = jsonArray2.getJSONObject(i).getInt("shopId");
                    childData.status = jsonArray2.getJSONObject(i).getInt("status");

                    entity.child.add(childData);
                }
                jsonAllList.add(entity);
            }

        } catch (JSONException je) {
            Log.e("getJSONShopProgram", "JSON파싱 중 에러발생", je);
        }
        return jsonAllList;
    }

    /**
     * 선택된 샵의 상세 정보 불러오기.
     * @param buf
     * @return
     */
    public static LKShopInfoObject getJSONShopInfo(
            JSONObject buf) {

        LKShopInfoObject entity = null;
        JSONObject jsonObject0  = null;
        JSONObject jsonObject1  = null;
        JSONObject jsonObject2  = null;
        JSONObject jsonObject3  = null;
        JSONArray jsonArray0    = null;
        JSONArray jsonArray1    = null;
        try {
            jsonObject0 = buf.getJSONObject("data");
            jsonObject1 = jsonObject0.getJSONObject("review");

            jsonArray0 = jsonObject0.getJSONArray("shopDetail");
            jsonObject2 = jsonArray0.getJSONObject(0);

            entity = new LKShopInfoObject();

            entity.id                = jsonObject2.getInt("id");
            entity.name              = jsonObject2.getString("name");
            entity.image             = jsonObject2.getString("logoImage");
            entity.about             = jsonObject2.getString("about");
            entity.howTo             = jsonObject2.getString("howto");
            entity.latitude          = jsonObject2.getDouble("latitude");
            entity.longitude         = jsonObject2.getDouble("longitude");
            entity.address1          = jsonObject2.getString("address1");
            entity.address2          = jsonObject2.getString("address2");
            entity.address3          = jsonObject2.getString("address3");
            entity.pNumber           = jsonObject2.getString("pNumber");
            entity.email             = jsonObject2.getString("email");
            entity.isPickUp          = jsonObject2.getBoolean("pickUp");
            entity.isBasicEnglish    = jsonObject2.getBoolean("basicEnglish");
            entity.isBasicChinese    = jsonObject2.getBoolean("basicChinese");
            entity.isLockerRoom      = jsonObject2.getBoolean("lockerRoom");
            entity.isShowerRoom      = jsonObject2.getBoolean("showerRoom");
            entity.isParkingLot      = jsonObject2.getBoolean("parkingLot");
            entity.isClothsForChange = jsonObject2.getBoolean("clothsForChange");
            entity.isTowels          = jsonObject2.getBoolean("towels");
            entity.isSunBlock        = jsonObject2.getBoolean("sunBloack");
            entity.isWashingKit      = jsonObject2.getBoolean("washingKit");
            entity.createdAt         = jsonObject2.getString("createdAt");
            entity.updatedAt         = jsonObject2.getString("updatedAt");

            jsonObject3 = jsonObject2.getJSONObject("tags");
            entity.shopActivityTag = new ArrayList<>();
            entity.shopActivityTag.add(0, jsonObject3.getBoolean("ATV"));
            entity.shopActivityTag.add(1, jsonObject3.getBoolean("Bungee Jump"));
            entity.shopActivityTag.add(2, jsonObject3.getBoolean("Fun Boat"));
            entity.shopActivityTag.add(3, jsonObject3.getBoolean("Paintball"));
            entity.shopActivityTag.add(4, jsonObject3.getBoolean("Paragliding"));
            entity.shopActivityTag.add(5, jsonObject3.getBoolean("Rafting"));
            entity.shopActivityTag.add(6, jsonObject3.getBoolean("Scuba Diving"));
            entity.shopActivityTag.add(7, jsonObject3.getBoolean("Ski"));
            entity.shopActivityTag.add(8, jsonObject3.getBoolean("Snowboard"));
            entity.shopActivityTag.add(9, jsonObject3.getBoolean("Surfing"));
            entity.shopActivityTag.add(10, jsonObject3.getBoolean("Wakeboard"));
            entity.shopActivityTag.add(11, jsonObject3.getBoolean("Water Ski"));

            jsonArray1 = jsonObject2.getJSONArray("shopImages");
            int cnt = jsonArray1.length();
            entity.shopImages = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                entity.shopImages.add(jsonArray1.getJSONObject(i).getString("image"));
            }
            entity.reviewsObject                = new LKShopReviewsObject();
            entity.reviewsObject.review         = jsonObject1.getString("review");
            entity.reviewsObject.rating         = jsonObject1.getDouble("star");
            entity.reviewsObject.attachedImage  = jsonObject1.getString("image");
            entity.reviewsObject.userName       = jsonObject1.getJSONObject("user").getString("username");
            entity.reviewsObject.data           = jsonObject1.getString("date");

        } catch (JSONException je) {
            Log.e("getJSONShopInfo", "JSON파싱 중 에러발생", je);
        }
        return entity;
    }

    /**
     * 탭 2번째 샵정보 불러오기.
     * @param buf
     * @return
     */
    public static ArrayList<LKShopListObject> getJSONShopList(
            JSONObject buf) {

        ArrayList<LKShopListObject> jsonAllList = null;
        JSONObject jsonObject0 = null;
        JSONObject jsonObject1 = null;
        JSONObject jsonObject2 = null;
        JSONArray jsonArray0 = null;
        JSONArray jsonArray1 = null;
        try {
            jsonAllList = new ArrayList<LKShopListObject>();

            jsonObject0 = buf.getJSONObject("data");
            jsonArray0 = jsonObject0.getJSONArray("rows");

            int rowsCnt = jsonArray0.length();
            for (int i = 0; i < rowsCnt; i++) {
                JSONObject jData = jsonArray0.getJSONObject(i);

                jsonArray1 = jData.getJSONArray("programs");

                int programCnt = jsonArray1.length();
                for (int j = 0; j < programCnt; j++) {
                    LKShopListObject entity = new LKShopListObject();
                    entity.activityName     = jData.getString("activityName");
                    jsonObject1             = jsonArray1.getJSONObject(j);
                    entity.price            = jsonObject1.getInt("price");
                    jsonObject2             = jsonObject1.getJSONObject("shop");
                    entity.shopId           = jsonObject2.getInt("id");
                    entity.shopIcon         = jsonObject2.getString("icon");
                    entity.shopName         = jsonObject2.getString("name");
                    entity.shopAddress1     = jsonObject2.getString("address1");
                    entity.shopAddress2     = jsonObject2.getString("address2");
                    entity.shopAddress3     = jsonObject2.getString("address3");
                    entity.shopImages       = jsonObject2.getJSONArray("shopImages").getJSONObject(0).getString("image");
                    entity.likes            = jsonObject2.getBoolean("likes");
                    entity.score            = jsonObject2.getDouble("score");

                    jsonAllList.add(entity);
                }
            }
        } catch (JSONException je) {
            Log.e("getJSONShopList", "JSON파싱 중 에러발생", je);
        }
        return jsonAllList;
    }
}
