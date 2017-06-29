package com.leisurekr.leisuresportskorea.profile;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;


/**
 * Created by user on 2017-05-30.
 */

public class ReservationObject implements Serializable{

    private int leftBackImage;
    private int price;
    private int adult;
    private int children;
    private String time;

    private String main;
    private String text1;
    private String text2;
    private String text3;
    private String progress;
    private String date;
    private String location1;
    private String location2;
    private String location3;

    ProgramObject programObject;



    public ReservationObject() {

    }

    public void setData(int leftBackImage, String main, String text1, String text2, String text3, int price
            , String progress, String date,String time, int adult,int children, String location1, String location2
            , String location3)
    {
        this.leftBackImage = leftBackImage;
        this.main = main;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.price = price;
        this.progress = progress;
        this.date = date;
        this.time = time;
        this.adult = adult;
        this.children = children;
        this.location1 = location1;
        this.location2 = location2;
        this.location3 = location3;
    }

    public void setData(JSONObject jsonObject)
    {
        try {
            this.adult = jsonObject.getInt("adult");
            this.children = jsonObject.getInt("child");
            this.progress = jsonObject.getString("status");
            this.date = jsonObject.getString("strDate");
            programObject = new ProgramObject();
            programObject.setData(jsonObject.getJSONObject("program"));

            if(adult < 0){
                adult = 0;
            }
            if(children < 0){
                children = 0;
            }

            Log.e("파싱성공","reservation 파싱 성공");
        }catch (JSONException e){
            Log.e("파싱 오류","reservation Parsing 오류");
        }
    }

    public int getLeftBackImage() {
        return leftBackImage;
    }

    public void setLeftBackImage(int leftBackImage) {
        this.leftBackImage = leftBackImage;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getAdult() {
        return adult;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getLocation3() {
        return location3;
    }

    public void setLocation3(String location3) {
        this.location3 = location3;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
