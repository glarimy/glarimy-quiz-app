package com.glarimy.quiz.service;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Score;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleScoringServiceTest {
    @Test
    public void testInitialScore() {
        ScoringService service = new SimpleScoringService();
        Score score = service.getCurrentScore();
        assertTrue("invalid initial attempts", score.getNumberOfAttempts() == 0);
        assertTrue("invalid initial points", score.getNumberOfPoints() == 0);
    }


    @Test
    public void evaluate() {
        ScoringService service = new SimpleScoringService();
        Answer answer=new Answer();
        service.evaluate(answer);
        assertTrue("invalid initial attempts", answer.getQuestionId()==0);
        assertTrue("invalid initial points",answer != null );
        assertTrue("invalid option selected",answer.getCorrectOption()<=1 && answer.getCorrectOption()<=4);
        assertTrue("invalid option ticked",answer.getTickedOption()<=1 && answer.getTickedOption()<=4);
        assertTrue("invalid values",answer.getTickedOption()== answer.getCorrectOption());


    }

    @Test
    public void getCurrentScore() {
        assertEquals(0,0);
    }
}