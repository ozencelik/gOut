package com.example.betuldemirci.gout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;
import me.itangqi.waveloadingview.WaveLoadingView;
import nl.dionsegijn.steppertouch.StepperTouch;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View v;

    private OnFragmentInteractionListener mListener;
    private WaveLoadingView mWaveLoadingView;
    private StepperTouch mStepper;

    private String []mStartColors;
    private String []bgColors;

    private ArrayList<ArcProgressStackView.Model>  models;

    private ArcProgressStackView arcProgressStackView;
    private Typeface typoRounded;



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

        mStartColors = getResources().getStringArray(R.array.polluted_waves);
        bgColors = getResources().getStringArray(R.array.medical_express);

        typoRounded = Typeface.createFromAsset(v.getContext().getAssets(),  "fonts/typo grotesk rounded light demo.otf");

        mStepper = v.findViewById(R.id.stepperTouch);

        arcProgressStackView = v.findViewById(R.id.apsv);
        models = new ArrayList<>();

        mWaveLoadingView = v.findViewById(R.id.waveLoadingView);

        mStepper.stepper.setMax(10);
        mStepper.stepper.setMin(0);

        mStepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Water Num : "+mStepper.stepper.getValue(),Toast.LENGTH_SHORT).show();
            }
        });


        models.add(new ArcProgressStackView.Model("Circle", 25, Color.parseColor(bgColors[0]), Color.parseColor(mStartColors[0])));
        models.add(new ArcProgressStackView.Model("Progress", 50, Color.parseColor(bgColors[1]), Color.parseColor(mStartColors[1])));
        models.add(new ArcProgressStackView.Model("Stack", 75, Color.parseColor(bgColors[2]), Color.parseColor(mStartColors[2])));
        models.add(new ArcProgressStackView.Model("View", 100, Color.parseColor(bgColors[3]), Color.parseColor(mStartColors[3])));

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


//        mStartColors = getResources().getIntArray(R.array.polluted_waves);
//        bgColors = getResources().getIntArray(R.array.medical_express);
//
//
//        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
//        models.add(new ArcProgressStackView.Model("Circle", 25, bgColors[0],mStartColors[0]));
//        models.add(new ArcProgressStackView.Model("Progress", 50, bgColors[1], mStartColors[1]));
//        models.add(new ArcProgressStackView.Model("Stack", 75, bgColors[2], mStartColors[2]));
//        models.add(new ArcProgressStackView.Model("View", 100, bgColors[3], mStartColors[3]));
//
//        final ArcProgressStackView arcProgressStackView = v.findViewById(R.id.apsv);
//        arcProgressStackView.setModels(models);




//        Comment for Badge View and Pie Chart

//        mBadge = v.findViewById(R.id.badge);
//        mBadge.setNumber(10);
//
//        mWaveLoadingView = v.findViewById(R.id.waveLoadingView);
//        mPieChart = v.findViewById(R.id.piechart);
//
//        mPieChart.setUsePercentValues(true);
//        mPieChart.getDescription().setEnabled(true);
//        mPieChart.setTransparentCircleRadius(50f);
//
//        mPieChart.setTouchEnabled(false);
//
//        mPieChart.setExtraOffsets(5, 10, 5, 5);
//        mPieChart.setDragDecelerationFrictionCoef(0.95f);
//
//        mPieChart.setDrawHoleEnabled(true);
//        mPieChart.setHoleColor(Color.WHITE);
//        mPieChart.setTransparentCircleRadius(61f);
//        mPieChart.setMaxAngle(180);
//        mPieChart.setRotationAngle(180);
//
//        mPieChart.animateXY(1000, 1000);
//
//        ArrayList<PieEntry> yValues = new ArrayList<>();
//        yValues.add(new PieEntry(34f, "XXX"));
//        yValues.add(new PieEntry(23f, "Turkey"));
//        yValues.add(new PieEntry(54f, "Usa"));
//        yValues.add(new PieEntry(12f, "Germany"));
//
//
//        PieDataSet mDataSet = new PieDataSet(yValues, "Countries");
//        mDataSet.setSliceSpace(3f);
//        mDataSet.setSelectionShift(5f);
//        mDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//
//        PieData mData = new PieData(mDataSet);
//        mData.setValueTextSize(10f);
//        mData.setValueTextColor(Color.YELLOW);
//
//        mPieChart.setData(mData);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
