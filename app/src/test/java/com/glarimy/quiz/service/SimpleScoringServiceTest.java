package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleScoringServiceTest {

    @Test
    public void evaluate() {
        SimpleScoringService simpleScoringService=new SimpleScoringService();
       Answer a1=new Answer();
       a1.setCorrectOption(0);

       Score score=simpleScoringService.evaluate(a1);
       int sc=score.getNumberOfPoints();

       int expected=0;
        assertEquals(expected,sc);
    }
}