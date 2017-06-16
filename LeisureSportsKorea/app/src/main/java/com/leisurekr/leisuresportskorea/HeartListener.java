package com.leisurekr.leisuresportskorea;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.profile.CartObject;

import static com.leisurekr.leisuresportskorea.okhttp.RequestResultDialog.showDialog;

/**
 * Created by user on 2017-06-15.
 */

public class HeartListener implements View.OnClickListener{

    Context context;
    public HeartListener(Context context){
        this.context = context;
    }
    @Override
    public void onClick(View v) {
        Log.e("HeatListener","clicked!!!");
        ImageView imageView = (ImageView) v;
        Log.e("HeatListener",Boolean.toString(imageView.isSelected()));
        if(imageView.isSelected()){
            imageView.setImageResource(R.drawable.btn_heart_unpress);
            imageView.setSelected(false);
        }else{
            imageView.setImageResource(R.drawable.btn_heart_press);
            imageView.setSelected(true);
        }
    }

    public class AsyncFavorInsert extends AsyncTask<CartObject , Integer, String> {

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(context,
                    "서버입력중","잠시만 기다려 주세요 ...", true);
        }
        @Override
        protected String doInBackground(CartObject... cartObjects) {
            return OkHttpAPIHelperHandler.bookJSONInsert(cartObjects);
        }
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if( result != null){
                if( result.equalsIgnoreCase("success")){
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_OK, null);
                }else{
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL,null);
                }
            }else{
                Bundle bundle = new Bundle();
                bundle.putString("message", "입력 중 문제 발생[디버깅]!");
                showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL,bundle);
            }
        }
    }
}
