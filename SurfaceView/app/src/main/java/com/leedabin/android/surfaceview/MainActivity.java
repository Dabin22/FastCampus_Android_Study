package com.leedabin.android.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    int devicewidth = 0;
    int deviceHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        devicewidth = metrics.widthPixels;
        deviceHeight = metrics.heightPixels;

        CustomSurfaceView surfaceView = new CustomSurfaceView(this);
        setContentView(surfaceView);
    }


    public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        private SurfaceThread thread;

        public CustomSurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            thread = new SurfaceThread(getHolder());
            setFocusable(true);
            thread.setDaemon(true);

        }

        public CustomSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        //뷰에 포커스가 생겼다.
        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if(!thread.isAlive())
                thread.start();
        }

        //뷰에 변경 사항이 생겼다.(사이즈 변경 등...)
        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

        }

        //화면이 생명주기가 끝났다.
        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            boolean retry = true;
            while (retry) {
                thread.runnig = false;
                try {
                    thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public class SurfaceThread extends Thread {
        SurfaceHolder holder;
        Paint paint = new Paint();
        int x = 0, y = 0;
        boolean runnig = true;

        public SurfaceThread(SurfaceHolder holder) {
            //surface뷰에서 넘겨준 홀더를 가지고 작업을 한다.
            this.holder = holder;
        }

        @Override
        public void run() {
            //무한 반복하면서 그림을 그려준다.
            //그림을 그릴 캔버스를 가져오고

            while (runnig) {
                Canvas canvas = null;
                try {
                    //그림을 그릴
                    synchronized (holder) {
                        canvas = holder.lockCanvas();
                        if (x > 0) {
                            paint.setColor(Color.BLACK);
                            canvas.drawRect(x - 1, x - 1, x + 51, y + 51, paint);
                        }
                        paint.setColor(Color.BLUE);
                        canvas.drawRect(x, y, 50 + x, 50 + y, paint);

                    }
                    x++;
                    y++;
                    if (x > devicewidth - 50 || y > deviceHeight - 50) {
                        x = 0;
                        y = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null)
                        holder.unlockCanvasAndPost(canvas);
                }

            }
        }
    }
}
