package com.withliyh.bigfont;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * 自动调整字体大小充满组建显示区域
 * Created by liy on 03/03/2017.
 */

public class AutoSizeTextView extends TextView {
    private Paint mPaint;

    private float mTextSize;

    public AutoSizeTextView(Context context) {
        super(context);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void refitText(String text, int viewWidth, int viewHeight) {


        mPaint = getPaint();


//获得当前TextView的有效宽度

        int availableWidth = viewWidth - this.getPaddingLeft() - this.getPaddingRight();
        int availableHeight = viewHeight - this.getPaddingTop() - this.getPaddingBottom();

        Rect rect = new Rect();

        mPaint.getTextBounds(text, 0, text.length(), rect);

//所有字符串所占像素宽度

        int textWidths = rect.width();

        mTextSize = this.getTextSize();//这个返回的单位为px
        while (textWidths < availableWidth) {
            mTextSize = mTextSize + 1;
            mPaint.setTextSize(mTextSize);//这里传入的单位是px
            mPaint.getTextBounds(text, 0, text.length(), rect);
            textWidths = rect.width();
        }

        int textHeights = rect.height();
        while (textHeights > availableHeight) {
            mTextSize = mTextSize - 1;
            mPaint.setTextSize(mTextSize);
            mPaint.getTextBounds(text, 0, text.length(), rect);
            textHeights = rect.height();
        }



        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);//这里制定传入的单位是px

    }


    @Override

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        refitText(getText().toString(), this.getWidth(), this.getHeight());

    }

}
