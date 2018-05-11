package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

public  class SimpleScoringService implements ScoringService {
protected int numberOfPoints;
protected int numberOfAttempts;

    @Override
    public Score evaluate(Answer answer) {
        int val=answer.getCorrectOption();
       int val1=answer.getTickedOption();
       numberOfPoints=0;
        Score score=new Score();
        score.setNumberOfPoints(numberOfPoints);

        return score;
    }
}
