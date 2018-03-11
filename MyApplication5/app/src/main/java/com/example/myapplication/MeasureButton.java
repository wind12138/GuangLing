package com.example.myapplication;

import android.util.Log;
import android.view.View;

/**
 * Created by 朱峰 on 2018/3/9.
 */

public class MeasureButton {

    public int measureWidth(int measureSpec,float mWidth){
        int result = 0;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if(specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        }
        else{
            result = (int)mWidth;
            if(specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
            }
        }
        return result;


    }

    public int measureHeight(int measureSpec,float mHeight){
        int result = 0;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if(specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        }
        else{
            result = (int)mHeight;
            if(specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
            }
        }
        return result;

    }
}
