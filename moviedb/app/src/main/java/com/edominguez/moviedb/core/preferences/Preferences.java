package com.edominguez.moviedb.core.preferences;

import static com.edominguez.moviedb.core.common.utils.ConstantsKt.EMPTY_STRING;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String TAG = "com.ontopai.app";
    private static final String JWT = TAG + "_JWT";

    private final Context context;

    public Preferences(Context ctx) {
        context = ctx;
    }

    //----------------------------- Set Preferences ---------------------------------------------

    public void setJWT(String data) { setValue(context, JWT, data); }


    //----------------------------- Get Preferences ---------------------------------------------

    public String getJWT() { return getStringValue(context, JWT, EMPTY_STRING); }

    /*---------------------------------------------------------------------------*/

    private static void setValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putString(key, value).apply();
    }

    private static void setValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        prefs.edit().putInt(key, value).apply();
    }

    private static String getStringValue(Context ctx, String key, String value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getString(key, value);
    }

    private static int getIntValue(Context ctx, String key, int value) {
        SharedPreferences prefs = getPreferences(ctx);
        return prefs.getInt(key, value);
    }

    private static SharedPreferences getPreferences(Context ctx) {
        return ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    @SuppressLint("ApplySharedPref")
    public static void deletePreference(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        prefs.edit().remove(key).commit();
    }

}
