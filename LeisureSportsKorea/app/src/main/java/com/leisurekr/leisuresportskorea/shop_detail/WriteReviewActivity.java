package com.leisurekr.leisuresportskorea.shop_detail;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.sharedPreferences.LKSharedPreferencesManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by mobile on 2017. 6. 1..
 */

public class WriteReviewActivity extends AppCompatActivity {
    Toolbar toolbar;

    private RatingBar ratingBar;
    private TextView changedRatingValue;
    float rate;
    int shopId;

    EditText review;
    String reviewString;
    ImageView addImage;
    private static final int PICK_FROM_GALLERY = 100;

    ImageView showImage;
    private String fileLocation;
    File myImageDir; //카메라로 찍은 사진을 저장할 디렉토리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_rating);

        Intent intent = getIntent();
        shopId = intent.getIntExtra("id", -1);

        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.write_review_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ratingBar = (RatingBar) findViewById(R.id.write_rating_bar);
        changedRatingValue = (TextView) findViewById(R.id.input_rating);
        addListenerOnRatingBar();

        showImage = (ImageView) findViewById(R.id.input_review_detail);

        review = (EditText) findViewById(R.id.input_review_title);
        addImage = (ImageView) findViewById(R.id.title_icon);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, PICK_FROM_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri returedImgURI;
        if (resultCode != Activity.RESULT_OK || resultCode == Activity.RESULT_CANCELED) {
            return;
        }
        switch (requestCode) {
            case PICK_FROM_GALLERY: {
                returedImgURI = data.getData();
                if (returedImgURI != null) {//가끔 단말기의 경우 uri로 리턴하지 않을때가 있다. else문 실행
                    showImage.setImageURI(returedImgURI);
                    Log.e("image경로",returedImgURI.getEncodedPath().toString());

                    //업로드 할 수 있도록 절대 주소를 알아낸다.
                    if (findImageFileNameFromUri(returedImgURI)) {
                        Log.e("review", " 갤러리에서 절대주소 Pick 성공");
                    } else {
                        Log.e("review", " 갤러리에서 절대주소 Pick 실패");
                    }
                } else {//uri가 없어서 실제 이미지를 가져옴
                    Bundle extras = data.getExtras();
                    Bitmap returedBitmap = (Bitmap) extras.get("data");
                    if (tempSavedBitmapFile(returedBitmap)) {
                        Log.e("review", "갤러리에서 Uri값이 없어 실제 파일로 저장 성공");
                    } else {
                        Log.e("review", "갤러리에서 Uri값이 없어 실제 파일로 저장 실패");
                    }
                }
                //crop이 필요하면 여기서 다시 호출
                // cropUpLoadImageIntent(currentSelectedUri);
                break;
            }
        }
    }

    private boolean findImageFileNameFromUri(Uri tempUri) {
        boolean flag = false;

        //실제 Image Uri의 절대이름
        String[] IMAGE_DB_COLUMN = {MediaStore.Images.ImageColumns.DATA};
        Cursor cursor = null;
        try {
            //Primary Key값을 추출
            String imagePK = String.valueOf(ContentUris.parseId(tempUri));
            //Image DB에 쿼리를 날린다.
            cursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_DB_COLUMN,
                    MediaStore.Images.Media._ID + "=?",
                    new String[]{imagePK}, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                fileLocation = cursor.getString(cursor.getColumnIndex(
                        MediaStore.Images.ImageColumns.DATA));
                flag = true;
            }
        } catch (SQLiteException sqle) {
            Log.e("findImage....", sqle.toString(), sqle);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return flag;
    }

    private boolean tempSavedBitmapFile(Bitmap tempBitmap) {
        boolean flag = false;
        try {
            String tempName = "upload_" + (System.currentTimeMillis() / 1000);
            String fileSuffix = ".jpg";
            //임시파일을 실행한다.(현재입이 종료되면 스스로 삭제)
            File tempFile = File.createTempFile(
                    tempName,            // prefix
                    fileSuffix,                   // suffix
                    myImageDir                   // directory
            );
            final FileOutputStream bitmapStream = new FileOutputStream(tempFile);
            tempBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bitmapStream);
            if (bitmapStream != null) {
                bitmapStream.close();
            }
            fileLocation = tempFile.getAbsolutePath();
        } catch (IOException i) {
            Log.e("저장중 문제발생", i.toString(), i);
        }
        return flag;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isSDCardAvailable()) {
            Toast.makeText(this, "SD 카드가 없어 종료 합니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        String currentAppPackage = getPackageName();

        myImageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                currentAppPackage);

        checkPermission();

        if (!myImageDir.exists()) {
            if (myImageDir.mkdirs()) {
                Toast.makeText(getApplication(), " 저장할 디렉토리가 생성 됨", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //현재 단말기의 SD카드가 마운트되지 않았거나 읽지전용임을 나타냄
    private boolean isSDCardAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private final int MY_PERMISSION_REQUEST_STORAGE = 100;

    private void checkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//마시멜로 이후버전이라면
            //쓰기와 읽기 퍼미션을 허용 하지 않는다면
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to write the permission.
                    Toast.makeText(this, "Read/Write external storage", Toast.LENGTH_SHORT).show();
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSION_REQUEST_STORAGE);

            } else {
                //사용자가 언제나 허락한것 이므로 할게 없음
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    //사용자가 퍼미션을 OK했을 경우
                } else {
                    Log.d("파일업로드", "Permission always deny");
                    //사용자가 퍼미션을 거절했을 경우
                }
                break;
        }
    }

    public void addListenerOnRatingBar() {

        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                changedRatingValue.setText(String.valueOf(rating));
                rate = rating;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    final int NO_IMAGE = 0;
    final int IS_IMAGE = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_action:

                if ((review.getText().toString() == null)
                        || (review.getText().length() <= 0)) {
                    Toast.makeText(WriteReviewActivity.this, "Please write any message."
                            , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WriteReviewActivity.this, "send!" + shopId
                            , Toast.LENGTH_SHORT).show();
                    reviewString = review.getText().toString();
                    new FileUpLoadAsyncTask().execute(fileLocation);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //공통모듈 쪽으로 코드 옮기기
    public static final MediaType IMAGE_MIME_TYPE = MediaType.parse("image/*");

    private class FileUpLoadAsyncTask extends AsyncTask<String, String, String> {

        private int type; //일반 쿼리

        /*public FileUpLoadAsyncTask(int type) {
            this.type = type;
        }*/

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... upLoadFilePath) {

            String resultValue = "";
            File upLoadfile = null;
            if(fileLocation!=null)
                upLoadfile = new File(upLoadFilePath[0]);
            Response response = null;
            try {
                //업로드는 타임 및 리드타임을 넉넉히 준다.
                OkHttpClient toServer = new OkHttpClient.Builder()
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody fileUploadBody;
                if(fileLocation!=null) {
                    fileUploadBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM) //파일 업로드시 반드시 설정
                            .addFormDataPart("userId", Integer.toString(1)) //기본 쿼리
                            .addFormDataPart("shopId", Integer.toString(shopId))
                            .addFormDataPart("review", reviewString)
                            .addFormDataPart("star", Float.toString(rate))
                            .addFormDataPart("image", upLoadfile.getName(), RequestBody.create(IMAGE_MIME_TYPE, upLoadfile))
                            .build();
                }else{
                    fileUploadBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM) //파일 업로드시 반드시 설정
                            .addFormDataPart("userId", Integer.toString(1)) //기본 쿼리
                            .addFormDataPart("shopId", Integer.toString(shopId))
                            .addFormDataPart("review", reviewString)
                            .addFormDataPart("star", Float.toString(rate))
                            .build();
                }
                //요청 세팅
                Request request = new Request.Builder()
                        .url(NetworkDefineConstant.SERVER_URL_REVIEW_INSERT)
                        .post(fileUploadBody) //반드시 post로
                        .addHeader(NetworkDefineConstant.AUTHORIZATION,
                                LKSharedPreferencesManager.getInstance().getKeyToken())
                        .build();

                //동기 방식
                response = toServer.newCall(request).execute();
                if (response.isSuccessful()) {
                    resultValue = response.body().string();
                }
            } catch (Exception e) {
                Log.e("UploadError", e.toString());
            } finally {
                if (response != null) {
                    response.close();
                }
            }
            return resultValue;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String message = "정상적으로 파일이 업로드 되었습니다";

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}