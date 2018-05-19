package com.glarimy.quiz.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;
import com.glarimy.quiz.service.QuestionService;
public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
