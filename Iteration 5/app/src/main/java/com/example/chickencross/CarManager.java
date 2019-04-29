package com.example.chickencross;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import android.support.constraint.ConstraintLayout;

import java.util.ArrayList;

public class CarManager {

    private ArrayList<Car> cars;
    private int playerGap;
    private int carGap;
    private int carHeight;
    private int color;
    private long startTime;
    private long initTime;
    private int score = 0;

    public CarManager(int playerGap, int carGap, int carHeight, int color){
        this.playerGap = playerGap;
        this.carGap = carGap;
        this.carHeight = carHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        cars = new ArrayList<>();

        populateCars();
    }

    public boolean playerCollide(Player player){
        for(Car c : cars){
            if(c.playerCollide(player)){
                return true;
            }
        }
        return false;
    }

    //function to populate the screen with obstacles
    private void populateCars(){
        int currY = -5*Constants.SCREEN_HEIGHT/4;       //the current y position will be initialized to 5/4 of the screen height
        while(currY < 0){       //gets rectangles until on reaches bottom
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));        //makes sure objects are on screen
            cars.add(new Car(carHeight, color, xStart, currY, playerGap));      //adds a new rectangle to cars array
            currY += carHeight + carGap;
        }
    }

    public void update(){
        if(startTime < Constants.INIT_TIME){
            startTime = Constants.INIT_TIME;
        }
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/2000.0))* Constants.SCREEN_HEIGHT/(10000.0f);             //sets speed for cars to reach bottom of screen (10s)
        for(Car car : cars){
            car.incrementY(speed * elapsedTime);
        }
        if(cars.get(cars.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT){        //if goes off screen
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            cars.add(0, new Car(carHeight, color, xStart, cars.get(0).getRectangle().top + carHeight + carGap, playerGap));
            cars.remove(cars.size() - 1);
            score++;
        }
    }

    public void draw(Canvas canvas){
        for(Car car : cars) {
            car.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);
        canvas.drawText(""+score, 50,50 + paint.descent() - paint.ascent(), paint);

    }
}
