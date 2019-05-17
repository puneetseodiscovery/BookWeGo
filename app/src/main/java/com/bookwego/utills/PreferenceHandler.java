package com.bookwego.utills;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHandler {

    public static final int MODE = Context.MODE_PRIVATE;
    public static final String PREF_NAME = "APPFRAMEWORK_PREFERENCES";


    public SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public SharedPreferences getFCMPREFERENCE(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
    public SharedPreferences.Editor getFCMEditor(Context context) {
        return getFCMPREFERENCE(context).edit();
    }

    public void clearSavedPrefrences(Context context) {
        SharedPreferences settings= context.getSharedPreferences(PREF_NAME, MODE);
        settings.edit().clear().apply();
    }



}