package com.blazeey.templates.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

public class LoadImage {

    public static LoadImage instance;
    private Bitmap bm = null;
    private AssetManager mngr;
    private int childCount;
    Context context;
    
    private LoadImage(Context context) {
        this.context = context;
        mngr = context.getAssets();
        childCount = 0;
    }

    public static LoadImage getInstance(Context context) {
        if (instance == null)
            instance = new LoadImage(context);
        return instance;
    }

    public Bitmap readBitmapFromAssets(String path) {
        bm = null;

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //reduce quality
            if (childCount < 90)
                options.inSampleSize = 2;
            else
                options.inSampleSize = 4;
            options.inJustDecodeBounds = false;
            options.inPurgeable = true;
            options.inInputShareable = true;

            InputStream ins = mngr.open(path);
            // Convert the input stream into a bitmap
            bm = BitmapFactory.decodeStream(ins, null, options);
            if (ins != null)
                ins.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return bm;
    }


    public Bitmap readBitmapFromSDCard(String path) {
        bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //reduce quality
        if (childCount < 90)
            options.inSampleSize = 2;
        else
            options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;

        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    public Bitmap readBitmapFromSDCard(String path, boolean compress) {
        bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        //reduce quality
        if (compress)
            options.inSampleSize = 2;
        else
            options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;

        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

    public Bitmap readBitmap(int id) {
        bm = null;
        BitmapFactory.Options options = new BitmapFactory.Options();

        //reduce quality
        options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;

        try {
            bm = BitmapFactory.decodeResource(context.getResources(), id, options);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return bm;
    }

    public void setChildCount(int count) {
        childCount = count;
    }

}