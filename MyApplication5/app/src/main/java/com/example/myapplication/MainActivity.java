package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ExditText focus_ExditText_input;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_1);
       // Inputinit();
        //ExditTextinit();
    }

    /*
    private void Inputinit() {
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void ExditTextinit() {
        focus_ExditText_input = (ExditText) findViewById(R.id.Layout_ExidtText);
        focus_ExditText_input.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Layout_ExidtText:
                if (button_animate.boolean_onclick == true) {
                    focus_ExditText_input.setFocusable(true);
                    focus_ExditText_input.setFocusableInTouchMode(true);
                    focus_ExditText_input.setCursorVisible(true);
                    focus_ExditText_input.requestFocus();
                    focus_ExditText_input.findFocus();
                    mInputMethodManager.showSoftInput(focus_ExditText_input, InputMethodManager.SHOW_FORCED);
                }
        }
    }

    */
}
