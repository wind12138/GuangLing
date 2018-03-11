package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by 朱峰 on 2018/3/9.
 */

public class ButtonS_animate {
    public void ButtonS_animate(View view,String width,float a,float b) {
       ObjectAnimator animator = ObjectAnimator.ofFloat(view,width,a,b);
    }
}
