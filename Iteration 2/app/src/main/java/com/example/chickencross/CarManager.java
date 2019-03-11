package com.example.chickencross;

import android.graphics.Canvas;
//import android.support.constraint.ConstraintLayout;

import java.util.ArrayList;

public class CarManager {
    //higher index means lower on screen means higher y value
    private ArrayList<Car> cars = new ArrayList<Car>();
    private int playerGap;
    private int carGap;
    private int carHeight;
    private int color;
    private long startTime;

    public CarManager(int playerGap, int carGap, int carHeight, int color){
        this.playerGap = playerGap;
        this.carGap = carGap;
        this.carHeight = carHeight;
        this.color = color;

        startTime = System.currentTimeMillis();


        populateCars();
    }

    private void populateCars(){
        int currY = -5*Constants.SCREEN_HEIGHT/4;
        while(currY < 0){       //gets rectangles until on reaches bottom
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));        //makes sure objects are on screen
            cars.add(new Car(carHeight, color, xStart, currY, playerGap));      //adds a new rectangle to cars array
            currY += carHeight + carGap;
        }
    }

    public void draw(Canvas canvas){
        for(Car car : cars){
            car.draw(canvas);
        }
    }

    public void update(){
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;             //sets speed for cars to reach bottom of screen (10s)
        for(Car car : cars){
            car.incrementY(speed * elapsedTime);
        }
        if(cars.get(cars.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT){        //if goes off screen
            int xStart = (int) (Math.random()*(Constants.SCREEN_WIDTH - playerGap));
            cars.add(0, new Car(carHeight, color, xStart, cars.get(0).getRectangle().top + carHeight + carGap, playerGap));

        }
    }
}
