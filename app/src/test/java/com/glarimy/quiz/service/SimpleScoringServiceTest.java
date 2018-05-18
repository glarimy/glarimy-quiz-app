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
        


    }

    @Test
    public void getCurrentScore() {
        assertEquals(0,0);
    }
}