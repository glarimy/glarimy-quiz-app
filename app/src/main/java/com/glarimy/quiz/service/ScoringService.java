package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

public interface ScoringService {
    /**
     * It returns the score
     * Parameters:
     * Answer:get the ticked option from Answer service.
     *
     * Return:gives the score after evaluating.
     */
    public Score evaluate(Answer answer);

}
