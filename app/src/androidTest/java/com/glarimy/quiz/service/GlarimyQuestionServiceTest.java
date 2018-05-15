package com.glarimy.quiz.service;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

class GlarimyQuestionServiceTest
{
    Context appContext = InstrumentationRegistry.getTargetContext();
    QuestionService service = new GlarimyQuestionService(appContext);

    @SmallTest
    public void testQuestion() {
        if (service.isConnected()) {
            Question question = service.get();
            assertTrue("Invalid question is returned", question != null);
            assertTrue("Invalid question id is returned", question.getId() >= 0);
            assertTrue("Invalid question description is returned", question.getDescription() != null);
            assertTrue("empty question description is returned", question.getDescription().trim().length() != 0);
            assertTrue("Invalid first option is returned", question.getOptionOne() != null);
            assertTrue("empty first option is returned", question.getOptionOne().trim().length() != 0);
            assertTrue("Invalid second option is returned", question.getOptionTwo() != null);
            assertTrue("empty second option is returned", question.getOptionTwo().trim().length() != 0);
            assertTrue("Invalid third option is returned", question.getOptionThree() != null);
            assertTrue("empty third option is returned", question.getOptionThree().trim().length() != 0);
            assertTrue("invalid fourth option is returned", question.getOptionFour() != null);
            assertTrue("empty fourth option is returned", question.getOptionFour().trim().length() != 0);

        } else {
            Question question = service.get();
            assertTrue("Question available without connection", question == null);
        }

    }


    public void testGetAnswer()
    {
        Question question = new Question();
        Answer answer = service.getAnswer(question.getId());

        if (service.isConnected()) {
            assertTrue("Invalid question id is returned", answer.getQuestionId() < 0);
        } else {
            answer = service.getAnswer(question.getId());
            assertTrue("answer available without internet connection", answer == null);
            assertTrue("answer will be returned",(answer.getCorrectOption()==question.getId())&& (answer.getCorrectOption()<=1&&answer.getCorrectOption()>=4));
        }

    }

    private void testIsConnected()
    {

        service.isConnected();

    }
}