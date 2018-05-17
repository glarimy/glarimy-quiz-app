package com.glarimy.quiz.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowTitle extends Activity
{

    TextView title,question;
    Button optionOne,optionTwo,optionThree,optionFour;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_title);

        title=findViewById(R.id.titleView);
        title=findViewById(R.id.questionView);

        optionOne=findViewById(R.id.optionOne);
        optionTwo=findViewById(R.id.optionTwo);
        optionThree=findViewById(R.id.optionThree);
        optionFour=findViewById(R.id.optionFour);



    }


}
