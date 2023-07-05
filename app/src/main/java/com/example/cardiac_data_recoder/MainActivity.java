package com.example.cardiac_data_recoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=3500;

    Animation topAnim,bottomAnim;

    GifImageView imageView;
    TextView headline, details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makefullscreen app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //animation
         topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
         bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

         imageView=findViewById(R.id.splashScreenImg);
         headline=findViewById(R.id.textViewHeadline);
         details=findViewById(R.id.textViewDetails);

         imageView.setAnimation(topAnim);
         headline.setAnimation(bottomAnim);
         details.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void  run()
            {
                Intent intent = new Intent(MainActivity.this, InsertReportActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);





    }
}