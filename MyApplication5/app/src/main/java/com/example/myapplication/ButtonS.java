package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfRenderer;
import android.util.AttributeSet;
import android.view.View;

import java.security.cert.CRLException;

/**
 * Created by 朱峰 on 2018/3/9.
 */

public class ButtonS extends View {

    private static final String TAG = "ButtonS";

    private int Measure_width,Measure_height,canvas_number = 1;

    //***********
    private float Layout_width = 500
            ,Layout_height = 100;                       //注意这里要改
    //**********

    private Paint Buttons_paint,Image_paint;

    private RectF ButtonsF,mDecRect;

    private Rect mSrcRect;

    public ButtonS(Context context) {
        this(context, null);
        init();
    }

    public ButtonS(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public ButtonS(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        Buttons_paint = createPaint(R.color.colorAccent,0,Paint.Style.FILL,1,255,false);
        Image_paint = createPaint(0,0, Paint.Style.FILL,1,255,true);
    }

    private Paint createPaint(int PaintColor,int TextSize,Paint.Style PaintStyle,int LineWidth,int PainAlpha,boolean Paintboolean) {
        Paint paint = new Paint();
        paint.setColor(PaintColor);
        paint.setTextSize(TextSize);
        paint.setStyle(PaintStyle);
        paint.setStrokeWidth(LineWidth);
        paint.setAlpha(PainAlpha);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(Paintboolean);
        paint.setDither(Paintboolean);
        return paint;
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
        super.layout(left,top,right,bottom);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        switch (canvas_number) {
            case 1:
                drawbuttons(canvas);
                ButtonS_animate ba = new ButtonS_animate();
                ba.ButtonS_animate(ButtonsF,);
                break;
            case 2:
                drawimage(canvas);
                break;
        }

    }

    private void drawbuttons(Canvas canvas) {
        int Buttons_width = getWidth();
        int Buttons_height = getHeight();
        int Buttons_Round = Buttons_height/2;

        ButtonsF = new RectF(0,0,Buttons_width,Buttons_height);
        canvas.drawRoundRect(ButtonsF,Buttons_Round,Buttons_Round,Buttons_paint);
        canvas_number++;
    }

    private void drawimage(Canvas canvas) {
        Resources m = getResources();
        Bitmap B = ((BitmapDrawable)m.getDrawable(src)).getBitmap();
        int mBitWidth = B.getWidth();
        int mBitHeight = B.getHeight();
        mSrcRect = new Rect(0,0,mBitWidth,mBitHeight);
        mDecRect = new RectF(0,0,mBitWidth,mBitHeight);
        Zip_Bitmap zb = new Zip_Bitmap();
        canvas.drawBitmap(zb.Zip_Bitmap(B,Measure_height,Measure_height,mBitWidth,mBitHeight),mSrcRect,mDecRect,Image_paint);
    }



}
