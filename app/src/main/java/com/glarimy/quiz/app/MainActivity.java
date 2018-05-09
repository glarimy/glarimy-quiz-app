package com.glarimy.quiz.app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.glarimy.quiz.service.QuestionService;
public class MainActivity extends AppCompatActivity
{
    /**
     * <h1>onCreate</h1>
     * onCreate implements an application that
     * simply calls QuestionService interface.
     * @param savedInstanceState is reference to bundle object .
     * @return Nothing.
     *
     *
     *
     * @since   2018-05-09
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuestionService questionService=new QuestionService() {
            @Override
            public int get() {
                return 0;
            }

            @Override
            public int getAnswer() {
                return 0;
            }

            @Override
            public boolean isConntected(Context context) {
                return false;
            }
        };
        questionService.isConntected(this);
    }

}
