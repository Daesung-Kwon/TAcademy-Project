package com.leisurekr.leisuresportskorea.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leisurekr.leisuresportskorea.PreInterestsActivity;
import com.leisurekr.leisuresportskorea.R;
import com.leisurekr.leisuresportskorea.common.NetworkDefineConstant;
import com.leisurekr.leisuresportskorea.okhttp.OkHttpAPIHelperHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileEditActivity extends AppCompatActivity {

    ImageView backImage;
    CircleImageView profileImage;
    TextInputEditText name;
    ImageView erase;
    TextInputEditText age;
    Spinner nationality;
    Spinner sex;
    RelativeLayout interests;
    TextView intereststext;
    TextView sns;

    Toolbar toolbar;
    String s = "Male";

    ProfileObject profileObject;

    int result = 0;

    int[] resultValue;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == 0) {
                    String tag = "";
                    String s = "";
                    int[] values = data.getIntArrayExtra("result");
                    resultValue = values;
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] == 1) {
                            switch (i) {
                                case 0:
                                    s = "ATV";
                                    break;
                                case 1:
                                    s = "Bungee Jump";
                                    break;
                                case 2:
                                    s = "Fun Boat";
                                    break;
                                case 3:
                                    s = "Paintball";
                                    break;
                                case 4:
                                    s = "Paragliding";
                                    break;
                                case 5:
                                    s = "Rafting";
                                    break;
                                case 6:
                                    s = "Scuba Diving";
                                    break;
                                case 7:
                                    s = "Ski";
                                    break;
                                case 8:
                                    s = "Snowboard";
                                    break;
                                case 9:
                                    s = "Surfing";
                                    break;
                                case 10:
                                    s = "Wake Board";
                                    break;
                                case 11:
                                    s = "Water Ski";
                                    break;
                            }
                            tag = tag + "# " + s + " ";
                        }
                    }
                    intereststext.setText(tag);
                }
                break;
            case 1:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit_activity);

        Intent intent = getIntent();
        profileObject = (ProfileObject) intent.getSerializableExtra("profileObject");

        toolbar = (Toolbar) findViewById(R.id.profile_edit_toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backImage = (ImageView) findViewById(R.id.profile_edit_backimage);
        profileImage = (CircleImageView) findViewById(R.id.profile_edit_circleimage);
        name = (TextInputEditText) findViewById(R.id.profile_edit_name);
        erase = (ImageView) findViewById(R.id.profile_edit_erase);
        age = (TextInputEditText) findViewById(R.id.profile_edit_age);
        nationality = (Spinner) findViewById(R.id.profile_edit_nationality);
        sex = (Spinner) findViewById(R.id.profile_edit_sex);
        interests = (RelativeLayout) findViewById(R.id.profile_edit_interests);
        intereststext = (TextView) findViewById(R.id.profile_edit_interststext);
        sns = (TextView) findViewById(R.id.profile_edit_sns);


        Glide.with(ProfileEditActivity.this).load(profileObject.getBackImage())
                .into(backImage);
        //backImage.setImageResource(R.drawable.pic_bg_surfing);
        switch (profileObject.getSex()) {
            case "Male":
                profileImage.setImageResource(R.drawable.ic_ma);
                break;
            case "Female":
                profileImage.setImageResource(R.drawable.ic_fe);
                break;
            case "Gender":
                profileImage.setImageResource(R.drawable.ic_ne);
                break;
            default:
                profileImage.setImageResource(R.drawable.ic_ne);
                break;
        }
        if (profileObject.getName() != null)
            name.setText(profileObject.getName());
        else
            Log.e("ProfileEdit", "profileObject.getName() = null");


        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
            }
        });

        if (profileObject.getNationality() != null) {
            String[] na = getResources().getStringArray(R.array.Nationality);
            for (int i = 0; i < na.length; i++) {
                if (na[i].equals(profileObject.getNationality())) {
                    nationality.setSelection(i);
                    break;
                }
            }
        } else
            Log.e("ProfileEdit", "profileObject.getNationality() = null");

        if (profileObject.getSex() != null) {
            String[] se = getResources().getStringArray(R.array.Sex);
            for (int i = 0; i < se.length; i++) {
                Log.e("ProfileEdit", "" + i);
                if (se[i].equals(profileObject.getSex())) {
                    sex.setSelection(i);
                    break;
                }
            }
        } else
            Log.e("ProfileEdit", "profileObject.getSex() = null");

        final HashMap<String, Boolean> tags = profileObject.getTags();
        final Set<String> keys = tags.keySet();


        interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int[] interestsValues = null;

                if (resultValue == null) {
                    Iterator<String> iterator = keys.iterator();

                    interestsValues = new int[]{
                            0, 0, 0, 0,
                            0, 0, 0, 0,
                            0, 0, 0, 0
                    };

                    int index = 0;
                    while (iterator.hasNext()) {
                        String s = iterator.next();
                        //Log.e("interestsValues", "" + s);
                        switch (s) {
                            case "ATV":
                                index = 0;
                                break;
                            case "Bungee Jump":
                                index = 1;
                                break;
                            case "Fun Boat":
                                index = 2;
                                break;
                            case "Paintball":
                                index = 3;
                                break;
                            case "Paragliding":
                                index = 4;
                                break;
                            case "Rafting":
                                index = 5;
                                break;
                            case "Scuba Diving":
                                index = 6;
                                break;
                            case "Ski":
                                index = 7;
                                break;
                            case "Snowboard":
                                index = 8;
                                break;
                            case "Surfing":
                                index = 9;
                                break;
                            case "Wake Board":
                                index = 10;
                                break;
                            case "Water Ski":
                                index = 11;
                                break;
                        }
                        if (tags.get(s)) {
                            interestsValues[index] = 1;
                        }

                    }
                }else{
                    interestsValues = resultValue;//이미 관심사 변경을 눌러서 변경을 하고
                                                    // 다시 관심사 변경을 누를때 초기 배열 변경
                }


                Log.e("interestsValues", "" + interestsValues[0] + interestsValues[1] + interestsValues[2]);
                Intent intent1 = new Intent(ProfileEditActivity.this, PreInterestsActivity.class);
                intent1.putExtra("init", interestsValues);

                startActivityForResult(intent1, 0);
            }
        });

        age.setText("" + profileObject.getAge());
        if (profileObject.getTags() != null) {
            String text = "";
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (tags.get(s)) {
                    text = text + "# " + s + " ";
                }
            }
            intereststext.setText(text);
        } else
            Log.e("ProfileEdit", "profileObject.getTags() = null");


        //age.setText();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_action:
                if (name.getText().toString().equals("") || name.getText().toString() == null) {
                    Toast.makeText(ProfileEditActivity.this,
                            "Please type your name", Toast.LENGTH_SHORT).show();
                } else if (((String) sex.getSelectedItem()).equals("Select") ||
                        (String) sex.getSelectedItem() == null) {
                    Toast.makeText(ProfileEditActivity.this,
                            "Please select your sex", Toast.LENGTH_SHORT).show();
                } else if (age.getText().toString().equals("") || age.getText().toString() == null) {
                    Toast.makeText(ProfileEditActivity.this,
                            "Please type your age", Toast.LENGTH_SHORT).show();
                } else if (((String) nationality.getSelectedItem()).equals("Select") ||
                        (String) sex.getSelectedItem() == null) {
                    Toast.makeText(ProfileEditActivity.this,
                            "Please select your nationality", Toast.LENGTH_SHORT).show();
                } else {
                    profileObject.setName(name.getText().toString());
                    profileObject.setSex((String) sex.getSelectedItem());
                    profileObject.setAge(Integer.parseInt(age.getText().toString()));
                    profileObject.setNationality((String) nationality.getSelectedItem());
                    new AsyncEditInsert().execute(profileObject);
                    setResult(0);
                    finish();
                }
                break;

        }
        return true;
    }

    public class AsyncEditInsert extends AsyncTask<ProfileObject, Integer, String> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ProfileEditActivity.this,
                    "서버입력중", "잠시만 기다려 주세요 ...", true);
        }

        @Override
        protected String doInBackground(ProfileObject... profileObjects) {
            return OkHttpAPIHelperHandler.editJSONInsert(profileObjects);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            if (result != null) {
                if (result.equalsIgnoreCase("success")) {
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_OK, null);
                } else {
                    showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL, null);
                }
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("message", "입력 중 문제 발생[디버깅]!");
                showDialog(NetworkDefineConstant.LK_INSERT_DIALOG_FAIL, bundle);
            }
        }
    }
}
