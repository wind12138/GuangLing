package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Build;
import android.util.AttributeSet;
import android.util.EventLog;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 朱峰 on 2017/11/27.
 */

public class week extends View {

    private final String TAG = "week";

    private int Rectwidth;
    private int Rectheight;
    private int a;
    private int m;

    private Calendar c;
    private static String mMonth;
    private static String mDay;
    private static String mWeek;
    private static String mYear;
    private String week_1;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint cPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mpath = new Path();

    String week [] = {"一","二","三","四","五"};
    String day [] = { "2","3","4","5","6"};

    private int RUAN_NIAN = 0;         // RUAN_NIAN------这里是29天
    private int PING_NIAN =1;          // PING_NIAN------这里是28天
    private int RUAN_YUE = 2;          // RUAN_YUE-------这里是31天
    private int PING_YUE = 3;          // PING_YUE-------这里是30天

    public enum Daies {
        RUAN_NIAN,PING_NIAN,RUAN_YUE,PING_YUE
    }

    public week(Context context) {
        this(context,null);
    }

    public week(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public week(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);

        init();
    }



    private void init(){
        //  初始画笔
        //第一支画笔
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        //第二支画笔
        cPaint.setColor(Color.RED);
        cPaint.setStrokeWidth(10);
        cPaint.setStyle(Paint.Style.FILL);
        //第三只笔
        bPaint.setStrokeWidth(5);
        bPaint.setColor(Color.BLACK);
        bPaint.setStyle(Paint.Style.FILL);
        bPaint.setTextSize(50);

        //获取当前日期
        if (Build.VERSION.SDK_INT >= 24) {
             c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            mYear = String.valueOf(c.get(Calendar.YEAR));                       //获取年份
            mMonth = String.valueOf(c.get(Calendar.MONTH)+1);                     //获取月份
            mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));                //获取第几天
            mWeek = String.valueOf(c.get(Calendar.DAY_OF_WEEK));                //获取星期几
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rectwidth = getWidth();
        Rectheight = getHeight()/10;
        canvas.drawRect(0, 0, Rectwidth, Rectheight, mPaint);
        canvas.drawRect(0,Rectheight,Rectwidth,10*Rectheight,mPaint);


        //绘制文字----第一行----周
        for (int i = 0; i < 5; i++) {
            if (day[i].equals(mWeek)) { //这里筛选到前星期
                week_1 = week[i];         //这里将mWeek改成中文数字 -------2017/11/30/星期四
                if (week[i].equals(week_1)) {
                    canvas.drawRect(i * Rectwidth / 5, 0, Rectwidth / 5 * (i + 1), Rectheight, cPaint);
                }
            }
            canvas.drawText("周" + week[i], Rectwidth / 5 * i + 50, Rectheight / 2, bPaint);
        }

        //绘制文字-----第二行------日
        int myear = Integer.parseInt(mYear);
        int mmoth = Integer.parseInt(mMonth);

