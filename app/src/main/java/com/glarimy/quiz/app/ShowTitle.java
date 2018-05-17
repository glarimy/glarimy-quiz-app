package com.glarimy.quiz.app;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.glarimy.quiz.model.Question;
import com.glarimy.quiz.service.GlarimyQuestionService;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

public class ShowTitle extends Activity {
    TextView titleView, questionView, optionsView;
    //Button optionOne, optionTwo, optionThree, optionFour;

    int buttonIds[] = new int[]{R.id.optionIdOne, R.id.optionIdTwo, R.id.optionIdThree, R.id.optionIdFour};
    Button buttonViews[] = new Button[buttonIds.length];
    String optionText[];

    GlarimyQuestionService glarimyQuestionService = new GlarimyQuestionService(this);
    Question question = new Question();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_title);

        titleView = findViewById(R.id.titleId);

        questionView = findViewById(R.id.questionId);
        questionView.setVisibility(View.INVISIBLE);

        optionsView = findViewById(R.id.optionsId);
        optionsView.setVisibility(View.INVISIBLE);

        try {
            question = glarimyQuestionService.get();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (question != null) {
            titleView.setText(question.getTitle());
            questionView.setText(question.getDescription());

            optionText[0] = question.getOptionOne();
            optionText[1] = question.getOptionTwo();
            optionText[2] = question.getOptionThree();
            optionText[3] = question.getOptionFour();

            for (int i = 0; i < 4; i++) {
                buttonViews[i] = findViewById(buttonIds[i]);
                buttonViews[i].setVisibility(View.INVISIBLE);
                buttonViews[i].setText(optionText[i]);
            }

            Handler answerHandler = new Handler();
            int time = 500;
            int i1;
            for (i1 = 0; i1 <= 4; i1++) {
                final int finalI = i1;
                answerHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (finalI == 0) {
                            optionsView.setVisibility(View.VISIBLE);
                            buttonViews[finalI].setVisibility(View.VISIBLE);
                        } else if (finalI == 4)
                            questionView.setVisibility(View.VISIBLE);
                        else
                            buttonViews[finalI].setVisibility(View.VISIBLE);
                    }
                }, time = time + 1000);
            }


        }
    }


    public void onSubmit(View v)
    {
        int choosedButton=v.getId();

        for(int i=0;i<4;i++)
        {
            if(choosedButton==buttonIds[i])
                buttonViews[i].setBackgroundColor(Color.RED);
            else
                buttonViews[i].setEnabled(false);
        }



    }

}
