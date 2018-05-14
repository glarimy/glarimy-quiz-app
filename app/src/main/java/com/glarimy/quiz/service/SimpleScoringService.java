package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

public  class SimpleScoringService implements ScoringService {
protected Score currentScore;
    @Override
    public boolean evaluate(Answer answer) {
       int  val= answer.getTickedOption();
        int val1=answer.getCorrectOption();
        if(val==val1)
        {

            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints()+1);
            currentScore.setNumberOfAttempts(currentScore.getNumberOfAttempts()+1);
            return true;
        }
        else
        {
            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints());
            currentScore.setNumberOfAttempts(currentScore.getNumberOfAttempts()+1);
            return false;
        }

    }

    @Override
    public Score getCurrentScore()
    {
        //current score
        return currentScore;
    }
}
