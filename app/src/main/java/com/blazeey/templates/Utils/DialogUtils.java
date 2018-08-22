package com.blazeey.templates.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.ViewGroup;

public class DialogUtils {

    public static void resize(Dialog dialog, Context context) {

        Point screenSize = Screen.getAppUsableScreenSize(context);

        if (Screen.getSmallestWidth(context) < 480) {
            dialog.getWindow().setLayout((int) (screenSize.x * 0.6), (int) (screenSize.y * 1.0));
        } else if (Screen.getSmallestWidth(context) < 600) {
            dialog.getWindow().setLayout((int) (screenSize.x * 0.6), (int) (screenSize.y * 0.9));
            Log.v("SIZE", "480");
        } else if (Screen.getSmallestWidth(context) < 720) {
            dialog.getWindow().setLayout((int) (screenSize.x * 0.6), (int) (screenSize.y * 0.8));
            Log.v("SIZE", "600");
        } else {
            dialog.getWindow().setLayout((int) (screenSize.x * 0.6), (int) (screenSize.y * 0.67));
        }
    }

    /**
     * Setting the default params of the dialog
     * @param dialog the dialog to modify
     * @param resource the layout resource of the dialog
     */
    private void setDefaultParams(Dialog dialog, int resource) {
        dialog.getWindow().getDecorView().setSystemUiVisibility(Screen.FULL_SCREEN_FLAG);
        dialog.setContentView(resource);
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
