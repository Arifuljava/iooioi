package com.grozziie.testingApplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitish.typewriterview.TypeWriterView;

import maes.tech.intentanim.CustomIntent;
public class Splashscreen extends AppCompatActivity {

    //View Variables
    private ImageView appLogo, pattern1, pattern2;
    private TextView  poweredBy, developerDepository;
    TypeWriterView appSlogan;

    //Other Variables
    private Animation topAnimation, bottomAnimation, startAnimation, endAnimation;
    private SharedPreferences onBoardingPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getColor(R.color.colorBackground));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        initViews();
        initAnimation();
    }

    private void initViews() {
        //Initialize Views
        appLogo = findViewById(R.id.app_logo);
        pattern1 = findViewById(R.id.pattern1);
        pattern2 = findViewById(R.id.pattern2);
        appSlogan = findViewById(R.id.app_slogan);
        poweredBy = findViewById(R.id.powered_by);
        developerDepository = findViewById(R.id.developer_depository);
        //Setting each character animation delay
        appSlogan.animateText("Grozziie");
        appSlogan.setCharacterDelay(300);
        appSlogan.setOnAnimationChangeListener(new TypeWriterView.OnAnimationChangeListener() {
            @Override
            public void onAnimationEnd() {
                //Do something
                appSlogan.isAnimationRunning(); //returns true if animation is still running
                appSlogan.stopAnimation(); //Stop the ongoing animation
                appSlogan.isTextInitialised(); //returns false if animation is not started
            }
        });

        //Remove Animation. This is required to be called when you want to minimize the app while animation is going on. Call this in onPause() or onStop()

    }

    private void initAnimation() {
        //Initialize Animations
        topAnimation = AnimationUtils.loadAnimation(Splashscreen.this, R.anim.splash_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(Splashscreen.this, R.anim.splash_bottom_animation);
        startAnimation = AnimationUtils.loadAnimation(Splashscreen.this, R.anim.splash_start_animation);
        endAnimation = AnimationUtils.loadAnimation(Splashscreen.this, R.anim.splash_end_animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        int SPLASH_TIMER = 4000;

        //Set Preferences
        onBoardingPreference = getSharedPreferences("onBoardingPreference", MODE_PRIVATE);
        final boolean isFirstTime = onBoardingPreference.getBoolean("firstTime", true);

        //Set Animation To Views
        appLogo.setAnimation(topAnimation);
        pattern1.setAnimation(startAnimation);
        pattern2.setAnimation(endAnimation);
        appSlogan.setAnimation(bottomAnimation);
        poweredBy.setAnimation(bottomAnimation);
        developerDepository.setAnimation(bottomAnimation);

        new Handler().postDelayed(() -> {
            if (isFirstTime) {
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = onBoardingPreference.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();

                Intent intent = new Intent(Splashscreen.this, ForSliderPurpose .class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                CustomIntent.customType(Splashscreen.this, "fadein-to-fadeout");
                finish();
            } else {
                Intent intent = new Intent(Splashscreen.this, ForSliderPurpose.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                CustomIntent.customType(Splashscreen.this, "fadein-to-fadeout");
                finish();
            }
        }, SPLASH_TIMER);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