        boolean panduan;
        int up_day = 28;
        int up_to_day;
        if (myear % 4 != 0) {                   //判断润年
            if (mmoth <= 7) {                 //把1~7月
                if (mmoth % 2 == 0 && mmoth != 2) {     //这是30天
                    up_to_day=up_day+ 3;
                    Daies m = Daies.RUAN_YUE;
                    riqi(canvas, m,up_to_day);
                } else if (mmoth % 2 != 0) {              //这是31天
                    if (mMonth.equals("3")) {
                        up_to_day = up_day;
                    }else{
                        up_to_day = up_day + 2;
                    }
                    Daies m = Daies.PING_YUE;
                    riqi(canvas, m,up_to_day);
                } else if (mMonth.equals("2")) {       //这是28天
                    up_to_day = up_day + 3;
                    Daies m = Daies.PING_NIAN;
                    riqi(canvas, m,up_to_day);
                }
            }
            else {                        //8~12月
                if (mmoth % 2 == 0) {                    //这是31天
                    if (mMonth.equals("8")) {
                        up_to_day = up_day + 3;
                    }
                    else {
                        up_to_day = up_day + 2;
                    }
                    Daies m = Daies.PING_YUE;
                    riqi(canvas, m,up_to_day);
                } else {                             //这是30天
                    up_to_day = up_day + 3;
                    Daies m = Daies.RUAN_YUE;
                    riqi(canvas, m,up_to_day);
                }
            }
        } else{
            if (mmoth <= 7) {                 //把1~7月
                if (mmoth % 2 == 0 && mmoth != 2) {     //这是30天
                    up_to_day=up_day+ 3;
                    Daies m = Daies.RUAN_YUE;
                    riqi(canvas, m,up_to_day);
                } else if (mmoth % 2 != 0) {              //这是31天
                    if (mMonth.equals("3")) {
                        up_to_day = up_day +1;
                    }else{
                        up_to_day = up_day + 2;
                    }
                    Daies m = Daies.RUAN_NIAN;
                    riqi(canvas, m,up_to_day);
                } else if (mMonth.equals("2")) {       //这是28天
                    up_to_day = up_day + 3;
                    Daies m = Daies.PING_NIAN;
                    riqi(canvas, m,up_to_day);
                }
            }
            else {                        //8~12月
                if (mmoth % 2 == 0) {                    //这是31天
                    if (mMonth.equals("8")) {
                        up_to_day = up_day + 3;
                    }
                    else {
                        up_to_day = up_day + 2;
                    }
                    Daies m = Daies.PING_YUE;
                    riqi(canvas, m,up_to_day);
                } else {                             //这是30天
                    up_to_day = up_day + 3;
                    Daies m = Daies.RUAN_YUE;
                    riqi(canvas, m,up_to_day);
                }
            }

        }
    }                               //这里绘制周


    private void riqi(Canvas canvas,Daies day,int up_to_day) {

        int weekday = 5;
        int mweek = Integer.parseInt(mWeek);         //当前星期几
        int mday = Integer.parseInt(mDay);          //当前几号
        if (mweek != 7 && mweek !=1) {
            a = weekday - (mweek - 1);  //这里的a意思是还省几天
            m = mweek - 1;
        }
        else {
            if (mWeek.equals("7")) {
                a = weekday - (mweek - 2);
                m = mweek - 2;
            }
            else if (mWeek.equals("1")) {
                a = weekday;
                m = 0;
            }
        }

            //这个确定当前几号
            for (int i = 0; i < 5; i++) {
                if (week[i].equals(week_1)) {
                    canvas.drawText(mDay + "日", Rectwidth / 5 * i + 50, Rectheight - 10, bPaint);
                    //这个确定前几天
                    for (int s = 1; s < m; s++) {
                        int date = mday - s;
                        if (date > 0) {
                            canvas.drawText(date + "日", Rectwidth / 5 * ((m-1)-s) + 50, Rectheight - 10, bPaint);
                        } else {
                            date = up_to_day + date;
                            canvas.drawText(date + "日", Rectwidth / 5 * ((m-1)-s) + 50, Rectheight - 10, bPaint);
                        }
                    }

                    //判断后面能否超出当月最大天数同时写出后几天
                    for (int c = 1; c <=a; c++) {
                        int ddate;
                        switch (day) {
                            case RUAN_NIAN: {
                                ddate = mday + c;
                                if (ddate <= 29) {
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                } else {
                                    ddate = ddate - 29;
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                }
                                break;
                            }
                            case PING_NIAN: {
                                ddate = mday + c;
                                if (ddate <= 28) {
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                } else {
                                    ddate = ddate - 28;
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                }
                                break;
                            }
                            case RUAN_YUE: {
                                ddate = mday + c;
                                if (ddate <= 30) {
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                } else {
                                    ddate = ddate - 30;
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                }
                                break;
                            }
                            case PING_YUE: {
                                ddate = mday + c;
                                if (ddate <= 31) {
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                } else {
                                    ddate = ddate - 31;
                                    canvas.drawText(ddate + "日", Rectwidth / 5 * (i+c) + 50, Rectheight - 10, bPaint);
                                }
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }
            }
        if (mWeek.equals("7")||mWeek.equals("1")){
            //这个确定前几天
            for (int s = 1; s <= m; s++) {
                int date = mday - s;
                if (date > 0) {
                    canvas.drawText(date + "日", Rectwidth / 5 * (m - s) + 50, Rectheight - 10, bPaint);
                } else {
                    date = up_to_day + date;
                    canvas.drawText(date + "日", Rectwidth / 5 * (m - s) + 50, Rectheight - 10, bPaint);
                }
            }

            //判断后面能否超出当月最大天数同时写出后几天
            for (int c = 1; c <= a; c++) {
                int ddate;
                switch (day) {
                    case RUAN_NIAN: {
                        ddate = mday + c;
                        if (ddate <= 29) {
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        } else {
                            ddate = ddate - 29;
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        }
                        break;
                    }
                    case PING_NIAN: {
                        ddate = mday + c;
                        if (ddate <= 28) {
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        } else {
                            ddate = ddate - 28;
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        }
                        break;
                    }
                    case RUAN_YUE: {
                        ddate = mday + c;
                        if (ddate <= 30) {
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        } else {
                            ddate = ddate - 30;
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        }
                        break;
                    }
                    case PING_YUE: {
                        ddate = mday + c;
                        if (ddate <= 31) {
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        } else {
                            ddate = ddate - 31;
                            canvas.drawText(ddate + "日", Rectwidth / 5 * (c - 1) + 50, Rectheight - 10, bPaint);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }

    }          //这里的绘制日期



}
