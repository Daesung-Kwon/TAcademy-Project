package com.leisurekr.leisuresportskorea.sharedPreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by mobile on 2017. 6. 14..
 */

public class LKSharedPreferencesManager {
    // 싱글톤 패턴으로 한번만 생성
    private static LKSharedPreferencesManager instance;

    public static LKSharedPreferencesManager getInstance() {
        if (instance == null) {
            instance = new LKSharedPreferencesManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private LKSharedPreferencesManager() {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(com.leisurekr.leisuresportskorea.LKApplication.getLKApplication());
        mEditor = mPrefs.edit();
    }

    public static final String KEY_PUSH = "push";
    public static final String KEY_INIT_INTERESTS = "interests";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_FCM = "fcmtoken";


    public void removeAll(){
        mEditor.clear();
        mEditor.commit();
    }

    public void setKeyFcm(Boolean fcm){
        mEditor.putBoolean(KEY_FCM, fcm);
        mEditor.commit();
    }

    public Boolean getKeyFcm() {
        return mPrefs.getBoolean(KEY_FCM,false);
    }


    public void setIsPushed(Boolean isPushed) {
        mEditor.putBoolean(KEY_PUSH, isPushed);
        mEditor.commit();
    }
    public Boolean getIsPushed() {
        return mPrefs.getBoolean(KEY_PUSH,true);
    }

    public void setKeyToken(String token) {
        mEditor.putString(KEY_TOKEN, token);
        mEditor.commit();
    }
    public String getKeyToken() {
        return mPrefs.getString(KEY_TOKEN, "");
    }

    public void setInterests(boolean trueOrFalse) {
        mEditor.putBoolean(KEY_INIT_INTERESTS, trueOrFalse);
        mEditor.commit();
    }

    public String getInterests() {
        return mPrefs.getString(KEY_INIT_INTERESTS, "");
    }

    public void setInterestsSet(Set<String> interests) {
        try {
            if (mEditor.equals(KEY_INIT_INTERESTS)) { mEditor.remove(KEY_INIT_INTERESTS); }
        }catch (RuntimeException re) {
            re.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        mEditor.putStringSet(KEY_INIT_INTERESTS, interests);
        mEditor.commit();
    }
    public Set<String> getInterestsSet() { return mPrefs.getStringSet(KEY_INIT_INTERESTS, null); }

    public boolean isBackupSync() {
        return mPrefs.getBoolean("perf_sync", false);
    }
}
