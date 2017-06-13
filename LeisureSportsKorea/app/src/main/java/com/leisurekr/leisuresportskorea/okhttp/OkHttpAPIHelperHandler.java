package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.home.HomeObject;
import com.leisurekr.leisuresportskorea.profile.CartObject;
import com.leisurekr.leisuresportskorea.profile.ReservationObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKEntityObjectDataType2;
import com.leisurekr.leisuresportskorea.ticket.TicketObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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



    public static ArrayList<CartObject> cartJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<CartObject> objects = null;

        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_CART_INFO_ALL_SELECT)
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
                    objects = OkHttpJSONDataParseHandler.getJSONCartList(jsonObject);
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

    public static String bookJSONInsert(CartObject... cartObjects) {

        boolean flag;
        String bloodInsertResultValue = "";
        CartObject reqParams = cartObjects[0];
        Response response = null;
        OkHttpClient toServer;


        try{
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 바디부분을 Form세팅 url에 포함되지 않는 자료
            RequestBody postBody = new FormBody.Builder()
                    .add("adult", Integer.toString(reqParams.getAdult()))
                    .add("child", Integer.toString(reqParams.getChildren()))
                    .add("status", Integer.toString(1))
                    .add("userId", Integer.toString(1))
                    .add("programId", Integer.toString(3))
                    .add("date", reqParams.getDate())
                    .add("time", reqParams.getTime())
                    .build();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_BOOK_INSERT)
                    .post(postBody)
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if( flag ){ //성공했다면
                returedJSON = response.body().string();
                Log.e("resultJSON", returedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    bloodInsertResultValue = jsonObject.optString("result");
                }catch(JSONException jsone){
                    Log.e("json에러", jsone.toString());
                }
            }else{
                //요청에러 발생시(http 에러)
            }

        }catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally{
            if(response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return bloodInsertResultValue;
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
