package com.example.mochico.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Aman on 14/2/19.
 */

public class AppSharedPreference {


    public static enum PREF_KEY {

        SET_IP("set_ip"),

        Id("Id"),
        UserName("UserName"),
        BarCode("BarCode"),
        TypeOfWork("TypeOfWork"),
        DescOfWork("DescOfWork"),

        Article("Article"),
        OuterBarCode("OuterBarCode"),
        OuterQty("OuterQty"),
        ScannedQty("ScannedQty");

        public final String KEY;
        PREF_KEY(String key) {
            this.KEY = key;
        }
    }

    public static int getInt(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        int value = sharedPref.getInt(key.KEY, 0);
        return value;
    }
    public static void putInt(Context context, PREF_KEY key, int value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }
    public static void putLong(Context context, PREF_KEY key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key.KEY, value);
        editor.commit();
    }
    public static void putFloat(Context context, PREF_KEY key, Float value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key.KEY, value);
        editor.commit();
    }
    public static Float getFloat(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        Float value = sharedPref.getFloat(key.KEY, 0);
        return value;
    }

    public static long getLong(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        long value = sharedPref.getLong(key.KEY, 0);
        return value;
    }

    public static void putString(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getString(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }

    public static void putBoolean(Context context, PREF_KEY key, boolean value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key.KEY, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        boolean value = sharedPref.getBoolean(key.KEY, false);
        return value;
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);

        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key, null);
        return value;
    }

    public static void clearAllPreferences(Context context) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void putHostUrl(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getHostUrl(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }



    //@Umesh
    public static void putId(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getId(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putUserName(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getUserName(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putBarCode(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getBarCode(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putTypeOfWork(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getTypeOfWork(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putDescOfWork(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getDescOfWork(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putOuterBarCode(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getOuterBarCode(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putOuterQty(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getOuterQty(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putScannedQty(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getScannedQty(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
    public static void putArticle(Context context, PREF_KEY key, String value) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key.KEY, value);

        // Commit the edits!
        editor.commit();
    }

    public static String getArticle(Context context, PREF_KEY key) {
        SharedPreferences sharedPref = PreferenceManager
                .getDefaultSharedPreferences(context);
        String value = sharedPref.getString(key.KEY, null);
        return value;
    }
}
