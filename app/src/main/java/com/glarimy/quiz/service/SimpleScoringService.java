package com.glarimy.quiz.service;

import android.util.Log;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

public class SimpleScoringService implements ScoringService {
    protected Score currentScore = new Score();
    boolean status;

    public SimpleScoringService() {
        currentScore.setNumberOfPoints(0);
        currentScore.setNumberOfAttempts(0);
    }


    @Override
    public boolean evaluate(Answer answer) {
        if (answer == null) {
            return false;
        }/*else if (answer.getQuestionId() == 0){
            return false;
        } else if (answer.getCorrectOption()>=1 && answer.getCorrectOption()<=4){
            return true;
        }else if (answer.getTickedOption()>=1 && answer.getTickedOption()<=4){
            return true;
        }else
            */
        if (answer.getTickedOption() == answer.getCorrectOption()) {
            Log.d("in simple scoring", "");
            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints() + 1);
            currentScore.setNumberOfAttempts(currentScore.getNumberOfAttempts() + 1);
            return true;
        } else {
            currentScore.setNumberOfPoints(currentScore.getNumberOfPoints());
            currentScore.setNumberOfAttempts(currentScore.getNumberOfAttempts() + 1);
            return false;
        }
    }

    @Override
    public Score getCurrentScore() {
        return currentScore;
    }
}
