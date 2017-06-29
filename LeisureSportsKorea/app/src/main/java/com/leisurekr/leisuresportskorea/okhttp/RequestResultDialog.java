package com.leisurekr.leisuresportskorea.okhttp;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;

/**
 * Created by user on 2017-05-23.
 */

public class RequestResultDialog extends Dialog {

    static Context  context;
    public RequestResultDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public static Dialog showDialog (int id, Bundle bundle) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("헌혈 요청 입력");
        Dialog bloodDialog = null;
        switch(id){
            case NetworkDefineConstant.LK_INSERT_DIALOG_OK:{
                alertDialog.setMessage(" 해당 정보를 입력하였습니다. ");
                alertDialog.setPositiveButton("확인", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });
                bloodDialog  = alertDialog.create();
                return bloodDialog;
            }
            case NetworkDefineConstant.LK_INSERT_DIALOG_FAIL:{
                if( bundle != null){
                    alertDialog.setMessage(bundle.getString("message"));
                }else{
                    alertDialog.setMessage(" 입력 실패 ");
                }
                alertDialog.setPositiveButton("확인", new OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                bloodDialog  = alertDialog.create();
                return bloodDialog;
            }
        }
        return null;
    }
}
