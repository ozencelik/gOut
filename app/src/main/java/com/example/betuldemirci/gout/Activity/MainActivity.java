package com.example.betuldemirci.gout.Activity;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Fragments.ChallengeFragment;
import com.example.betuldemirci.gout.Fragments.HomeFragment;
import com.example.betuldemirci.gout.Pedometer.SensorService;
import com.example.betuldemirci.gout.Pedometer.StepDetector;
import com.example.betuldemirci.gout.Pedometer.StepListener;
import com.example.betuldemirci.gout.Fragments.ProfileFragment;
import com.example.betuldemirci.gout.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PEDOMETER";
    android.support.v4.app.FragmentManager manager;
    android.support.v4.app.FragmentTransaction transaction;
    private BottomNavigationView mNavigation;

    public static ProgressDialog mProgressDialog;

    //FOR BACKGROUND STEP COUNT
    private Intent mServiceIntent;
    private SensorService mSensorService;
    private Context ctx;

    private HomeFragment mHomeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();

            mProgressDialog.show();
            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    mProgressDialog.dismiss();
                }
            };
            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 3000);


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.container, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_challenge:
                    transaction.replace(R.id.container, new ChallengeFragment()).commit();
                    return true;
                case R.id.navigation_account_circle:
                    transaction.replace(R.id.container, new ProfileFragment()).commit();
                    return true;
            }
            mProgressDialog.dismiss();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        //Progress Loading
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);

        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("    Loading...");


        mNavigation = findViewById(R.id.navigation);

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mSensorService = new SensorService(ctx);
        mServiceIntent = new Intent(ctx, mSensorService.getClass());
        if (!isMyServiceRunning(mSensorService.getClass())) {
            startService(mServiceIntent);
        }

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        mHomeFragment = new HomeFragment();

        transaction.replace(R.id.container, mHomeFragment).commit();

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i ("isMyServiceRunning?", true+"");
                return true;
            }
        }
        Log.i ("isMyServiceRunning?", false+"");
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        //sensorManager.unregisterListener(MainActivity.this);
        //setProgressBarIndeterminateVisibility(false);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

}
