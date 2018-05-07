package com.example.betuldemirci.gout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.betuldemirci.gout.Helper.BottomNavigationViewBehavior;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;
    android.support.v4.app.FragmentManager manager;
    android.support.v4.app.FragmentTransaction transaction;

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
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.container, new DashboardFragment()).commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.container, new NotificationFragment()).commit();
                    return true;
                case R.id.navigation_accesebility:
                    transaction.replace(R.id.container, new AccesibleFragment()).commit();
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
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();

        layoutParams.setBehavior(new BottomNavigationViewBehavior());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container, new HomeFragment()).commit();
    }



}
