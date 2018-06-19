package com.example.betuldemirci.gout.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.betuldemirci.gout.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddNewChallengeActivity extends AppCompatActivity {
    private static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";

    private Button mDatePickerFrom, mDatePickerTo;
    private Calendar mCalendar;
    private DatePickerDialog.OnDateSetListener mDateFrom, mDateTo;
    private RelativeLayout[] mRels;
    private ImageView[] mStepNumberImages;

    private CircleImageView mFriendsList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_challenge);

        mDatePickerFrom = findViewById(R.id.date_picker_from);
        mDatePickerTo = findViewById(R.id.date_picker_to);

        mRels = new RelativeLayout[4];
        mRels[0] = findViewById(R.id.step_number1);
        mRels[1] = findViewById(R.id.step_number2);
        mRels[2] = findViewById(R.id.step_number3);
        mRels[3] = findViewById(R.id.step_number4);

        mStepNumberImages = new ImageView[4];
        mStepNumberImages[0] = findViewById(R.id.sn1);
        mStepNumberImages[1] = findViewById(R.id.sn2);
        mStepNumberImages[2] = findViewById(R.id.sn3);
        mStepNumberImages[3] = findViewById(R.id.sn4);

        mFriendsList = findViewById(R.id.add_friends);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mCalendar = Calendar.getInstance();
        dateClickListeners(0); dateClickListeners(1);

        mDatePickerFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewChallengeActivity.this, mDateFrom, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mDatePickerTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddNewChallengeActivity.this, mDateTo, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mFriendsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewChallengeActivity.this, ListFriendsToAddChallenge.class));
            }
        });

        StepNumbersRelativeLayout();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void dateClickListeners(int i){

        switch (i){
            case 0:
                mDateFrom = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, monthOfYear);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel(0);
                    }
                };
                break;


            case 1:

                mDateTo = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        mCalendar.set(Calendar.YEAR, year);
                        mCalendar.set(Calendar.MONTH, monthOfYear);
                        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel(1);
                    }
                };
                break;
        }
    }


    private void updateLabel(int i ) {

        //String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.US);

        switch (i) {
            case 0:
                mDatePickerFrom.setText(sdf.format(mCalendar.getTime()));
                break;
            case 1:
                mDatePickerTo.setText(sdf.format(mCalendar.getTime()));
                break;
        }

    }

    private void StepNumbersRelativeLayout(){

        mRels[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepNumberImages[0].setBackgroundResource(R.drawable.circle_shape_orange);
                mStepNumberImages[1].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[2].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[3].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
            }
        });

        mRels[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepNumberImages[0].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[1].setBackground(getResources().getDrawable(R.drawable.circle_shape_orange));
                mStepNumberImages[2].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[3].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
            }
        });

        mRels[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepNumberImages[0].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[1].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[2].setBackground(getResources().getDrawable(R.drawable.circle_shape_orange));
                mStepNumberImages[3].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
            }
        });

        mRels[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStepNumberImages[0].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[1].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[2].setBackground(getResources().getDrawable(R.drawable.circle_shape_gray));
                mStepNumberImages[3].setBackground(getResources().getDrawable(R.drawable.circle_shape_orange));
            }
        });

    }


}
