package com.careem.hacareem.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by anas__000 on 30-Apr-17.
 */
public class Helper {
    public static ProgressDialog dialog;
    public static void showSpinner(String msg, Context context){
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(msg);
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
