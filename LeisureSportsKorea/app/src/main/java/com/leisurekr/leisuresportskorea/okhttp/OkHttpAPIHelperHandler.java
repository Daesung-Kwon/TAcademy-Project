package com.leisurekr.leisuresportskorea.okhttp;

import android.util.Log;

import com.leisurekr.leisuresportskorea.CheckoutObject;
import com.leisurekr.leisuresportskorea.FavorObject;
import com.leisurekr.leisuresportskorea.SearchObject;
import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.home.HomeObject;
import com.leisurekr.leisuresportskorea.profile.CartObject;
import com.leisurekr.leisuresportskorea.profile.FavoritesObject;
import com.leisurekr.leisuresportskorea.profile.ProfileObject;
import com.leisurekr.leisuresportskorea.profile.ReservationObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopInfoObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopListObject;
import com.leisurekr.leisuresportskorea.shop_detail.LKShopReviewsObject;
import com.leisurekr.leisuresportskorea.shop_detail.ParentData;
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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON1", returnedJSON);

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
                Log.e("Handler 성공", "getJSONHomeList Handler 성공");

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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON1", returnedJSON);

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
                Log.e("Handler 성공", "getJSONReservationList Handler 성공");
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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON1", returnedJSON);

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
                Log.e("Handler 성공", "getJSONTicketList Handler 성공");
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

    public static ProfileObject profileJSONAllSelect() {
        OkHttpClient toServer = null;
        ProfileObject objects = null;

        boolean flag;
        Response response = null;
        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_PROFILE_ALL_SELECT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();
            response = toServer.newCall(request).execute();
            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;
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
                    objects = OkHttpJSONDataParseHandler.getJSONProfile(jsonObject);
                else {
                    //실패하면 뭔가를 한다.
                }
                Log.e("Handler 성공", "profile select Handler 성공");
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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            Log.e("test token id",NetworkDefineConstant.AUTH_TOKEN);

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;
            JSONObject msg = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON1", returnedJSON);

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

                Log.e("Handler 성공", "cart select Handler 성공");
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
        String bookInsertResultValue = "";
        CartObject reqParams = cartObjects[0];
        Response response = null;
        OkHttpClient toServer;


        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 바디부분을 Form세팅 url에 포함되지 않는 자료
            RequestBody postBody = new FormBody.Builder()
                    .add("adult", Integer.toString(reqParams.getAdult()))
                    .add("child", Integer.toString(reqParams.getChildren()))
                    .add("status", Integer.toString(1))
                    .add("programId", Integer.toString(reqParams.getProgramObject().getId()))
                    .add("date", reqParams.getDate())
                    .add("time", reqParams.getTime())
                    .build();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_BOOK_INSERT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .post(postBody)
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                Log.e("resultJSON", returedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    bookInsertResultValue = jsonObject.optString("result");
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
            } else {
                //요청에러 발생시(http 에러)
                Log.e("toCart", "flag = false");
            }

        } catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return bookInsertResultValue;
    }

    public static String editJSONInsert(ProfileObject... profileObjects) {

        boolean flag;
        String editInsertResultValue = "";
        ProfileObject reqParams = profileObjects[0];
        Response response = null;
        OkHttpClient toServer;


        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 바디부분을 Form세팅 url에 포함되지 않는 자료
            RequestBody postBody = new FormBody.Builder()
                    .add("username", reqParams.getName())
                    .add("nationality", reqParams.getNationality())
                    .add("age", Integer.toString(reqParams.getAge()))
                    .add("sex", reqParams.getSex())
                    .build();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_EDIT_INSERT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .put(postBody)
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                //Log.e("resultJSON", returedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    editInsertResultValue = jsonObject.optString("msg");
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
            } else {
                //요청에러 발생시(http 에러)
                Log.e("toCart", "flag = false");
            }

        } catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return editInsertResultValue;
    }

    public static String favorJSONInsert(FavorObject... favorObjects) {

        Log.e("heart","중");
        boolean flag;
        String favorResultValue = "";
        FavorObject reqParams = favorObjects[0];
        Response response = null;
        OkHttpClient toServer;
        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 바디부분을 Form세팅 url에 포함되지 않는 자료
            RequestBody postBody = new FormBody.Builder()
                    .add("userId", Integer.toString(reqParams.getUserId()))
                    .add("shopId", Integer.toString(reqParams.getShopId()))
                    .build();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_FAVOR_POST)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .post(postBody)
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                Log.e("favor resultJSON", returedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    favorResultValue = jsonObject.optString("msg");
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
            } else {
                //요청에러 발생시(http 에러)
                Log.e("Favor", "flag = false");
            }

        } catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return favorResultValue;
    }

    public static String checkoutJSONInsert(int status,CheckoutObject... checkoutObjects) {

        boolean flag;
        String checkoutInsertResultValue = "";
        CheckoutObject reqParams = checkoutObjects[0];
        Log.e("checkoutJSONinsert", Integer.toString(reqParams.getBookid()));
        Response response = null;
        OkHttpClient toServer;


        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 바디부분을 Form세팅 url에 포함되지 않는 자료
            RequestBody postBody=null;
            Request request = null;
            switch (status) {
                case 2:
                postBody = new FormBody.Builder()
                        .add("name", reqParams.getName())
                        .add("pNum", reqParams.getPhoneNum())
                        .add("email", reqParams.getEmail())
                        .add("date", reqParams.getDate())
                        .add("time", reqParams.getTime())
                        .add("adult", Integer.toString(reqParams.getAdult()))
                        .add("child", Integer.toString(reqParams.getChild()))
                        .add("status", Integer.toString(status))
                        .add("userId", Integer.toString(1))
                        .add("programId", Integer.toString(reqParams.getProgramId()))
                        .build();
                    request = new Request.Builder()
                            .url(NetworkDefineConstant.SERVER_URL_RESERVATION_DELEDTE)
                            .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                            .post(postBody)
                            .build();

                    break;
                case 4:
                    postBody = new FormBody.Builder()
                            .add("date", reqParams.getDate())
                            .add("time", reqParams.getTime())
                            .add("adult", Integer.toString(reqParams.getAdult()))
                            .add("child", Integer.toString(reqParams.getChild()))
                            .add("status", Integer.toString(status))
                            .build();
                    request = new Request.Builder()
                            .url(NetworkDefineConstant.SERVER_URL_RESERVATION_DELEDTE+"/"+reqParams.getBookid())
                            .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                            .put(postBody)
                            .build();
                    break;
            }
            //요청 세팅(form(Query String) 방식의 포스트)

            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                Log.e("resultJSON", returedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    checkoutInsertResultValue = jsonObject.optString("result");
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
            } else {
                //요청에러 발생시(http 에러)
                Log.e("checkoutJSONInsert", "flag = false  "+status);
            }

        } catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return checkoutInsertResultValue;
    }

    public static String cartJSONDelete(CartObject... cartObjects) {

        boolean flag;
        CartObject reqParams = cartObjects[0];
        Log.e("searchJSONInsert()", " "+reqParams.getId());
        Response response = null;
        OkHttpClient toServer;
        String cartDeleteResultValue = null;

        try {
            //shopListEntityObjects = new ArrayList<LKShopListObject>();
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_CART_DELETE
                            + reqParams.getId())
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .delete()
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                Log.e("resultJSON", returedJSON);
                Log.e("cartDelete", "flag = true");
                try {
                    JSONObject jsonObject = new JSONObject(returedJSON);
                    cartDeleteResultValue = jsonObject.optString("result");
                    Log.e("cartDelete", cartDeleteResultValue);
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
            } else {
                //요청에러 발생시(http 에러)
            }

        } catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return cartDeleteResultValue;
    }

    public static ArrayList<LKShopListObject> searchJSONInsert(SearchObject... searchObjects) {

        boolean flag;
        SearchObject reqParams = searchObjects[0];
        Log.i("searchJSONInsert()", reqParams.getDate());
        Response response = null;
        OkHttpClient toServer;
        ArrayList<LKShopListObject> shopListEntityObjects = null;


        try {
            //shopListEntityObjects = new ArrayList<LKShopListObject>();
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SEARCH_INSERT
                            + "date=" + reqParams.getDate() +"&loc=강원도" + "&adult=" + reqParams.getAdult()
                            + "&child=" + reqParams.getChildren())
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();
            //동기 방식 실제 연결이 되고 요청이 이루어지는 부분
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returedJSON;
            JSONObject jsonObject = null;
            if (flag) { //성공했다면
                returedJSON = response.body().string();
                Log.i("resultJSON", returedJSON);
                try {
                    jsonObject = new JSONObject(returedJSON);
                    if (jsonObject == null) {
                        Log.i("searchJSONInsert()", "jsonObject==null");
                    }
                    if (OkHttpJSONDataParseHandler.getJSONShopList(jsonObject) != null) {
                        Log.i("searchJSONInsert()", "OkHttpJSONDataParseHandler.getJSONShopList(jsonObject)==null");
                    }
                    Log.i("resultJSON", "파싱 성공");
                } catch (JSONException jsone) {
                    Log.e("json에러", jsone.toString());
                }
                shopListEntityObjects = OkHttpJSONDataParseHandler.getJSONShopList(jsonObject);
            } else {
                //요청에러 발생시(http 에러)
                Log.i("searchJSONInsert()", "요청 에러");
            }
        } catch (UnknownHostException une) {
            Log.i("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            Log.i("bbb", uee.toString());
        } catch (Exception e) {
            Log.i("ccc", e.toString());
        } finally {
            if (response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }

        if (shopListEntityObjects == null) {
            Log.i("searchJSONInsert()", "shopListEntityObjects==null");
        }
        return shopListEntityObjects;
    }


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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("Response.body()", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);


                    Log.e("resultJSON2", jsonObject.toString());
                } catch (JSONException jsonE) {

                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                parentDatas = OkHttpJSONDataParseHandler.getJSONShopProgram(jsonObject);
                Log.e("Handler 성공", "getJSONShopProgram Handler 성공");

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

        return parentDatas;
    }

    /**
     * 샵의 상세 정보
     *
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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;
            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("Response.body()", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                    Log.e("resultJSON2", jsonObject.toString());
                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                lkTestEntityObjects = OkHttpJSONDataParseHandler.getJSONShopInfo(jsonObject);
                Log.e("Handler 성공", "getJSONShopInfo Handler 성공");

            } else {
                //요청에러 발생시(http 에러)
                Log.e("Handler 오류", "Flag is NULL");

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

    public static ArrayList<FavoritesObject> favorListJSONAllSelect() {
        OkHttpClient toServer = null;
        ArrayList<FavoritesObject> shopListEntityObjects = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_LIKE_ALL_SELECT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;

            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                shopListEntityObjects = OkHttpJSONDataParseHandler.getJSONFavor(jsonArray);
                Log.e("Handler 성공", "getJSONShopList Handler 성공");

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
        return shopListEntityObjects;
    }

    /**
     * 샵 리스트
     *
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
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;

            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                shopListEntityObjects = OkHttpJSONDataParseHandler.getJSONShopList(jsonObject);
                Log.e("Handler 성공", "getJSONShopList Handler 성공");

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
        return shopListEntityObjects;
    }

    /**
     * 관심사 서버 저장
     * @return
     */
    public static String interestsJSONAllInsert(int[] data) {
        boolean flag;
        String insertResultValue = "";
        int[] reqParams = data;
        Log.e("reqParams", String.valueOf(reqParams.length));
        Response response = null;
        OkHttpClient toServer;

        try{
            toServer = OkHttpInitSingletonManager.getOkHttpClient();
            //요청 Body Form 세팅
            RequestBody postBody = requestBodyParseData(reqParams);
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_INTERESTS_INSERT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .post(postBody)
                    .build();
            //동기 방식
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();
            String returnedJSON;
            if( flag ){ //성공했다면
                returnedJSON = response.body().string();
                //Log.e("interests resultJSON", returnedJSON);
                try {
                    JSONObject jsonObject = new JSONObject(returnedJSON);
                    insertResultValue = jsonObject.optString("msg");
                    Log.e("Handler 성공", "getJSONShopList Handler 성공");
                }catch(JSONException jsone){
                    Log.e("interests json에러", jsone.toString());
                }
            }else{
                //요청에러 발생시(http 에러)
                Log.e("interests resultJSON", "Flag is NULL");
            }

        }catch (UnknownHostException une) {
            e("aaa", une.toString());
        } catch (UnsupportedEncodingException uee) {
            e("bbb", uee.toString());
        } catch (Exception e) {
            e("ccc", e.toString());
        } finally{ /** TODO : Very Important!!! **/
            if(response != null) {
                response.close(); //3.* 이상에서는 반드시 닫아 준다.
            }
        }
        return insertResultValue;
    }

    public static RequestBody requestBodyParseData(int[] data) {
        int[] parsedData = getParsingData(data);
        int length = 0;
        for(int i=0;i<parsedData.length;i++){
            if(parsedData[i]!=0){
                length++;
            }
        }
        switch (length) {
            case 1: {
                return new FormBody.Builder()
                        .add("userId", String.valueOf(2))
                        .add("activityId", String.valueOf(parsedData[0]))
                        .build();
            }
            case 2:
                return new FormBody.Builder()
                        .add("userId", String.valueOf(2))
                        .add("activityId", String.valueOf(parsedData[0]))
                        .add("activityId", String.valueOf(parsedData[1]))
                        .build();
            case 3:
                return new FormBody.Builder()
                        .add("userId", String.valueOf(2))
                        .add("activityId", String.valueOf(parsedData[0]))
                        .add("activityId", String.valueOf(parsedData[1]))
                        .add("activityId", String.valueOf(parsedData[2]))
                        .build();
            case 4:
                return new FormBody.Builder()
                        .add("userId", String.valueOf(2))
                        .add("activityId", String.valueOf(parsedData[0]))
                        .add("activityId", String.valueOf(parsedData[1]))
                        .add("activityId", String.valueOf(parsedData[2]))
                        .add("activityId", String.valueOf(parsedData[3]))
                        .build();
        }
        return null;
    }

    public static int[] getParsingData(int[] rawData) {
        int[] parsed = {0,0,0,0,0,0,0,0,0,0,0,0};

        int index = 0;
        for (int i = 0; i < rawData.length; i++) {
            if (rawData[i] == 1) {
                parsed[index] = i+1;
                index++;
            }
        }
        return parsed;
    }

    /**
     * 샵 리스트
     *
     * @return
     */
    public static String facebookUserInfoJSONInsert(ArrayList<String> arrayData) {
        OkHttpClient toServer = null;
        String userLoginToken = null;
        boolean flag;
        Response response = null;

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            //요청 Body Form 세팅
            RequestBody postBody = new FormBody.Builder()
                    .add("socialId", arrayData.get(0))
                    .add("email", arrayData.get(1))
                    .add("username", arrayData.get(2))
                    .add("nationality", arrayData.get(3))
                    .add("sex", arrayData.get(4))
                    .add("age", arrayData.get(5))
                    .build();
            //요청 세팅(form(Query String) 방식의 포스트)
            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_FACEBOOK_USER_INFO_INSERT)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .post(postBody)
                    .build();
            //동기 방식
            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;

            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                Log.e("resultJSON for Token", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                userLoginToken = OkHttpJSONDataParseHandler.getUserLoginToken(jsonObject);
                Log.e("Handler 성공", "facebookUserInfoJSONInsert Handler 성공");

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
        return userLoginToken;
    }

    /**
     * 샵 리스트
     *
     * @return
     */
    public static ArrayList<LKShopReviewsObject> shopReviewJSONAllSelect(int shopId) {
        OkHttpClient toServer = null;
        ArrayList<LKShopReviewsObject> shopReviewEntityObjects = null;
        boolean flag;
        Response response = null;
        String mShopIDURL = "/" + String.valueOf(shopId);

        try {
            toServer = OkHttpInitSingletonManager.getOkHttpClient();

            Request request = new Request.Builder()
                    .url(NetworkDefineConstant.SERVER_URL_SHOP_REVIEW_ALL_SELECT + mShopIDURL)
                    .addHeader(NetworkDefineConstant.AUTHORIZATION,NetworkDefineConstant.AUTH_TOKEN)
                    .build();

            response = toServer.newCall(request).execute();

            flag = response.isSuccessful();

            String returnedJSON;
            JSONObject jsonObject = null;

            if (flag) { //성공했다면
                returnedJSON = response.body().string();
                //Log.e("resultJSON", returnedJSON);

                try {
                    jsonObject = new JSONObject(returnedJSON);

                } catch (JSONException jsonE) {
                    Log.e("json에러", jsonE.toString());
                }
                // Parser가 들어간다.
                shopReviewEntityObjects = OkHttpJSONDataParseHandler.getJSONShopReviews(jsonObject);
                Log.e("Handler 성공", "getJSONShopList Handler 성공");

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
        return shopReviewEntityObjects;
    }
}
