package com.lpan.my.mh.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lpan.my.mh.AppContext;


/**
 * Created by lpan on 2018/5/6.
 */

public class Preferences {
    public static final String TAG=Preferences.class.getSimpleName();

    public static final String KEY_GAME_MONEY_INFO = "game_money_info";
    public static final String KEY_LING_SHI_INFO = "ling_shi_info";
    public static final String KEY_QIANG_HUA_INFO = "qiang_hua_info";

    private SharedPreferences mPrefs;

    private static Preferences preferences;

    private Preferences(Context context) {
        this.mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Preferences getInstance() {
        if (preferences == null) {
            preferences = new Preferences(AppContext.getContext());
        }
        return preferences;
    }

    public void putInt(String key, int value) {
        mPrefs.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int value) {
        return mPrefs.getInt(key, value);
    }

    public String getString(String key, String value) {
        return mPrefs.getString(key, value);
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public void putString(String key, String value) {
        mPrefs.edit().putString(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        mPrefs.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key,false);
    }

    public boolean getBoolean(String key, boolean value) {
        return mPrefs.getBoolean(key, value);
    }

    public void clear(String key) {
        mPrefs.edit().remove(key).apply();
    }

}
