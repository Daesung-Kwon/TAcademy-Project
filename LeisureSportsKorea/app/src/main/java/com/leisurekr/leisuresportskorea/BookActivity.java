package com.leisurekr.leisuresportskorea;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;
import com.leisurekr.leisuresportskorea.profile.CartObject;
import com.leisurekr.leisuresportskorea.profile.ProfileCartActivity;
import com.leisurekr.leisuresportskorea.profile.ProgramObject;
import com.leisurekr.leisuresportskorea.profile.ShopObject;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    ImageView activityImage;
    TextView title1;
    TextView title2;
    TextView title3;
    TextView price;

    LinearLayout datePopupBtn;
    TextView date;
    String dateString;
    LinearLayout timePopupBtn;
    TextView time;

    TextView currentAdult;
    ImageView subAdult;
    TextView currentNumberAdult;
    ImageView addAdult;

    TextView currentChildren;
    ImageView subChildren;
    TextView currentNumberChildren;
    ImageView addChildren;

    TextView totalPrice;

    Button addToCartBtn;
    Button checkOutBtn;

    DatePicker datePicker;

    int adult = 1;
    int children = 0;
    int adultPrice = 0;
    int childrenPrice = 0;
    int total = 0;
    BookObject object;

    int programId;
    String programName;
    String programImage;
    String activityName;
    String shopName;
    String shopImage;
    int shopId;
    int number;

    int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        /**
         * "programId", "programName", "adultPrice", "childPrice", "programImage", "activityName"
         "shopName", "shopImage", "shopId","number"*/
        Intent intent = getIntent();
        programId = intent.getIntExtra("programId", -1);
        programName = intent.getStringExtra("programName");
        adultPrice = intent.getIntExtra("adultPrice",0);
        childrenPrice = intent.getIntExtra("childPrice",0);
        programImage = intent.getStringExtra("programImage");
        activityName = intent.getStringExtra("activityName");
        shopName = intent.getStringExtra("shopName");
        shopImage = intent.getStringExtra("shopImage");
        shopId = intent.getIntExtra("shopId",0);
        number = intent.getIntExtra("number",0);
        groupId = intent.getIntExtra("groupId",0);
        Log.e("groupId",R.id.individual_program_elv+" / "+groupId);
        object = new BookObject();
        String s;//action이름앞에 숫자를 위한 스트링 0~9까지는 00. 01. 02. 로 만든고 10부터는 10. 11. 12 로 만듬
        if(number<10){
            s= "0"+number;
        }else{
            s = Integer.toString(number);
        }
        String a;//프로그램 이름에 액티비티 이름을 분리하는 경우 Fun Boat만 s와 공백의 추가로 붙기에 +2을 한다
                    //Fun Boat가 아닌경우에는 공백만 제거하기 위해 +1을 한다.
                    //activityName이 null인 경우는 관심사에 포함되지 않은 개별 종목이 오는경우 이름을 분리하지 않는다.
                    //
        if(activityName!=null) {
            if(groupId!=R.id.individual_program_elv) {
                if (activityName.equals("Fun Boat")) {
                    a = programName.substring(activityName.length() + 2);
                } else {
                    a = programName.substring(activityName.length() + 1);
                }
            }else{
                a = programName;
            }
        }else{
            a = programName;
        }
        object.setData(programImage
                , s+". "+activityName
                , a
                , ""
                , adultPrice, 1, adultPrice, 0, childrenPrice, 0);

        toolbar = (Toolbar) findViewById(R.id.book_toolbar);
        toolbar.setTitle("Book");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityImage = (ImageView) findViewById(R.id.book_activityimage);
        title1 = (TextView) findViewById(R.id.book_text1);
        title2 = (TextView) findViewById(R.id.book_text2);
        title3 = (TextView) findViewById(R.id.book_text3);
        price = (TextView) findViewById(R.id.book_price);

        datePopupBtn = (LinearLayout) findViewById(R.id.book_datepopup);
        date = (TextView) findViewById(R.id.book_date);
        timePopupBtn = (LinearLayout) findViewById(R.id.book_timepopup);
        time = (TextView) findViewById(R.id.book_time);

        currentAdult = (TextView) findViewById(R.id.book_currentadult);
        subAdult = (ImageView) findViewById(R.id.book_subadult);
        currentNumberAdult = (TextView) findViewById(R.id.book_currentnumber_adult);
        addAdult = (ImageView) findViewById(R.id.book_addadult);

        currentChildren = (TextView) findViewById(R.id.book_currentchildren);
        subChildren = (ImageView) findViewById(R.id.book_subchildren);
        currentNumberChildren = (TextView) findViewById(R.id.book_currentnumber_children);
        addChildren = (ImageView) findViewById(R.id.book_addchildren);

        totalPrice = (TextView) findViewById(R.id.book_totalprice);

        addToCartBtn = (Button) findViewById(R.id.book_addtocart);
        checkOutBtn = (Button) findViewById(R.id.book_checkout);


        Glide.with(BookActivity.this).load(object.getActivityImage())
                .into(activityImage);
        title1.setText(object.getActivityName());
        title2.setText(object.getText1());
        title3.setText(object.getText2());
        price.setText("$" + object.getPrice());

        adultPrice = object.getAdultPrice();
        childrenPrice = object.getChildrenPrice();

        currentAdult.setText("Adult 1 $" + adultPrice);
        currentChildren.setText("");

        total = adultPrice;
        totalPrice.setText("$" + total);

        subAdult.setOnClickListener(this);
        addAdult.setOnClickListener(this);
        subChildren.setOnClickListener(this);
        addChildren.setOnClickListener(this);
        datePopupBtn.setOnClickListener(this);
        timePopupBtn.setOnClickListener(this);
        addToCartBtn.setOnClickListener(this);
        checkOutBtn.setOnClickListener(this);

    }

    ArrayList<CartObject> arrayList;
    CartObject cartObject;
    ProgramObject programObject;
    ShopObject shopObject;

    @Override
    public void onClick(View v) {
        String s = "";
        switch (v.getId()) {
            case R.id.book_addadult:
                adult++;
                if (adult == 0)
                    s = "";
                else
                    s = "Adult " + adult + " $" + (adult * adultPrice);

                currentAdult.setText(s);
                currentNumberAdult.setText(Integer.toString(adult));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_subadult:
                adult--;
                if (adult < 0) {
                    adult = 0;
                }
                if (adult == 0)
                    s = "";
                else
                    s = "Adult " + adult + " $" + (adult * adultPrice);
                currentAdult.setText(s);
                currentNumberAdult.setText(Integer.toString(adult));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_addchildren:
                children++;
                if (children == 0)
                    s = "";
                else
                    s = "Children " + children + " $" + (children * childrenPrice);
                currentChildren.setText(s);
                currentNumberChildren.setText(Integer.toString(children));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_subchildren:
                children--;
                if (children < 0) {
                    children = 0;
                }
                if (children == 0)
                    s = "";
                else
                    s = "Children " + children + " $" + (children * childrenPrice);
                currentChildren.setText(s);
                currentNumberChildren.setText(Integer.toString(children));
                totalPrice.setText("$"+((adult * adultPrice)+(children * childrenPrice)));
                break;
            case R.id.book_datepopup:
                CalenderDialog cd = new CalenderDialog(BookActivity.this);
                cd.setDate(date,dateString);
                cd.show();
                break;
            case R.id.book_timepopup:
                TimeDialog td = new TimeDialog(BookActivity.this);
                td.setDate(time);
                td.show();
                break;
            case R.id.book_addtocart:
                cartObject = new CartObject();
                programObject = new ProgramObject();
                shopObject = new ShopObject();
                cartObject.setDate(date.getText().toString());
                cartObject.setTime(time.getText().toString());
                cartObject.setProgramObject(programObject);
                cartObject.setAdult(adult);
                cartObject.setChildren(children);
                cartObject.setAdultPrice(adultPrice);
                cartObject.setChildrenPrice(childrenPrice);
                programObject.setId(programId);
                Log.e("programId", " "+programId);
                if(date.getText()==null||date.getText().toString().equals("Select date of use")){
                    date.requestFocus();
                    Toast.makeText(BookActivity.this, "Please select date of use"
                            , Toast.LENGTH_SHORT).show();
                }else if(time.getText()==null||time.getText().toString().equals("Select time of use")) {
                    time.requestFocus();
                    Toast.makeText(BookActivity.this, "Please select time of use"
                            , Toast.LENGTH_SHORT).show();
                }else if(adult == 0 && children == 0){
                    Toast.makeText(BookActivity.this, "Please select number of people more than 1"
                            , Toast.LENGTH_SHORT).show();
                }else{
                    new AsyncBookInsert().execute(cartObject);

                    //아래 세줄을 어싱크가 onPostExecute()에서 할지 생각해보자.
                    Intent toCartIntent = new Intent(BookActivity.this, ProfileCartActivity.class);
                    startActivity(toCartIntent);
                    finish();
                }
                break;

            case R.id.book_checkout:
                arrayList = new ArrayList<>();
                cartObject = new CartObject();
                programObject = new ProgramObject();
                shopObject = new ShopObject();
                cartObject.setDate(date.getText().toString());
                cartObject.setTime(time.getText().toString());
                cartObject.setProgramObject(programObject);
                cartObject.setAdult(adult);
                cartObject.setChildren(children);
                cartObject.setAdultPrice(adultPrice);
                cartObject.setChildrenPrice(childrenPrice);
                programObject.setName(programName);
                programObject.setId(programId);
                programObject.setPrice(total);
                programObject.setShopObject(shopObject);
                programObject.setActivityName(activityName);
                programObject.setImage(programImage);
                shopObject.setName(shopName);
                shopObject.setId(shopId);
                shopObject.setImage(shopImage);
                shopObject.setLocation1("");
                shopObject.setLocation2("");
                shopObject.setLocation3("");
                if(date.getText()==null||date.getText().toString().equals("Select date of use")){
                    Toast.makeText(BookActivity.this, "Please select date of use"
                            , Toast.LENGTH_SHORT).show();
                }else if(time.getText()==null||time.getText().toString().equals("Select time of use")) {
                    Toast.makeText(BookActivity.this, "Please select time of use"
                            , Toast.LENGTH_SHORT).show();
                }else if(adult == 0 && children == 0){
                    Toast.makeText(BookActivity.this, "Please select number of people more than 1"
                            , Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(BookActivity.this, BookInformationActivity.class);
                    arrayList.add(cartObject);
                    intent.putExtra("check out", arrayList);
                    startActivity(intent);
                    finish();
                }

                break;

        }

    }

    public class AsyncBookInsert extends AsyncTask<CartObject , Integer, String>{

        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(BookActivity.this,
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
                if( result.equalsIgnoreCase("OK")){
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
