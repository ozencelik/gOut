package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Activity.MainActivity;
import com.example.betuldemirci.gout.Activity.OnBoardingActivity;
import com.example.betuldemirci.gout.Model.OnBoardingModel;
import com.example.betuldemirci.gout.R;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.ThemeManager;
import com.rey.material.widget.Slider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.betuldemirci.gout.Activity.OnBoardingActivity.mCurrentPage;

public class OnBoardingSliderAdapter extends PagerAdapter  {

    private static final String TAG = "OnBoardingSliderAdapter";
    private OnBoardingModel mOnModel = new OnBoardingModel();
    public static OnBoardingActivity mOnActivity = new OnBoardingActivity();

    private Context mContext;

    private double KgDistance;
    private TextView mWeeksNumber, mWeeksStatus, mPerWeeks;
    private EditText[] mEditText = new EditText[4];
    private double[] mDouble = new double[4];

    public static LinearLayout[] mLin = new LinearLayout[3];
    public static Button male;
    public static Button female;

    public OnBoardingSliderAdapter(Context context){
        this.mContext = context;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null;
        int mLayoutId = -1;

        switch (position) {
            case 0:
                mLayoutId = R.layout.goal_type;
                view = inflater.inflate(mLayoutId, container, false);

                mLin[0] = view.findViewById(R.id.be_healtier);
                mLin[1] = view.findViewById(R.id.lose_weight);
                mLin[2] = view.findViewById(R.id.gain_weight);

                mLin[0].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { mOnModel.setmGoalType("Be Healtier"); mOnActivity.mSliderViewPager.setCurrentItem(mCurrentPage + 1);}});
                mLin[1].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { mOnModel.setmGoalType("Lose Weight"); mOnActivity.mSliderViewPager.setCurrentItem(mCurrentPage + 1);}});
                mLin[2].setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { mOnModel.setmGoalType("Gain Weight"); mOnActivity.mSliderViewPager.setCurrentItem(mCurrentPage + 1);}});

                break;
            case 1:
                mLayoutId = R.layout.sex_type;
                view = inflater.inflate(mLayoutId, container, false);

                male = view.findViewById(R.id.male); female = view.findViewById(R.id.female);
                male.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { mOnModel.setmSexType("Male");  mOnActivity.mSliderViewPager.setCurrentItem(mCurrentPage + 1);}});
                female.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { mOnModel.setmSexType("Female"); mOnActivity.mSliderViewPager.setCurrentItem(mCurrentPage + 1); }});
                break;
            case 2:
                mLayoutId = R.layout.other_info_type;
                view = inflater.inflate(mLayoutId, container, false);

                mEditText[0] = view.findViewById(R.id.user_age); mEditText[1] = view.findViewById(R.id.user_height);
                mEditText[2] = view.findViewById(R.id.user_weight); mEditText[3] = view.findViewById(R.id.user_goal_weight);

                final Slider mSlider = view.findViewById(R.id.slider);

                mWeeksNumber = view.findViewById(R.id.weeks_number); mWeeksStatus = view.findViewById(R.id.weeks_status); mPerWeeks = view. findViewById(R.id.per_week);

                addListeners();

                mSlider.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
                    @Override
                    public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
                        mOnModel.setmWeeksGoalWeight(newValue);
                        MessageProvider(newValue);
                    }
                });
                break;

        }
        container.addView(view, 0);

        return view;
    }

    public void MessageProvider(int mNewVal){
        double result = KgDistance / mNewVal;
        result = Math.floor(result * 100) / 100;

        mWeeksNumber.setText(mNewVal + " Weeks -");
        mPerWeeks.setText(result + " kg / week");

        if(result > 0.8){
            mWeeksStatus.setText("Dangerous");
            mWeeksStatus.setTextColor(Color.parseColor("#FF3B30"));
        }

        else if(result > 0.4 && result < 0.8){
            mWeeksStatus.setText("Recomended");
            mWeeksStatus.setTextColor(Color.parseColor("#66ff66"));
        }
        else if( result < 0.4) {
            mWeeksStatus.setText("Easy");
            mWeeksStatus.setTextColor(Color.parseColor("#0BCCE6"));
        }

    }

    public void addListeners(){
        mEditText[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mEditText[0].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(mEditText[0].getText().toString().length() > 0){
                    mDouble[0] = Double.parseDouble(mEditText[0].getText().toString());
                    mOnModel.setmAge(mDouble[0]);
                }
            }
        });
        mEditText[1].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(mEditText[1].getText().toString().length() > 0) {
                    mDouble[1] = Double.parseDouble(mEditText[1].getText().toString());
                    mOnModel.setmHeight(mDouble[1]);
                }
            }
        });
        mEditText[2].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(mEditText[2].getText().toString().length() > 0) {
                    mDouble[2] = Double.parseDouble(mEditText[2].getText().toString());
                    KgDistance = Math.abs(mDouble[3] - mDouble[2]);
                    mOnModel.setmCurrentWeight(mDouble[2]);
                }
            }
        });
        mEditText[3].addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(mEditText[3].getText().toString().length() > 0) {
                    mDouble[3] = Double.parseDouble(mEditText[3].getText().toString());
                    KgDistance = Math.abs(mDouble[3] - mDouble[2]);
                    mOnModel.setmGoalWeight(mDouble[3]);
                }
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return 3;//TOTAL_TYPES;
    }


}
