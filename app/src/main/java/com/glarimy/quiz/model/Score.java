package com.glarimy.quiz.model;

import java.io.Serializable;

public class Score implements Serializable
{
    protected int numberOfPoints;
    protected int numberOfAttempts;

    public Score() {
    }


    //get and set methods for the property numberOfPoints
    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    //get and set methods for the property numberOfAttempts
    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }
}
