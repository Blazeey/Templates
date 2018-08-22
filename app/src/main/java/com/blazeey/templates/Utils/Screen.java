package com.blazeey.templates.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Screen {

    public static final int FULL_SCREEN_FLAG = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
            View.SYSTEM_UI_FLAG_FULLSCREEN;

    public static int getSmallestWidth(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        return configuration.smallestScreenWidthDp;
    }

    public static Point getAppUsableScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager != null ? windowManager.getDefaultDisplay() : null;
        Point size = new Point();
        if (display != null) {
            display.getSize(size);
        }
        return size;
    }

    /* Full Screen
    void setFullScreen() {
        int flag = FULL_SCREEN_FLAG;
        getWindow().getDecorView().setSystemUiVisibility(flag);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setFullScreen();
        }
    }
    */
}
