package com.leisurekr.leisuresportskorea.common;

import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;

/**
 * Created by mobile on 2017. 6. 7..
 */

public class NetworkDefineConstant {
    public static final String HOST_URL = "http://52.79.102.193:3000";
    public static final String REQUEST_GET = "GET";
    public static final String REQUEST_POST = "POST";


    //요청 URL path
    public static String SERVER_URL_SHOP_ALL_SELECT;
    public static String SERVER_URL_SHOP_FILTERED_SELECT;
    public static String SERVER_URL_SHOP_REVIEW_ALL_SELECT;
    public static String SERVER_URL_HOME_INFO_ALL_SELECT;
    public static String SERVER_URL_RESERVATION_INFO_ALL_SELECT;
    public static String SERVER_URL_TICKET_INFO_ALL_SELECT;
    public static String SERVER_URL_CART_INFO_ALL_SELECT;
    public static String SERVER_URL_SHOP_DETAIL_INFO_ALL_SELECT;
    public static String SERVER_URL_SHOP_DETAIL_PROGRAM_ALL_SELECT;
    public static String SERVER_URL_BOOK_INSERT;
    public static String SERVER_URL_INTERESTS_INSERT;
    public static String SERVER_URL_LIKE_ALL_SELECT;
    public static String SERVER_URL_EDIT_INSERT;
    public static String SERVER_URL_CHECKOUT_INSERT;
    public static String SERVER_URL_RESERVATION_DELEDTE;
    public static String SERVER_URL_SEARCH_INSERT;
    public static String SERVER_URL_CART_DELETE;
    public static String SERVER_URL_PROFILE_ALL_SELECT;
    public static String SERVER_URL_REVIEW_INSERT;
    public static String SERVER_URL_FAVOR_POST;
    public static String SERVER_URL_FACEBOOK_USER_INFO_INSERT;
    public static String AUTH_TOKEN ;
    public static String SERVER_URL_PAYPAL_CHECKOUT_INFO;
    public static String AUTHORIZATION = "authorization";


    static {
        SERVER_URL_SHOP_ALL_SELECT =
                HOST_URL + "/shops";
        SERVER_URL_SHOP_FILTERED_SELECT =
                HOST_URL + "/shops?";
        SERVER_URL_SHOP_REVIEW_ALL_SELECT =
                HOST_URL + "/reviews";
        SERVER_URL_REVIEW_INSERT =
                HOST_URL + "/reviews";
        SERVER_URL_HOME_INFO_ALL_SELECT =
                HOST_URL + "/programs";
        SERVER_URL_RESERVATION_INFO_ALL_SELECT =
                HOST_URL + "/books?status=reservation";
        SERVER_URL_TICKET_INFO_ALL_SELECT =
                HOST_URL + "/books?status=ticket";
        SERVER_URL_CART_INFO_ALL_SELECT =
                HOST_URL + "/books?status=cart";
        SERVER_URL_CART_DELETE =
                HOST_URL + "/books/";
        SERVER_URL_SHOP_DETAIL_INFO_ALL_SELECT =
                HOST_URL + "/programs";
        SERVER_URL_SHOP_DETAIL_PROGRAM_ALL_SELECT =
                HOST_URL + "/";
        SERVER_URL_BOOK_INSERT =
                HOST_URL + "/books";
        SERVER_URL_CHECKOUT_INSERT =
                HOST_URL + "/books";
        SERVER_URL_RESERVATION_DELEDTE=
                HOST_URL + "/books";
        SERVER_URL_SEARCH_INSERT =
                HOST_URL + "/shops?";
        SERVER_URL_PROFILE_ALL_SELECT =
                HOST_URL + "/users";
        SERVER_URL_EDIT_INSERT=
                HOST_URL + "/users";
        SERVER_URL_INTERESTS_INSERT =
                HOST_URL + "/interests";
        SERVER_URL_FAVOR_POST=
                HOST_URL + "/likes";
        SERVER_URL_LIKE_ALL_SELECT=
                HOST_URL + "/likes";
        SERVER_URL_FACEBOOK_USER_INFO_INSERT=
                HOST_URL + "/login";
        AUTH_TOKEN = LKSharedPreferencesManager.getInstance().getKeyToken();
        SERVER_URL_PAYPAL_CHECKOUT_INFO=
                HOST_URL + "/paypal";

    }

    //다이얼로그 요청값
    public static final int LK_INSERT_DIALOG_OK = 1;
    public static final int LK_INSERT_DIALOG_FAIL = 2;
}
