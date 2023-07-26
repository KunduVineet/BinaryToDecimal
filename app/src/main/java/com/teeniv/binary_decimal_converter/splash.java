package com.teeniv.binary_decimal_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    TextView txtAnim;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent iHome = new Intent(splash.this, MainActivity.class);
                startActivity(iHome);
            }
        },3000);

        txtAnim = findViewById(R.id.txtAnim);

        Animation move = AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        txtAnim.startAnimation(move);

    }
}