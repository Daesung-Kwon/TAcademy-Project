package com.leisurekr.leisuresportskorea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

/**
 * Created by user on 2017-06-14.
 */

public class AsyncCheckoutInsert extends AsyncTask<CheckoutObject, Integer, String> {

    int type;
    Context context;

    public AsyncCheckoutInsert(Context context, int type){
        this.context = context;
        this.type = type;
    }

    private ProgressDialog progressDialog;
    @Override

    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,
                "서버입력중","잠시만 기다려 주세요 ...", true);
    }
    @Override
    protected String doInBackground(CheckoutObject... checkoutObjects) {
        return OkHttpAPIHelperHandler.checkoutJSONInsert(type,checkoutObjects);
    }
    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        /*if( result != null){
            if( result.equalsIgnoreCase("OK")){
                showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_OK, null);
            }else{
                showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL,null);
            }
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("message", "입력 중 문제 발생[디버깅]!");
            showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL,bundle);
        }*/
    }
}

