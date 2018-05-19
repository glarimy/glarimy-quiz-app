package com.glarimy.quiz.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.glarimy.quiz.service.GlarimyQuestionService;

public class ShowErrorMessage extends Activity {
    GlarimyQuestionService glarimyQuestionService = new GlarimyQuestionService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_error_message);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ShowErrorMessage.this, ShowTitle.class));
                finish();
            }
        }, 1000);


    }
}
