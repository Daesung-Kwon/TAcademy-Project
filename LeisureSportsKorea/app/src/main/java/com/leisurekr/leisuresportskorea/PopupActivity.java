package com.leisurekr.leisuresportskorea;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;
import com.leisurekr.leisuresportskorea.shop.FilterImageAdapter;
import com.leisurekr.leisuresportskorea.shop_detail.ParentData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.leisurekr.leisuresportskorea.common.SharedPreferencesDefineConstant.PREF_NAME;

/**
 * Created by mobile on 2017. 6. 14..
 */

public class PopupActivity extends Activity implements View.OnClickListener {
    SharedPreferences mPrefs; // 키-값 형태로 데이터를 저장하는 구조
    SharedPreferences.Editor mEditor; // 프리퍼런스 편집(CRUD) 에딧터

    Display display;

    private GridView gridView;
    private static FilterImageAdapter gridAdapter;
    private TextView saveBtn;
    private TextView skipBtn;

    static Integer[] interestsValues = new Integer[] {
            0, 0, 0, 0,
            0, 0, 0, 0,
            0, 0, 0, 1
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setFinishOnTouchOutside(false);
        setContentView(R.layout.pre_select_interests);

        mPrefs = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();

        display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        setupActivityDisplaySize(0.9, 0.8);

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

    public void setupActivityDisplaySize(double resizeWidth, double resizeHeight) {
        int width = (int)(display.getWidth() * resizeWidth);
        int height = (int)(display.getHeight() * resizeHeight);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.skip_btn_on_popup_activity:
                onBackPressed();
                break;
            case R.id.save_button_on_popup_activity:
                // 최초 세팅 Interests 값 저장.
                setChoiceInterestsPref(true);
                new AsyncInterestsInsert().execute();
                finish();
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

    public static class AsyncInterestsInsert
            extends AsyncTask<String, Integer, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog = ProgressDialog.show(LKApplication.getLKApplication(), "", "Loading...", true);
        }

        @Override
        protected String doInBackground(String... params) {
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
}
