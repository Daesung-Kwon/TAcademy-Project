package com.leisurekr.leisuresportskorea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;
import com.leisurekr.leisuresportskorea.shop.FilterImageAdapter;

import java.util.HashSet;
import java.util.Set;

import static com.leisurekr.leisuresportskorea.common.SharedPreferencesDefineConstant.PREF_NAME;

/**
 * Created by mobile on 2017. 6. 14..
 */

public class PreInterestsActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences mPrefs; // 키-값 형태로 데이터를 저장하는 구조
    SharedPreferences.Editor mEditor; // 프리퍼런스 편집(CRUD) 에딧터

    private GridView gridView;
    private static FilterImageAdapter gridAdapter;
    private TextView saveBtn;
    private TextView skipBtn;

    static int[] interestsValues = new int[] {
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 1
    };

    boolean isResult = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_select_interests);



        intent = getIntent();
        if(intent.getIntArrayExtra("init")!=null) {
            interestsValues = intent.getIntArrayExtra("init");
            isResult=true;
        }else
            isResult=false;


        mPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();

        gridView = (GridView) findViewById(R.id.filter_grid_view_on_popup_activity);
        skipBtn  = (TextView) findViewById(R.id.skip_btn_on_popup_activity);
        saveBtn  = (TextView) findViewById(R.id.save_button_on_popup_activity);

        gridAdapter = new FilterImageAdapter(this, interestsValues);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridAdapter.updateSelectedTag(position);
            }
        });

        skipBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip_btn_on_popup_activity:
                if(isResult==true){
                    setResult(1);
                    finish();
                }
                else{
                    Intent intent = new Intent(PreInterestsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            case R.id.save_button_on_popup_activity:
                if(isResult==true){
                    new AsyncInterestsInsert().execute();
                    intent.putExtra("result",interestsValues);
                    setResult(0,intent);
                    finish();
                }
                else{
                    // 최초 세팅 Interests 값 저장.
                    setChoiceInterestsPref(true);
                    new AsyncInterestsInsert().execute();

                    Intent intent = new Intent(PreInterestsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    public void setChoiceInterestsPref(boolean did) {
        LKSharedPreferencesManager.getInstance().setInterests(did);
    }

    public void setInterestsSetType() {
        Set<String> storeInterestsHash = new HashSet<>();
        int rows;
        for (rows = 0; rows < gridAdapter.tag.length; rows++) {
            if (gridAdapter.tag[rows] == 1) storeInterestsHash.add(String.valueOf(rows+1));
        }
        LKSharedPreferencesManager.getInstance().setInterestsSet(storeInterestsHash);
    }

    public class AsyncInterestsInsert
            extends AsyncTask<String, Integer, String> {

        //ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(LKApplication.getLKApplication(), "", "Loading...", true);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("get","preinter doinback");
            return OkHttpAPIHelperHandler.interestsJSONAllInsert(gridAdapter.tag);
        }
        @Override
        protected void onPostExecute(String result) {
            //dialog.dismiss();
            if (result.equals("success")) {
                Log.i("Interests Insert", "OK");
            }else {
                Log.i("Interests Insert", "FAIL");
            }

        }
    }

    @Override
    public void onBackPressed() {

    }
}
