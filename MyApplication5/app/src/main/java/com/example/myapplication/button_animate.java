package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 朱峰 on 2018/3/3.
 */

public class button_animate extends View{

    public static boolean boolean_onclick;

    public static int button_animate_WIDTH,button_animate_HEIGHT;

    private static final String TAG = "button_animate";

    private RectF buttonF,mDecRect;

    private boolean ANSWER = false;

    private int buttonColor,cricleColor,textColor,Xwidth,Yheight,Mwidth,Mheight,mTime,xwidth,W = 0,m =0,TIME,X,Y;

    private Paint buttonPaint,textPaint,bitPaint,linepaint;

    private String buttontext;

    private float textX,textY,left,top,right,bottom,mWidth,mHeight,scaleWidth,scaleHeight,i,t;

    private int button_layout,mBitwidth,mBitheight,src,ANIMATE = 1;

    private Rect mSrcRect;

    public button_animate(Context context) {
        this(context, null);
    }

    public button_animate(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public button_animate(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.button_animate);
        try {
            buttonColor = typedArray.getColor(R.styleable.button_animate_buttonColor,0);
            mWidth = typedArray.getDimension(R.styleable.button_animate_mWdith,200);
            mHeight = typedArray.getDimension(R.styleable.button_animate_mHeight,100);
            cricleColor = typedArray.getColor(R.styleable.button_animate_cricleColor,0);
            textColor = typedArray.getColor(R.styleable.button_animate_textColor,0);
            buttontext = typedArray.getString(R.styleable.button_animate_text);
            button_layout = typedArray.getInt(R.styleable.button_animate_button_layout,0);
            src = typedArray.getResourceId(R.styleable.button_animate_ImageSrc,0);
        }finally {
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        buttonPaint = createPaint(buttonColor,0,Paint.Style.FILL,32);
        linepaint = createPaint(buttonColor,0, Paint.Style.FILL,1);
    }

    private Paint createPaint(int paintColor,int textSize,Paint.Style style,int lineWidth) {
        Paint paint = new Paint();
        paint.setColor(paintColor);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(lineWidth);
        paint.setTextSize(textSize);
        paint.setStyle(style);
        return  paint;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                Mwidth = measureWidth(widthMeasureSpec),
                Mheight = measureHeight(heightMeasureSpec));
        Log.d(TAG, "sdad+  "+Mwidth);
        Log.d(TAG, "onMeasure: "+mWidth);
    }

    private int measureWidth(int measureSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        else{
            result = (int)mWidth;
            if(specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result,specSize);
                Log.d(TAG, "measureWidth: sda  "+specSize);
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
            result = (int)mHeight;
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
        getpingmu();
        l = Xwidth/2 - Mwidth/2;
        t = Yheight/2 - Mheight/2;
        r = Xwidth/2 + Mwidth/2;
        b = Yheight/2 + Mheight/2;
        super.layout(l, t, r, b);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float linebottom = (bottom - Y) + 5;
        switch(ANIMATE) {
            case 1:
                drawbutton(canvas);
                break;
            case 2:
                drawImage(canvas);
                drawLine(canvas);
                break;
        }
    }

    private void drawbutton(Canvas canvas) {
        button_animate_WIDTH = getWidth();
        button_animate_HEIGHT = getHeight();
        left = 0 + m;
        top = 0;
        right = button_animate_WIDTH - m;
        bottom = button_animate_HEIGHT;
        float yheight = button_animate_HEIGHT/2;
        TIME = (button_animate_WIDTH - button_animate_HEIGHT)/2;
        X = (2*button_animate_WIDTH - button_animate_HEIGHT)/4;
        Y = button_animate_HEIGHT/4;
        i = (2*button_animate_WIDTH - button_animate_HEIGHT)/4 - W;
        t = (2*button_animate_WIDTH + button_animate_HEIGHT)/4 - W;

        buttonF = new RectF(left,top,right,bottom);
        canvas.drawRoundRect(buttonF,yheight,yheight,buttonPaint);
    }

    private void drawImage (Canvas canvas) {
        bitPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitPaint.setFilterBitmap(true);
        bitPaint.setDither(true);
        Resources mresource =getResources();
        Bitmap mbitmap = ((BitmapDrawable)mresource.getDrawable(src)).getBitmap();
        mBitwidth = mbitmap.getWidth();
        mBitheight = mbitmap.getHeight();
        mSrcRect = new Rect(0,0,mBitwidth,mBitheight);
        mDecRect = new RectF(i,top + Y,t,bottom - Y);
        canvas.drawBitmap(zipBitmap(mbitmap,bottom,bottom),mSrcRect,mDecRect,bitPaint);
    }

    private void drawLine(Canvas canvas) {
        float bottomx =(bottom - Y) + 10;
        canvas.drawLine(0,bottomx,button_animate_WIDTH,bottomx,linepaint);
    }

    public Bitmap zipBitmap(Bitmap map,float NewWidth,float NewHeight) {
        scaleWidth = (NewWidth)/mBitwidth;
        scaleHeight = (NewHeight)/mBitheight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        Bitmap newmap = Bitmap.createBitmap(map,0,0,mBitwidth,mBitheight,matrix,true);
        return newmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (x > left && x < right) {
            if(y > top && y < bottom) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_DOWN:
                        if (!ANSWER) {
                            buttonAnimate();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                }
            }
        }
        return  super.onTouchEvent(event);
    }

    private void buttonAnimate() {
        setClickable(false);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,TIME);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int c = (Integer)valueAnimator.getAnimatedValue();
                m = c;
                left = 0 + m;
                right = right-m;
                invalidate();

            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animator){
                ANSWER = true;
                FinallyAnimate(ANSWER);
                ImageAnimate();
            }
        });
        valueAnimator.start();

    }

    private void ImageAnimate(){
        ValueAnimator va = ValueAnimator.ofInt(0,X);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int c = (Integer)valueAnimator.getAnimatedValue();
                W = c;
                i = (2*button_animate_WIDTH - button_animate_HEIGHT)/4 - W;
                t = (2*button_animate_WIDTH + button_animate_HEIGHT)/4 - W;
                invalidate();

            }
        });

        va.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animator){
                boolean_onclick = true;
            }
        });

        va.start();
    }

    private int FinallyAnimate(boolean an) {
        if(an == true) {
            ANIMATE++;
        }
        return ANIMATE;
    }
}
