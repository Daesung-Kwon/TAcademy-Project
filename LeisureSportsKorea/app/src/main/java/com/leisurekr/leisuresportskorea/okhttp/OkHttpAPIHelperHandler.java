package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopInfoObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.ParentData;

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
    public static ArrayList<ParentData> shopProgramJSONALLSelect(int shopId) {
        OkHttpClient toServer = null;
        ArrayList<ParentData> parentDatas = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SHOP_DETAIL_INFO_ALL_SELECT
                            + "/" + String.valueOf(shopId))
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;
            if( flag ){ //성공했다면
                returnedJSON = response.body().string();
                Log.e("Response.body()", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                    Log.e("resultJSON2", jsonObject.toString());
                }catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                parentDatas = OkHttpJSONDataParseHandler.getJSONShopProgram(jsonObject);

            }else{
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally{ /** TODO : Very Important!!! **/
            if(response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return parentDatas;
    }

    /**
     * 샵의 상세 정보
     * @param shopId
     * @return
     */
    public static LKShopInfoObject shopInfoJSONALLSelect(int shopId) {
        OkHttpClient toServer = null;
        LKShopInfoObject lkTestEntityObjects = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SHOP_ALL_SELECT
                    + "/" + String.valueOf(shopId))
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;
            if( flag ){ //성공했다면
                returnedJSON = response.body().string();
                Log.e("Response.body()", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                    Log.e("resultJSON2", jsonObject.toString());
                }catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                lkTestEntityObjects = OkHttpJSONDataParseHandler.getJSONShopInfo(jsonObject);

            }else{
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally{ /** TODO : Very Important!!! **/
            if(response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        return lkTestEntityObjects;
    }

    /**
     * 샵 리스트
     * @return
     */
    public static ArrayList<LKShopListObject> shopListJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<LKShopListObject> shopListEntityObjects = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SHOP_ALL_SELECT)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;

            if( flag ){ //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                }catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                shopListEntityObjects = OkHttpJSONDataParseHandler.getJSONShopList(jsonObject);
                //Log.i("After Parser", bloodEntityObjects.toString());

            }else{
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("une", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("uee", uee.toString());
        } catch (Exception e) {
            e("e", e.toString());
        } finally{ /** TODO : Very Important!!! **/
            if(response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return shopListEntityObjects;
    }
}
