package com.example.cardiac_data_recoder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.cardiac_data_recoder.auth.loginactivity1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=3500;

    Animation topAnim,bottomAnim;

    FirebaseAuth mAuth;
    GifImageView imageView;
    TextView headline, details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
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
                FirebaseUser user = mAuth.getCurrentUser();
                if (user == null){
                    Intent intent=new Intent(MainActivity.this, loginactivity1.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(MainActivity.this, DataViewerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3500);
    }
}