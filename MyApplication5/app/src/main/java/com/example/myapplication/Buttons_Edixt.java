package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by 朱峰 on 2018/3/9.
 */

public class Buttons_Edixt extends AppCompatEditText {

    private int Measure_width,Measure_height;

    private float Layout_width = 500,Layout_height = 100;

    public  Buttons_Edixt(Context context) {
        this(context,null);
    }

    public Buttons_Edixt(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public Buttons_Edixt(Context context,AttributeSet attrs,int defStyle) {
        super(context,attrs,defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        MeasureButton m = new MeasureButton();
        setMeasuredDimension(
                Measure_width = m.measureWidth(widthMeasureSpec,Layout_width),
                Measure_height = m.measureHeight(heightMeasureSpec,Layout_height));
    }

    @Override
    public void layout(int left,int top,int right,int bottom) {
        left = 200;
        top = 100;
        right = 700;
        bottom = 200;
        super.layout(left,top,right,bottom);
    }

}
