package com.leisurekr.leisuresportskorea.sharedPreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.PrintStream;
import java.util.HashSet;
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

    public static final String KEY_ID = "id";
    public static final String KEY_INIT_INTERESTS = "interests";

    public void setId(String id) {
        mEditor.putString(KEY_ID, id);
        mEditor.commit();
    }
    public String getId() {
        return mPrefs.getString(KEY_ID,"");
    }

    public void setInterests(boolean trueOrFalse) {
        mEditor.putBoolean(KEY_INIT_INTERESTS, trueOrFalse);
        mEditor.commit();
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
