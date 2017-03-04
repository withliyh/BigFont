package com.withliyh.bigfont;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.view.View;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String text = getIntent().getStringExtra("text");
        if (false) {
            setContentView(R.layout.activity_main);
            TinyTextView textView = (TinyTextView) findViewById(R.id.fullscreen_content);
            textView.setText(text);
            textView.setTextColor(Color.RED);
            textView.setText(text);
            textView.setTextSize(100);
            textView.setBackgroundColor(Color.WHITE);
            return;
        }
        setContentView(R.layout.activity_main_ex);

        final TextView textView = (TextView) findViewById(R.id.textContent);
        //textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        textView.setTextColor(Color.RED);
        textView.setText(text);
        textView.setTextSize(20);
        //textView.setBackgroundColor(Color.WHITE);
        textView.setTypeface(Typeface.MONOSPACE);
        if (false) {return;}



        textView.post(new Runnable() {
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


                boolean hasEmoji = false;
                for (int i= 0; i<text.length(); i++) {
                    if (!isNotEmojiCharacter(text.charAt(i))) {
                        hasEmoji = true;
                        break;
                    }
                }

                float scaleVl;
                if (!hasEmoji) {
                    scaleVl = scaleFact(containerWidth, containerHeight, textWidth, textHeight);
                } else {
                    scaleVl = scaleFact(containerWidth, containerHeight, viewWidth, viewHeight);

                }

                textView.setScaleX(scaleVl);
                textView.setScaleY(scaleVl);



//                Animation scaleAnimation = new ScaleAnimation(1.0f, scaleVl, 1.0f, scaleVl,
//                        Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
//                scaleAnimation.setDuration(2000);
//                scaleAnimation.setFillAfter(true);
//                textView.startAnimation(scaleAnimation);


            }
        });
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

    /**
     * 判断是否是非Emoji字符之外的正常字符，正常字符返回true
     * @param codePoint
     * @return
     */

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD));
    }
}
