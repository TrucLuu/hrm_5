package com.example.fhrm.hrm_system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


/**
 * Created by luuhoangtruc on 26/01/2016.
 */
public class SplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    private ImageView imageSplash;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initialize();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void initialize(){
        imageSplash = (ImageView)findViewById(R.id.imageSplash);
        imageSplash.setImageResource(R.mipmap.logo);
    }
}
