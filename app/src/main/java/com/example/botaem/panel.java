package com.example.botaem;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class panel extends View {
    public panel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    float x, y;
    float r = 20;

    ArrayList<Point> p  = new ArrayList<Point>();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint c = new Paint();
        c.setColor(Color.BLUE);

        canvas.drawCircle(x, y, r, c);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float xx = event.getX();
        float yy = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: //Нажатие
                r = 20;
                p.clear();
                p.add(new Point((int)xx, (int)yy));
                return true;
            case MotionEvent.ACTION_MOVE: //Движение
                p.add(new Point((int)xx, (int)yy));
                return true;
            case MotionEvent.ACTION_UP: //Отпускание
                drawshape();
                return true;
        }

        return true;
    }

    public void drawshape(){
        Thread thread = new Thread("New Thread") {
            public void run(){
                for (Point i : p)
                {
                    x = i.x;
                    y = i.y;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    invalidate();
                }
                r = 0;
                invalidate();
            }
        };
        thread.start();
    }
}
