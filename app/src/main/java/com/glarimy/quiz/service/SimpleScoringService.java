package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

public  class SimpleScoringService implements ScoringService {
protected Score currentScore;


    @Override
    public boolean evaluate(Answer answer) {
        return false;
    }

    @Override
    public Score getCurrentScore() {
        return null;

    }
}
