package com.example.mochico.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import com.example.mochico.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethod {
    private static Vibrator vibrator;
    private static Editor editor;
    public static String NOTIFICATION_STATUS = "NOTIFICATION_STATUS";

    /**
     * Called for setting the value based on key in Prefs
     */

    public static void setPrefsData(Context context, String prefsKey,
                                    String prefValue) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(prefsKey, prefValue);
        editor.commit();
    }


    public static void saveNotification(Context context, String key, boolean value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();

    }


    public static boolean getNotification(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        boolean noti_status = sharedPreferences.getBoolean(NOTIFICATION_STATUS, true);

        return noti_status;
    }

    public static void saveLoginPreferencesUserId(Context context, String value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putString("USERID", value);
        editor.commit();

    }


    public static String getLoginSavedPreferencesUSerID(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        String login_status = sharedPreferences.getString("USERID", "");

        return login_status;
    }


    public static void saveLoginPreferencesProductId(Context context, String value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putString("PRODUCTID", value);
        editor.commit();

    }


    public static String getLoginSavedPreferencesProductId(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        String login_status = sharedPreferences.getString("PRODUCTID", "");

        return login_status;
    }


    public static void savepPin(Context context, String value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putString("PIN", value);
        editor.commit();

    }


    public static String getPin(Context context) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        String login_status = sharedPreferences.getString("PIN", "");

        return login_status;
    }


    public static void setPrefsLoginStatus(Context context, String prefsKey,
                                           boolean prefValue) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putBoolean(prefsKey, prefValue);
        editor.commit();
    }

    public static boolean getPrefsLoginData(Context context, String prefsKey,
                                            boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        boolean prefsValue = sharedPreferences.getBoolean(prefsKey,
                defaultValue);
        return prefsValue;
    }


    public static String getDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mmaa");
        dateFormatter.setTimeZone(TimeZone.getDefault());
        String dt = dateFormatter.format(value);

        return dt;
    }


    /**
     * Called for getting the value based on key from Prefs
     */
    public static String getPrefsData(Context context, String prefsKey,
                                      String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        String prefsValue = sharedPreferences.getString(prefsKey, defaultValue);
        return prefsValue;
    }

    /**
     * Called for Showing Alert in Application
     */
    public static void showAlert(String message, Activity context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Called for checking Email Validation
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Called for checking Internet connection
     */
    public static boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        try {
            netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }


    public static void saveLoginPreferences(Context context, String key,
                                            String value) {

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public static void displaySnackbarLogin(View view, Context context, String s) {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View sbview = snack.getView();
        sbview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        TextView textView = (TextView) sbview.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        snack.show();
    }

    public static void displayWhiteback(View view, Context context, String s) {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View sbview = snack.getView();
        sbview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
        TextView textView = (TextView) sbview.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorBlack));
        snack.show();
    }
    public static void displaySnackbarerror(View view, Context context, String s) {
        Snackbar snack = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
        View sbview = snack.getView();
        sbview.setBackgroundColor(ContextCompat.getColor(context, R.color.colorred));
        TextView textView = sbview.findViewById(R.id.snackbar_text);
        textView.setTextColor(context.getResources().getColor(R.color.colorWhite));
        snack.show();
    }




    public static String convertDate(String timestamp) {


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm aa");

            Date resultdate = new Date(Long.valueOf(timestamp));
            System.out.println(dateFormat.format(resultdate));


            // Calendar calendar = Calendar.getInstance();
            //TimeZone tz = TimeZone.getDefault();
            // calendar.setTimeInMillis(Long.valueOf(timestamp) * 1000);
            // calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            // SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy-HH-mm-ss");
            //SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm aa");

            //Date currenTimeZone = (Date) calendar.getTime();


            return dateFormat.format(resultdate);
        } catch (Exception e) {
        }


        return "";
    }

    public static boolean copyStream(InputStream input, OutputStream output)
            throws IOException {
        boolean isCopied = false;
        byte[] buffer = new byte[1024];
        int bytesRead;
        try {
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            isCopied = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            isCopied = false;
        }
        return isCopied;
    }

    public static void closeKeyboard(final Context context, final View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboardfragment(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public static void vibraTeSoundfail(final Context context) {
        vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
    }

    public static void ringSuccess(Context context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.success);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ringSoundFail(Context context) {
        MediaPlayer mp = MediaPlayer.create(context, R.raw.ring_cut);
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
