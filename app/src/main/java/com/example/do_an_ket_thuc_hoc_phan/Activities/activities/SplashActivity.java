package com.example.do_an_ket_thuc_hoc_phan.Activities.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.do_an_ket_thuc_hoc_phan.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView imgbackground,imgmaybay;
    private RatingBar ratingBar;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         imgbackground = findViewById(R.id.background);
         imgmaybay = findViewById(R.id.maybay);
         ratingBar = findViewById(R.id.ratingbar);
         txt = findViewById(R.id.txt);
        Animation slide_aim = AnimationUtils.loadAnimation(this,R.anim.slide_im);
        Animation bottom_aim = AnimationUtils.loadAnimation(this,R.anim.bottom_im);
        imgmaybay.setAnimation(bottom_aim);
        imgbackground.setAnimation(slide_aim);
        txt.setAnimation(slide_aim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },4000);
    }
}