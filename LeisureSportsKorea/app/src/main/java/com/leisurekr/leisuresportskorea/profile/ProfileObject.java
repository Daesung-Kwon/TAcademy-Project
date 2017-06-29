package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by user on 2017-06-14.
 */

public class ProfileObject implements Serializable{
    private int id;
    private String name;
    private String email;
    int age;
    private HashMap<String, Boolean> tags;
    private String sex;
    private String backImage;
    private String nationality;

    /*public ProfileObject(){}
    protected ProfileObject(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        sex = in.readString();
        backImage = in.readString();
        nationality = in.readString();
    }*/



    public void setData(JSONObject jsonObject){
        try {

            backImage = jsonObject.getString("actImage");

            JSONObject jsonObject1 = jsonObject.getJSONObject("userInfo");
            this.id = jsonObject1.getInt("userId");
            this.name = jsonObject1.getString("username");
            this.email = jsonObject1.getString("email");
            this.sex = jsonObject1.getString("sex");
            this.age = jsonObject1.getInt("age");
            this.nationality = jsonObject1.getString("nationality");

            JSONObject jsonObject2 = jsonObject1.getJSONObject("tags");
            tags = new HashMap<String, Boolean>();
            tags.put("ATV",jsonObject2.getBoolean("ATV"));
            tags.put("Bungee Jump",jsonObject2.getBoolean("Bungee Jump"));
            tags.put("Fun Boat",jsonObject2.getBoolean("Fun Boat"));
            tags.put("Paintball",jsonObject2.getBoolean("Paintball"));
            tags.put("Paragliding",jsonObject2.getBoolean("Paragliding"));
            tags.put("Rafting",jsonObject2.getBoolean("Rafting"));
            tags.put("Scuba Diving",jsonObject2.getBoolean("Scuba Diving"));
            tags.put("Ski",jsonObject2.getBoolean("Ski"));
            tags.put("Snowboard",jsonObject2.getBoolean("Snowboard"));
            tags.put("Surfing",jsonObject2.getBoolean("Surfing"));
            tags.put("Wake Board",jsonObject2.getBoolean("Wakeboard"));
            tags.put("Water Ski",jsonObject2.getBoolean("Water Ski"));


            Log.e("파싱 성공", "ProfileObjcet에서 파싱 성공");
        }catch(JSONException e){
            Log.e("파싱 오류", "ProfileObjcet에서 파싱 오류");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, Boolean> getTags() {
        return tags;
    }

    public void setTags(HashMap<String, Boolean> tags) {
        this.tags = tags;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(sex);
        dest.writeString(backImage);
        dest.writeString(nationality);
    }

    public static final Creator<ProfileObject> CREATOR = new Creator<ProfileObject>() {
        @Override
        public ProfileObject createFromParcel(Parcel in) {
            return new ProfileObject(in);
        }

        @Override
        public ProfileObject[] newArray(int size) {
            return new ProfileObject[size];
        }
    };*/
}
