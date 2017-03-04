package com.withliyh.bigfont;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class FontMetricsDemoActivity extends Activity {
    private Canvas canvas;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageview1);
        Paint textPaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize( 80);
        textPaint.setColor( Color.RED);

        // FontMetrics对象
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        String text = "中文";



        // 计算每一个坐标
        float baseX = 0;
        float baseY = 100;
        float topY = baseY + fontMetrics.top;
        float ascentY = baseY + fontMetrics.ascent;
        float descentY = baseY + fontMetrics.descent;
        float bottomY = baseY + fontMetrics.bottom;
        float leading = baseY + fontMetrics.leading;


        Log.d("fontMetrics", "baseX    is:" + 0);
        Log.d("fontMetrics", "baseY    is:" + 100);
        Log.d("fontMetrics", "topY     is:" + topY);
        Log.d("fontMetrics", "ascentY  is:" + ascentY);
        Log.d("fontMetrics", "descentY is:" + descentY);
        Log.d("fontMetrics", "bottomY  is:" + bottomY);
        Log.d("fontMetrics", "leading  is:" + leading);



        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        canvas  = new Canvas(mutableBitmap);



        // 绘制文本
        canvas.drawText(text, baseX, baseY, textPaint);

        // BaseLine描画
        Paint baseLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);

        baseLinePaint.setColor( Color.RED);
        canvas.drawLine(0, baseY, canvas.getWidth(), baseY, baseLinePaint);

        // Base描画
        canvas.drawCircle( baseX, baseY, 5, baseLinePaint);

        // TopLine描画
        Paint topLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        topLinePaint.setColor( Color.LTGRAY);
        canvas.drawLine(0, topY, canvas.getWidth(), topY, topLinePaint);

        // AscentLine描画
        Paint ascentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        ascentLinePaint.setColor( Color.GREEN);
        canvas.drawLine(0, ascentY, canvas.getWidth(), ascentY, ascentLinePaint);

        // DescentLine描画
        Paint descentLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        descentLinePaint.setColor( Color.YELLOW);
        canvas.drawLine(0, descentY, canvas.getWidth(), descentY, descentLinePaint);

        // ButtomLine描画
        Paint bottomLinePaint = new Paint( Paint.ANTI_ALIAS_FLAG);
        bottomLinePaint.setColor( Color.MAGENTA);
        canvas.drawLine(0, bottomY, canvas.getWidth(), bottomY, bottomLinePaint);


        imageView.setImageBitmap(mutableBitmap);

    }
}