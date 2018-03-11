package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by 朱峰 on 2018/3/3.
 */

public class ExditText extends AppCompatEditText{

    private static final String TAG = "ExditText";

    private int Swidth,Sheight,Xwidth,Yheight;

    private float sWidth,sHeight;


    public  ExditText(Context context) {
        this(context,null);
    }

    public ExditText(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ExditText(Context context,AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
        TypedArray typed = context.obtainStyledAttributes(attrs,R.styleable.ExditText);
        try {
            sWidth = typed.getDimension(R.styleable.ExditText_swidth,200);
            sHeight = typed.getDimension(R.styleable.ExditText_sheight,50);
        }
        finally {
            typed.recycle();
        }
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                Swidth = measureWidth(widthMeasureSpec),
                Sheight = measureHeight(heightMeasureSpec));
        Log.d(TAG, "Mwidth ===="+ Swidth);
        Log.d(TAG, "mwidth =========="+sWidth);
    }

    private int measureWidth(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        else{
            result = (int)sWidth;
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        else{
            result = (int)sHeight;
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
            }
        }
        return result;

    }

    private void getpingmu() {
        DisplayMetrics wm = new DisplayMetrics();
        wm = getResources().getDisplayMetrics();
        Xwidth = wm.widthPixels;
        Yheight = wm.heightPixels;
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        int x = (Xwidth/2 - Swidth/2) + button_animate.button_animate_HEIGHT;
        int y = (Yheight/2 - Sheight/2) + 20 ;
        int z = (Yheight/2 + Sheight/2) + 20 ;
        getpingmu();
        l = x;
        t = y;
        r = Xwidth/2 + Swidth/2;
        b = z;
        super.layout(l, t, r, b);

    }

}

