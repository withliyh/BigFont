package com.withliyh.bigfont;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ex);
        final String text = getIntent().getStringExtra("text");
        final TextView textView = (TextView) findViewById(R.id.textContent);
        textView.setTextColor(Color.RED);
        textView.setText(text);
        textView.setTextSize(20);
        textView.setBackgroundColor(Color.WHITE);

       // if (true) {return;}

        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect rect = new Rect();
                textView.getPaint().getTextBounds(text, 0, text.length(), rect);

                int textWidth = rect.width();
                int textHeight = rect.height();

                int viewWidth = textView.getWidth();
                int viewHeight = textView.getHeight();

                int containerWidth = ((View)(textView.getParent())).getWidth();
                int containerHeight = ((View)(textView.getParent())).getHeight();

                float adjestWl = viewWidth / (float)textWidth;
                float adjestHl = viewHeight/ (float)textHeight;

                float scaleVl = scaleFact(containerWidth, containerHeight, textWidth, textHeight);
                textView.setScaleX(scaleVl);
                textView.setScaleY(scaleVl);



            }
        }, 1);
//        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//
//
//            }
//        });

    }

    private float scaleFact(float containerWidth, float containerHeight, float viewWidth, float viewHeight) {
        float wi = containerWidth / viewWidth;
        float hi = containerHeight / viewHeight;
        return wi > hi ? hi : wi;
    }
}
