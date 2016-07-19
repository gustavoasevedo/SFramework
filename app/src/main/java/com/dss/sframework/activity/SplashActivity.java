package com.dss.sframework.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.dss.sframework.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends ActionBarActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @AfterViews
    void afterViews() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                continueApplication();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    public void continueApplication(){
        Intent mainIntent = new Intent(SplashActivity.this,MainActivity_.class);
        SplashActivity.this.startActivity(mainIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        SplashActivity.this.finish();
    }
}