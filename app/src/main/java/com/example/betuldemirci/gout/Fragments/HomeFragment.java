package com.example.betuldemirci.gout.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Adapters.RecyclerViewAdapter;
import com.example.betuldemirci.gout.Model.ExerciseData;
import com.example.betuldemirci.gout.Model.HomeFragmentModel;
import com.example.betuldemirci.gout.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;


public class HomeFragment extends Fragment {

    private static final String TAG = "HOME FRAGMENT : ";

    private View v;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter firstAdapter;
    private RecyclerView StackView;
    private ArrayList<HomeFragmentModel> mList;

    public static String[] mStartColors;
    public static String[] bgColors;
    public static Typeface typoRounded;

    //Comment Yaz Şunlara
    TextView minsNumber, timeType;
    Button runningButton;

    WaveLoadingView mWave;
    TextView typeNumber, typeName;
    StepperTouch mStepper;
    double dailyWater;
    double goalWater;
    String waterType = "glass";

    int dailyStep, dailyGoalStep, calorie;

    ImageView mImage;
    TextView weightAmount;
    Button weightButton;
    int imageId = R.drawable.weight_icon_kg;
    double kg;


    private DatabaseReference mDatabaseReference;
    private String mUserId;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        Log.i(TAG, "onCreateView");

        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        //DEFINITIONS
        mStartColors = getResources().getStringArray(R.array.devlight);
        bgColors = getResources().getStringArray(R.array.medical_express);
        typoRounded = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/typo_grotesk_rounded_light_demo.otf");

        mList= new ArrayList<>();

        StackView = v.findViewById(R.id.stack_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        firstAdapter = new RecyclerViewAdapter(mList, getContext());

        //////////////////////////////////////////////////////////////////////////////////

        minsNumber = v.findViewById(R.id.mins_number);
        timeType = v.findViewById(R.id.time_type);
        runningButton = v.findViewById(R.id.runningButton);

        mWave = v.findViewById(R.id.waveLoadingView);
        typeNumber = v.findViewById(R.id.goal_water);
        typeName = v.findViewById(R.id.goal_type);
        mStepper = v.findViewById(R.id.stepperTouch);

        mImage = v.findViewById(R.id.weightType);
        weightAmount = v.findViewById(R.id.weightAmount);
        weightButton = v.findViewById(R.id.button);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        getExerciseData();

        mList.add(new HomeFragmentModel(HomeFragmentModel.STACK_TYPE, 0, 0, calorie));

        StackView.setLayoutManager(mLayoutManager);
        StackView.setItemAnimator(new DefaultItemAnimator());
        StackView.setAdapter(firstAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

        runningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mStepper.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int value, boolean positive) {

                if(positive)plusWaveLoadingView();
                else minusWaveLoadingView();

                //mStepper.stepper.setValue((int)dailyWater);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy : DAILY WATE : "+dailyWater);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(mUserId).child("mExerciseData").child("mDailyWater");
        mDatabaseReference.setValue(--dailyWater);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }

    public void setExerciseData(){

        mList.clear();
        mList.add(new HomeFragmentModel(HomeFragmentModel.STACK_TYPE, dailyStep, dailyGoalStep, calorie));

        StackView.setLayoutManager(mLayoutManager);
        StackView.setItemAnimator(new DefaultItemAnimator());
        StackView.setAdapter(firstAdapter);

        minsNumber.setText(String.valueOf(15));
        timeType.setText("mins");

        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));

        typeNumber.setText(String.valueOf((int)goalWater));
        typeName.setText(waterType);

        mStepper.stepper.setMax((int)goalWater);
        mStepper.stepper.setMin(0);
        mStepper.stepper.setValue((int)dailyWater);
        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));

        mImage.setImageResource(imageId);
        weightAmount.setText(String.valueOf(kg));

    }

    public void getExerciseData(){

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    final ExerciseData ex = new ExerciseData();

                    ex.setmDailyStep(ds.child(mUserId).child("mExerciseData").child("mDailyStep").getValue(int.class));
                    ex.setmDailyStepGoal(ds.child(mUserId).child("mExerciseData").child("mDailyStepGoal").getValue(int.class));
                    ex.setmDailyWater(ds.child(mUserId).child("mExerciseData").child("mDailyWater").getValue(int.class));
                    ex.setmDailyWaterGoal(ds.child(mUserId).child("mExerciseData").child("mDailyWaterGoal").getValue(int.class));
                    ex.setmDailyStepGoal(ds.child(mUserId).child("mExerciseData").child("mDailyStepGoal").getValue(int.class));
                    ex.setmDailyWater(ds.child(mUserId).child("mExerciseData").child("mDailyWater").getValue(int.class));
                    ex.setmCalori(ds.child(mUserId).child("mExerciseData").child("mCalori").getValue(int.class));

                    kg = ds.child(mUserId).child("mWeight").getValue(double.class);
                    dailyWater = ex.getmDailyWater();
                    dailyWater--;
                    goalWater = ex.getmDailyWaterGoal();
                    dailyStep = ex.getmDailyStep();
                    dailyGoalStep = ex.getmDailyStepGoal();
                    calorie = ex.getmCalori();

                    setExerciseData();

                    //Toast.makeText(getContext(), "Girdi : "+kg+" "+dailyWater+" "+goalWater, Toast.LENGTH_SHORT).show();

                }

                setExerciseData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void plusWaveLoadingView(){
        dailyWater++;
        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));
        Log.i(TAG, "onDestroy : DAILY WATER PLUS : "+dailyWater);
    }

    public void minusWaveLoadingView(){
        dailyWater--;
        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));
        Log.i(TAG, "onDestroy : DAILY WATER MINUS : "+dailyWater);
    }


}
