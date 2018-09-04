package com.ipsacademypune.user.ipsacademy.Adapters;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveImagePosition_pref {

    private static Context context;

    public final static String PREFS_NAME = "appname_prefs";

    public SaveImagePosition_pref(Context context) {
        this.context = context;
    }

    public static void setStr(String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key, "DNF");
    }

    public static void setInt(String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(String.valueOf(key), value);
        editor.commit();
    }

    public static int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }
}
