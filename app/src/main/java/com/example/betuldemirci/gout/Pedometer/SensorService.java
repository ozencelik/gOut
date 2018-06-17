package com.example.betuldemirci.gout.Pedometer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.betuldemirci.gout.Activity.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fabio on 30/01/2016.
 */
public class SensorService extends Service implements SensorEventListener, StepListener{
    private static final String TAG = "SENSOR SERVICE";
    public Context ctx;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static int time=0;
    public static int counter=0;

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




    public SensorService(Context applicationContext) {
        super();
        Log.i("HERE", "here I am!");

        ctx = applicationContext;


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) ctx.getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();


        simpleStepDetector.registerListener(this);

        stepsCount = 0;
        //sensorManager.registerListener(applicationContext, accel, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);

    }

    public SensorService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        startTimer();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("EXIT", "ondestroy!");
        Intent broadcastIntent = new Intent("com.example.betuldemirci.gout.Pedometer.RestartSensor");
        sendBroadcast(broadcastIntent);
        stoptimertask();
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startTimer() {

        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 1000, 1000); //
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                counter = preferences.getInt("Counter", -1);

                Log.i("in timer", "in timer ++++  "+ (counter++));
                Log.i("11111111", "11111111 ++++  "+ (time++) + "\n");

                editor.putInt("Counter", counter);

                editor.commit();
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

        Log.i(TAG, TEXT_NUM_STEPS + "Adım: " + stepsCount + " Mesafe: " + formatter.format(distance) + " Kalori: " + formatter.format(CaloriesBurned));
        //Toast.makeText(ctx, "Adım: " + stepsCount + " Mesafe: " + formatter.format(distance) + "km Kalori: " + formatter.format(CaloriesBurned), Toast.LENGTH_SHORT).show();
        //mHomeFragment.updateStep(numSteps);
    }
}