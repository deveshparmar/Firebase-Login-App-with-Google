package com.codewithdevesh.foodybitez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
  ImageView logo,splashImage;
  TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        splashImage = findViewById(R.id.image);
        appName = findViewById(R.id.appname);



//        splashImage.animate().translationY(-1600).setDuration(1000).setStartDelay(3000);
//        appName.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
//        logo.animate().translationY(1500).setDuration(1000).setStartDelay(3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
            }
        },3000);
    }
}