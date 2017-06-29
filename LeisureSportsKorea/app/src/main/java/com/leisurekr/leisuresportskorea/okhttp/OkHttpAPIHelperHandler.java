package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.home.HomeObject;
import com.leisurekr.leisuresportskorea.profile.ReservationObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKEntityObjectDataType2;
import com.leisurekr.leisuresportskorea.ticket.TicketObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.util.Log.e;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class OkHttpAPIHelperHandler {

    public static HomeObject homeJSONAllSelect() {
        OkHttpClient toServer = null;
        HomeObject objects = null;

        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_HOME_INFO_ALL_SELECT)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON1", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);
                    if (jsonObject.getString("msg").equals("success"))
                        jsonObject = jsonObject.getJSONObject("data");
                    else
                        //실패하면 뭔가를 한다.
                        Log.e("resultJSON2", jsonObject.toString());
                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                objects = OkHttpJSONDataParseHandler.getJSONHomeList(jsonObject);
                //Log.i("After Parser", bloodEntityObjects.toString());

            } else {
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally { /** TODO : Very Important!!! **/
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return objects;
    }

    public static ArrayList<ReservationObject> reservationJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<ReservationObject> objects = null;

        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_RESERVATION_INFO_ALL_SELECT)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON1", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);
                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                if (jsonObject.getString("msg").equals("success"))
                    objects = OkHttpJSONDataParseHandler.getJSONReservationList(jsonObject);
                else {
                    //실패하면 뭔가를 한다.
                }
                Log.e("Handler 성공", "Reservation Handler 성공");
                //Log.i("After Parser", bloodEntityObjects.toString());

            } else {
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally { /** TODO : Very Important!!! **/
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return objects;
    }

    public static ArrayList<TicketObject> ticketJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<TicketObject> objects = null;

        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_TICKET_INFO_ALL_SELECT)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON1", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);
                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                if (jsonObject.getString("msg").equals("success"))
                    objects = OkHttpJSONDataParseHandler.getJSONTicketList(jsonObject);
                else {
                    //실패하면 뭔가를 한다.
                }
                Log.e("Handler 성공", "ticket Handler 성공");
                //Log.i("After Parser", bloodEntityObjects.toString());

            } else {
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally { /** TODO : Very Important!!! **/
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return objects;
    }


    public static ArrayList<LKEntityObjectDataType2> testJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<LKEntityObjectDataType2> lkTestEntityObjects = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SHOP_DETAIL_INFO_ALL_SELECT)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON1", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);
                    jsonArray = jsonObject.optJSONArray("data2");

                    Log.e("resultJSON2", jsonArray.toString());
                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                lkTestEntityObjects = OkHttpJSONDataParseHandler.getJSONTestList(jsonArray);
                //Log.i("After Parser", bloodEntityObjects.toString());

            } else {
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally { /** TODO : Very Important!!! **/
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return lkTestEntityObjects;
    }
}
