package com.glarimy.quiz.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.glarimy.quiz.model.Score;
import com.glarimy.quiz.service.GlarimyQuestionService;
import com.glarimy.quiz.service.SimpleScoringService;

public class ShowCurrentScore extends Activity {
    SimpleScoringService simpleScoringService = new SimpleScoringService();
    GlarimyQuestionService glarimyQuestionService = new GlarimyQuestionService(this);

    TextView scoreView;
    Score score = new Score();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_current_score);

        Toast.makeText(this, "" + score.getNumberOfPoints(), Toast.LENGTH_LONG).show();
        score = simpleScoringService.getCurrentScore();

        scoreView = findViewById(R.id.currentScoreId);
        scoreView.setText("" + score.getNumberOfPoints());
        if (glarimyQuestionService.isConnected()) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent showTitle = new Intent(ShowCurrentScore.this, ShowTitle.class);
                    startActivity(showTitle);
                    finish();
                }
            }, 2000);
        } else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(ShowCurrentScore.this, ShowErrorMessage.class));
                    finish();
                }
            }, 2000);
        }
    }
}
