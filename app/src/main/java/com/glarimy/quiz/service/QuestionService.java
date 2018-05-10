package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;

/**
 * *This service helps to get the data from cloud.
 *Author:venu
 * Since:2018
 *
 */
public interface QuestionService {
    /**
     * Returns a question when quiz is started.
     * Parameters:None
     *
     * Return:question gets when called
     */
    public Question get();

    /**
     *
     * Returns the answer
     * Parameters:
     * questionid:for every we have a unique id base on id we retrive the answer.
     * Return:the answer
     */
    public Answer getAnswer(int questionid);

    /**
     *It returns the network status of the user.
     * Parameters:None
     * Return:user is having active connection or not
     */
    public boolean isConntected();
}
