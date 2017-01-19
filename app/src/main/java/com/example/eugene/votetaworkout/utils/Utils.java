package com.example.eugene.votetaworkout.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * @author Eugene
 * @since 1/15/2017.
 */
public class Utils {
    public static void showAlertMessage(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", null)
                .show();
    }
}
