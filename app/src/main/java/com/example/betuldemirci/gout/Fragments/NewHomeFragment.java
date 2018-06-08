package com.example.betuldemirci.gout.Fragments;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Adapters.RecyclerViewAdapter;
import com.example.betuldemirci.gout.Model;
import com.example.betuldemirci.gout.R;

import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;


public class NewHomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private View v;

    public static String[] mStartColors;
    public static String[] bgColors;
    public static Typeface typoRounded;

    private RecyclerView StackView, Cards;

    private ArrayList<Model> mList;

    TextView minsNumber, timeType;
    Button runningButton;

    WaveLoadingView mWave;
    TextView typeNumber, typeName;
    StepperTouch mStepper;
    double dailyWater = 3;
    double goalWater = 10;
    String waterType = "glass";

    ImageView mImage;
    TextView weightAmount;
    Button weightButton;
    int imageId = R.drawable.weight_icon_lbs;
    double kg = 50.2;



    private static final String TAG = "AGFAN";


    public NewHomeFragment() {
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
        v = inflater.inflate(R.layout.fragment_new_home, container, false);


        //DEFINITIONS
        mStartColors = getResources().getStringArray(R.array.devlight);
        bgColors = getResources().getStringArray(R.array.medical_express);
        typoRounded = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/typo grotesk rounded light demo.otf");



        mList= new ArrayList<>();
        mList.add(new Model(Model.STACK_TYPE, 4560, 6000, 56));


        StackView = v.findViewById(R.id.stack_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        //LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        RecyclerViewAdapter firstAdapter = new RecyclerViewAdapter(mList, getContext());
        StackView.setLayoutManager(mLayoutManager);
        StackView.setItemAnimator(new DefaultItemAnimator());
        StackView.setAdapter(firstAdapter);


        //////////////////////////////////////////////////////////////////////////////////

        minsNumber = v.findViewById(R.id.mins_number);
        timeType = v.findViewById(R.id.time_type);
        runningButton = v.findViewById(R.id.runningButton);

        minsNumber.setText(String.valueOf(15));
        timeType.setText("mins");
        runningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        mWave = v.findViewById(R.id.waveLoadingView);
        typeNumber = v.findViewById(R.id.goal_water);
        typeName = v.findViewById(R.id.goal_type);
        mStepper = v.findViewById(R.id.stepperTouch);

        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));

        typeNumber.setText(String.valueOf((int)goalWater));
        typeName.setText(waterType);

        mStepper.stepper.setMax((int)goalWater);
        mStepper.stepper.setMin(0);
        mStepper.stepper.setValue((int)dailyWater);
        mStepper.stepper.addStepCallback(new OnStepCallback() {
            @Override
            public void onStep(int value, boolean positive) {

                if(positive)plusWaveLoadingView();
                else minusWaveLoadingView();

            }
        });



        mImage = v.findViewById(R.id.weightType);
        weightAmount = v.findViewById(R.id.weightAmount);
        weightButton = v.findViewById(R.id.button);

        mImage.setImageResource(imageId);
        weightAmount.setText(String.valueOf(kg));
        /*
        mList= new ArrayList<>();
        mList.add(new Model(Model.RUNNING_TYPE, 15, "mins"));
        mList.add(new Model(Model.WATER_TYPE, 3, 10,"glass"));
        mList.add(new Model(Model.WEIGHT_TYPE, R.drawable.weight_icon_kg, 50.2));
        mList.add(new Model(Model.RUNNING_TYPE, 15, "mins"));
        mList.add(new Model(Model.WATER_TYPE, 3, 10,"glass"));
        mList.add(new Model(Model.WEIGHT_TYPE, R.drawable.weight_icon_lbs, 50.2));
        mList.add(new Model(Model.RUNNING_TYPE, 15, "mins"));
        mList.add(new Model(Model.WEIGHT_TYPE, R.drawable.weight_icon_lbs, 50.2));
        mList.add(new Model(Model.WATER_TYPE, 3, 10,"glass"));
        mList.add(new Model(Model.RUNNING_TYPE, 15, "mins"));
        mList.add(new Model(Model.WATER_TYPE, 3, 10,"glass"));


        Cards = v.findViewById(R.id.activities_recycler_view);
        //LinearLayoutManager nLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        LinearLayoutManager nLayoutManager = new GridLayoutManager(getActivity(), 3);
        Cards.setLayoutManager(nLayoutManager);
        //Cards.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(5), true));
        Cards.setItemAnimator(new DefaultItemAnimator());

        RecyclerViewAdapter nAdapter = new RecyclerViewAdapter(mList, getContext());

        Cards.setAdapter(nAdapter);*/


        return v;
    }

    public void plusWaveLoadingView(){
        dailyWater++;
        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));
        //Toast.makeText(getActivity(), "girdi",Toast.LENGTH_SHORT).show();
    }

    public void minusWaveLoadingView(){
        dailyWater--;
        mWave.setProgressValue((int)((dailyWater / goalWater) * 100));
        //Toast.makeText(getActivity(), "girdi",Toast.LENGTH_SHORT).show();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
