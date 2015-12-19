package com.zhengxiaoyao0716.zxingtest.zxingutil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by zhengxiaoyao0716 on 2015/11/26.
 */
public class ScanView extends View {
    private Paint paint;
    ScanView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int left = canvas.getWidth() / 2 - 200;
        int top = canvas.getHeight() / 2 - 200;
        int right = canvas.getWidth() / 2 + 200;
        int bottom = canvas.getHeight() / 2 + 200;

        paint.setARGB(100, 0, 0, 0);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,              0,              left,               canvas.getHeight(),     paint);
        canvas.drawRect(right,          0,              canvas.getWidth(),  canvas.getHeight(),     paint);
        canvas.drawRect(left,           0,              right,              top,                    paint);
        canvas.drawRect(left,           bottom,         right,              canvas.getHeight(),     paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(left,           top,            right,              bottom,                 paint);
    }
}
