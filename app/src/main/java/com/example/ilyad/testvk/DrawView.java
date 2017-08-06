package com.example.ilyad.testvk;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return event.getAction() != MotionEvent.ACTION_DOWN || drawThread.core.tapped();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*Поток отрисовки*/

    class DrawThread extends Thread {
        SurfaceHolder surfaceHolder;
        private boolean running;
        Core core;

        DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            core = new Core();
        }

        void setRunning(boolean running) {
            this.running = running;
        }



        @Override
        public void run() {
            super.run();
            Canvas canvas;
            Paint p = new Paint();
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setTextSize(80);
            core = new Core();
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null) continue;
                    canvas.drawColor(Color.rgb(23,12,36));
//                    Paint clearPaint = new Paint();
//                    clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
//                    canvas.drawRect(0, 0, width, height, clearPaint);
                    p.setStrokeWidth(Core.widthLine);
                    if (!core.isGaveOver()) {
                        paintCurveLine(canvas, p, core);
                        paintSticks(canvas, p, core);
                        if (core.isStartGame()) {
                            p.setColor(Color.WHITE);
                            if (core.getScore() != core.getOldScore()) {
                                p.setTextSize(80);
                                core.countWane();
                            } else core.setCount(100);  // анимация для score
                            if (core.getCount() == 50) {
                                core.setOldScore(core.getScore());
                            }
                            canvas.drawText(String.valueOf(core.getScore()), Core.widthPanel / 2, 100, p);   // после 100 очков счет пишется также по центру
                        }

                    } else {
                        p.setColor(Color.rgb(232,220,0));
                        paintBang(canvas, p, core);
                    }
                    if (!core.isStartGame()) {
                        p.setColor(Color.WHITE);
                        p.setTextSize(50);
                        canvas.drawText(core.getText(), (Core.widthPanel - core.getText().length() * 20) / 2, 100, p);
                    }

                } finally {
                    if (canvas != null) surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }

    }

    private void paintCurveLine(Canvas canvas, Paint p, Core core) {
        int widthCometa = Core.widthLine;
        for (int i = 1; i < core.getLine().getCount(); i++) {

            p.setStrokeWidth(widthCometa);
            if (i % 3 != 0) p.setColor(Color.CYAN);
            else p.setColor(Color.RED);
            canvas.drawLine(core.getLine().getLineX()[i - 1], core.getLine().getLineY()[i - 1], core.getLine().getLineX()[i], core.getLine().getLineY()[i], p);
        }
    }

    private void paintSticks(Canvas canvas, Paint p, Core core) {
        p.setColor(Color.parseColor("#02eff7"));
        for (Stick element : core.getSticks()) {
            int y = element.getY();
            canvas.drawLine(element.getX1(), y, element.getX2(), y, p);
            if (element.isDoubleSticks()) {
                canvas.drawLine(element.getX3(), y, element.getX4(), y, p);
            }
        }
    }

    private void paintBang(Canvas canvas, Paint p, Core core) {
        if (core.getCount() < Core.heightPanel * 2) {
            int radius = core.getCount() / 2;
            canvas.drawOval(core.getLine().getX1() - radius, core.getLine().getY1() - radius, core.getCount(), core.getCount(), p);
            core.setCount(core.getCount() + 40);
        } else paintResult(canvas, p, core);
    }

    private void paintResult(Canvas canvas, Paint p, Core core) {
        canvas.drawText("Game over", 150, 150, p);
        p.setColor(Color.WHITE);
        if (core.getScore() > core.getMaxScore()) {
            core.setMaxScore(core.getScore());
            canvas.drawText("you new record: " + core.getScore(), 120, 300, p);
        } else {
            canvas.drawText("Best scrore: " + core.getMaxScore(), 100, 400, p);
            p.setStrokeWidth(35);
            canvas.drawText("You scrore: " + core.getScore(), 150, 300, p);
        }
        p.setStrokeWidth(20);
        canvas.drawText("Tap to replay", 170, 500, p);
    }

}

