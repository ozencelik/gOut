package com.example.betuldemirci.gout.Activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Fragments.ChallengeFragment;
import com.example.betuldemirci.gout.Fragments.HomeFragment;
import com.example.betuldemirci.gout.Pedometer.StepDetector;
import com.example.betuldemirci.gout.Pedometer.StepListener;
import com.example.betuldemirci.gout.Fragments.ProfileFragment;
import com.example.betuldemirci.gout.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {

    private static final String TAG = "PEDOMETER";
    private TextView mTextMessage;
    android.support.v4.app.FragmentManager manager;
    android.support.v4.app.FragmentTransaction transaction;
    private CoordinatorLayout mCoordinator;
    private BottomNavigationView mNavigation;
    private CoordinatorLayout.LayoutParams mLayoutParams;

    private String countedStep, detectedStep;

    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";

    static double weight = 96.0; // kg
    static double height = 183.0; // cm
    static double stepsCount = 0;
    final static double walkingFactor = 0.57;
    static double CaloriesBurnedPerMile;
    static double strip;
    static double stepCountKm; // step/km
    static double conversationFactor;
    static double CaloriesBurned;
    static NumberFormat formatter = new DecimalFormat("#0.00");

    static double distance;

    private HomeFragment mHomeFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
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
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Progress Loading
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(true);

        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        mNavigation = findViewById(R.id.navigation);
        //mCoordinator = findViewById(R.id.container);
        //mLayoutParams = (CoordinatorLayout.LayoutParams) mNavigation.getLayoutParams();
        //mLayoutParams.setBehavior(new BottomNavigationViewBehavior());

        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        stepsCount = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        mHomeFragment = new HomeFragment();

        transaction.replace(R.id.container, mHomeFragment).commit();

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(MainActivity.this);
        setProgressBarIndeterminateVisibility(false);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        stepsCount++;

        CaloriesBurnedPerMile = walkingFactor * (weight * 2.2);
        strip = height * 0.415;
        //stepCountMile = 160934.4 / strip;
        stepCountKm = 100000.0 / strip;
        conversationFactor = CaloriesBurnedPerMile / stepCountKm;
        CaloriesBurned = stepsCount * conversationFactor;
        distance = (stepsCount * strip) / 100000;

        Log.d(TAG, TEXT_NUM_STEPS + "Adım: " + stepsCount + " Mesafe: " + formatter.format(distance) + " Kalori: " + formatter.format(CaloriesBurned));
        Toast.makeText(MainActivity.this, "Adım: " + stepsCount + " Mesafe: " + formatter.format(distance) + "km Kalori: " + formatter.format(CaloriesBurned), Toast.LENGTH_SHORT).show();
        //mHomeFragment.updateStep(numSteps);
    }

}
