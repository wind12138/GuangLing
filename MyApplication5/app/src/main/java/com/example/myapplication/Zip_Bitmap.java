package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by 朱峰 on 2018/3/9.
 */

public class Zip_Bitmap {
    public Bitmap Zip_Bitmap(Bitmap map,int newWidth,int newHeight,int bwidth,int bheight) {
        float scaleWidth = (newWidth)/bwidth;
        float scaleHeight = (newHeight)/bheight;
        Matrix ma = new Matrix();
        ma.postScale(scaleWidth,scaleHeight);
        Bitmap newmap = Bitmap.createBitmap(map,0,0,bwidth,bheight,ma,true);
        return newmap;
    }
}
