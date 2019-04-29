package com.example.chickencross;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private Player player;
    private Point playerPoint;
    private CarManager carManager;
    private Rect r = new Rect();
    private boolean playerMoving = false;
    private boolean gameOver = false;       //detects if player has crashed
    private long gameOverTime;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);

        player = new Player(new Rect(100,100,200,200), Color.rgb(255,239,251));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3 * Constants.SCREEN_HEIGHT/4);   //halfway down, three-fourths up
        player.update(playerPoint);

        carManager = new CarManager(200, 350, 75, Color.rgb(0,87,75));

        setFocusable(true);
    }

    public void reset(){
        playerPoint = new Point(Constants.SCREEN_WIDTH/2, 3 * Constants.SCREEN_HEIGHT/4);   //halfway down, three-fourths up
        player.update(playerPoint);
        carManager = new CarManager(200, 350, 75, Color.rgb(0,87,75));
        playerMoving = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        Constants.INIT_TIME = System.currentTimeMillis();
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch(Exception e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY())){
                    playerMoving = true;
                }
                if(gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameOver = false;       //resets game state to allow new game
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(!gameOver && playerMoving) {
                    playerPoint.set((int)event.getX(), (int)event.getY());      //gets user's position from motion event
                }
                break;
            case MotionEvent.ACTION_UP:
                playerMoving = false;
                break;
        }
        return true;
        //return super.onTouchEvent(event);
    }

    public void update(){
        if(!gameOver) {
            player.update(playerPoint);
            carManager.update();
            if(carManager.playerCollide(player)) {   //ending game if player has crashed
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.rgb(210,239,239));
        player.draw(canvas);
        carManager.draw(canvas);

        if(gameOver){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.DKGRAY);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text){
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text,0,text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

}
