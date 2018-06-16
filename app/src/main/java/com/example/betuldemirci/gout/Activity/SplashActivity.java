package com.example.betuldemirci.gout.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.betuldemirci.gout.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "Splash Activity";

    private Thread timerThread;
    private ImageView mImageView;

    private static FirebaseAuth mAuth;
    private static FirebaseAuth.AuthStateListener mAuthStateListener;
    private boolean isUserExist = false;
    private FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        //hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);


        mImageView = findViewById(R.id.gout);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = mAuth.getCurrentUser();

                if(mUser != null) isUserExist = true;
                else isUserExist = false;

            }
        };

        timerThread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if(isUserExist){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        SplashActivity.this.finish();
                        return;
                    }else{
                        startActivity(new Intent(SplashActivity.this, VideoActivity.class));
                        SplashActivity.this.finish();
                        return;
                    }
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        timerThread.start();

        mAuth.addAuthStateListener(mAuthStateListener);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        if(mUser != null) isUserExist = true;
        else isUserExist = false;

        Toast.makeText(SplashActivity.this, "isUserExist : "+isUserExist, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        //finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}