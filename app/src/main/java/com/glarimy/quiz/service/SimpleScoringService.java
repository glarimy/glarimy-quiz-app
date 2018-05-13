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
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public Score getCurrentScore()
    {
        Answer answer=new Answer();
        currentScore=new Score();
        if (evaluate(answer))
        {
            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints()+1);

        }else {
            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints());
        }
        currentScore.setNumberOfPoints(currentScore.getNumberOfAttempts()+1);
        return currentScore;
    }
}
