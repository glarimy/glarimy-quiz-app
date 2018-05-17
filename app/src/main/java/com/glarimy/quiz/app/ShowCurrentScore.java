package com.glarimy.quiz.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.glarimy.quiz.service.SimpleScoringService;

public class ShowCurrentScore extends Activity {

    TextView currentScoreView;

    SimpleScoringService simpleScoringService=new SimpleScoringService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_current_score);

        currentScoreView=findViewById(R.id.currentScoreId);
        currentScoreView.setText(""+simpleScoringService.getCurrentScore());

    }
}
