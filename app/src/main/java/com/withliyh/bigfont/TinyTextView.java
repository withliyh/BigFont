package com.withliyh.bigfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;


/**
 * TextView比较重量，只是显示单行文本可以更轻量
 * Created by liy on 2016/8/4.
 */
public class TinyTextView extends View {

    private TextPaint mDrawPaint;
    private int mTextSize; //文本大小
    private int mTextColor;//文本颜色
    private String mText;//文本
    private Rect textBoundRect = new Rect();//文本边界

    public TinyTextView(Context context) {
        super(context);
        initPaint();
    }

    public TinyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeStyle(context, attrs);
        initPaint();
    }

    public TinyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeStyle(context, attrs);
        initPaint();
    }

    private void initTypeStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TinyTextView);
        this.mTextSize = a.getDimensionPixelSize(R.styleable.TinyTextView_textSize, 16);
        this.mTextColor = a.getColor(R.styleable.TinyTextView_textColor, Color.GREEN);
        CharSequence charSequence = a.getText(R.styleable.TinyTextView_text);
        this.mText = charSequence != null ? charSequence.toString() : "";
        a.recycle();
    }

    private void initPaint() {
        this.mDrawPaint = new TextPaint();
        this.mDrawPaint.setAntiAlias(true);
        this.mDrawPaint.setColor(this.mTextColor);
        this.mDrawPaint.setTextSize(this.mTextSize);
        this.mDrawPaint.setTextAlign(Paint.Align.LEFT);

    }

    public void setText(String mText) {
        this.mText = mText;
        requestLayout();
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public void setTextSize(int size) {
        this.mTextSize = size;
        this.mDrawPaint.setTextSize(size);
        requestLayout();
    }

    public void setTextColor(int color) {
        this.mTextColor = color;
        this.mDrawPaint.setColor(color);
        requestLayout();
    }

    private int calcSize(int measureSpec, int defaultSize) {
        int resultSize = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                resultSize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                resultSize = defaultSize;
                break;
            case MeasureSpec.EXACTLY:
                resultSize = size;
        }
        return resultSize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        this.mDrawPaint.getTextBounds(this.mText, 0, this.mText.length(), textBoundRect);
        int w = calcSize(widthMeasureSpec, (int)(textBoundRect.width() + 0.5));
        int h = calcSize(heightMeasureSpec, textBoundRect.height());
        setMeasuredDimension(w, h);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        refitText(mText, getWidth(), getHeight());
        float baseX = canvas.getWidth() / 2 - textBoundRect.width() / 2;
        float baseY = canvas.getHeight()/2-(mDrawPaint.descent()+mDrawPaint.ascent())/2;

        // 绘制文本
        canvas.drawText(mText, baseX, baseY, mDrawPaint);
    }

    private void refitText(String text, int viewWidth, int viewHeight) {

//获得当前TextView的有效宽度

        int availableWidth = viewWidth - this.getPaddingLeft() - this.getPaddingRight();
        int availableHeight = viewHeight - this.getPaddingTop() - this.getPaddingBottom();

        Rect rect = new Rect();

        mDrawPaint.getTextBounds(text, 0, text.length(), rect);
//所有字符串所占像素宽度

        int textWidths = rect.width();

        while (textWidths < availableWidth) {
            mTextSize = mTextSize + 1;
            mDrawPaint.setTextSize(mTextSize);//这里传入的单位是px
            mDrawPaint.getTextBounds(text, 0, text.length(), rect);
            textWidths = rect.width();
        }

        int textHeights = rect.height();
        while (textHeights > availableHeight) {
            mTextSize = mTextSize - 1;
            mDrawPaint.setTextSize(mTextSize);
            mDrawPaint.getTextBounds(text, 0, text.length(), rect);
            textHeights = rect.height();
        }

    }

}