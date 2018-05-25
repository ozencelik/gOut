package com.example.betuldemirci.gout;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;
import me.itangqi.waveloadingview.WaveLoadingView;
import nl.dionsegijn.steppertouch.StepperTouch;



public class HomeFragment extends Fragment{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View v;

    private WaveLoadingView mWaveLoadingView;
    private StepperTouch mStepper;

    private String []mStartColors;
    private String []bgColors;

    private ArrayList<ArcProgressStackView.Model>  models;

    private ArcProgressStackView arcProgressStackView;
    private Typeface typoRounded;

    TextView stepCount;

    private static final String TAG = "AGFAN";


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        //DEFINITIONS
        mStartColors = getResources().getStringArray(R.array.devlight);
        bgColors = getResources().getStringArray(R.array.medical_express);
        typoRounded = Typeface.createFromAsset(v.getContext().getAssets(),  "fonts/typo grotesk rounded light demo.otf");
        mStepper = v.findViewById(R.id.stepperTouch);
        arcProgressStackView = v.findViewById(R.id.apsv);
        models = new ArrayList<>();
        mWaveLoadingView = v.findViewById(R.id.waveLoadingView);

        stepCount = v.findViewById(R.id.step_count);


        //SETT VALUES
        mStepper.stepper.setMax(10);
        mStepper.stepper.setMin(0);

        mStepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Water Num : "+mStepper.stepper.getValue(),Toast.LENGTH_SHORT).show();
            }
        });


        models.add(new ArcProgressStackView.Model("Adım", 25, Color.parseColor(bgColors[0]), Color.parseColor(mStartColors[0])));
        models.add(new ArcProgressStackView.Model("Kalori", 50, Color.parseColor(bgColors[1]), Color.parseColor(mStartColors[1])));
//        models.add(new ArcProgressStackView.Model("Stack", 75, Color.parseColor(bgColors[2]), Color.parseColor(mStartColors[2])));
//        models.add(new ArcProgressStackView.Model("View", 100, Color.parseColor(bgColors[3]), Color.parseColor(mStartColors[3])));

        arcProgressStackView.setDrawWidthDimension(60*models.size());
        arcProgressStackView.setModels(models);
        arcProgressStackView.setIsShadowed(false);
        arcProgressStackView.setIsDragged(false);
        arcProgressStackView.setTypeface(typoRounded);
        arcProgressStackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arcProgressStackView.animate();
            }
        });



        return v;
    }

    public void updateStep(int numberSteps){
        stepCount.setText(numberSteps);
    }

}
