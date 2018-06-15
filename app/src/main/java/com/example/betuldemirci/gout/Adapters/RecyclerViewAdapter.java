package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.betuldemirci.gout.Fragments.HomeFragment;
import com.example.betuldemirci.gout.Model.HomeFragmentModel;
import com.example.betuldemirci.gout.R;

import java.util.ArrayList;

import devlight.io.library.ArcProgressStackView;
import me.itangqi.waveloadingview.WaveLoadingView;
import nl.dionsegijn.steppertouch.StepperTouch;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private HomeFragment mNew = new HomeFragment();


    private Activity mActivity;
    private ArrayList<HomeFragmentModel> mDataSet;
    Context mContext;
    int TOTAL_TYPES;
    private static final String TAG = "RecyclerView Adapter";


    public static class StackTypeViewHolder extends RecyclerView.ViewHolder {
        TextView dailyStep;
        TextView goalStep;
        ArcProgressStackView mArc;
        ArrayList<ArcProgressStackView.Model> mModels;


        public StackTypeViewHolder(View itemView) {
            super(itemView);
            this.dailyStep = itemView.findViewById(R.id.step_count);
            this.goalStep = itemView.findViewById(R.id.goal_step);
            this.mArc = itemView.findViewById(R.id.apsv);

        }

    }

    public static class RunningTypeViewHolder extends RecyclerView.ViewHolder {

        TextView minsNumber;
        TextView timeType;
        Button button;

        public RunningTypeViewHolder(View itemView) {
            super(itemView);

            this.minsNumber = itemView.findViewById(R.id.mins_number);
            this.timeType = itemView.findViewById(R.id.time_type);
            this.button = itemView.findViewById(R.id.button);
        }

    }

    public static class WaterTypeViewHolder extends RecyclerView.ViewHolder {

        WaveLoadingView mWave;
        TextView typeNumber;
        TextView typeName;
        StepperTouch mStepper;
        Button button;

        public WaterTypeViewHolder(View itemView) {
            super(itemView);

            this.mWave = itemView.findViewById(R.id.waveLoadingView);
            //this.typeNumber = itemView.findViewById(R.id.goal_water);
            //this.typeName = itemView.findViewById(R.id.goal_type);
            this.mStepper = itemView.findViewById(R.id.stepperTouch);

            //this.button = itemView.findViewById(R.id.button);
        }

    }

    public static class WeightTypeViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView weightAmount;
        Button button;

        public WeightTypeViewHolder(View itemView) {
            super(itemView);
/*
            this.mImage = itemView.findViewById(R.id.weightType);
            this.weightAmount = itemView.findViewById(R.id.weightAmount);
            this.button = itemView.findViewById(R.id.button);*/
        }

    }


    public RecyclerViewAdapter(ArrayList<HomeFragmentModel> data, Context context) {
        this.mDataSet = data;
        this.mContext = context;
        TOTAL_TYPES = mDataSet.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HomeFragmentModel.STACK_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_stack_view, parent, false);
                return new StackTypeViewHolder(view);
            case HomeFragmentModel.RUNNING_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_running_view, parent, false);
                return new RunningTypeViewHolder(view);
            case HomeFragmentModel.WATER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_water_view, parent, false);
                return new WaterTypeViewHolder(view);
            case HomeFragmentModel.WEIGHT_TYPE:
                //view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_deneme, parent, false);
                //return new WeightTypeViewHolder(view);
                break;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (mDataSet.get(position).type) {
            case 0:
                return HomeFragmentModel.STACK_TYPE;
            case 1:
                return HomeFragmentModel.RUNNING_TYPE;
            case 2:
                return HomeFragmentModel.WATER_TYPE;
            case 3:
                return HomeFragmentModel.WEIGHT_TYPE;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final HomeFragmentModel object = mDataSet.get(position);
        if (object != null) {
            switch (object.type) {
                case HomeFragmentModel.STACK_TYPE:

                    ((StackTypeViewHolder) holder).dailyStep.setText(String.valueOf((int) object.dailyStep) + "/");
                    ((StackTypeViewHolder) holder).goalStep.setText(String.valueOf((int) object.goalStep));

                    double a = (object.dailyStep / object.goalStep) * 100;
                    ((StackTypeViewHolder) holder).mModels = new ArrayList<>();
                    ((StackTypeViewHolder) holder).mModels.add(new ArcProgressStackView.Model("Adım", (int) a, Color.parseColor(mNew.bgColors[0]), Color.parseColor(mNew.mStartColors[0])));
                    ((StackTypeViewHolder) holder).mModels.add(new ArcProgressStackView.Model("Kalori", (int) object.calori, Color.parseColor(mNew.bgColors[1]), Color.parseColor(mNew.mStartColors[1])));
                    //        models.add(new ArcProgressStackView.Model("Stack", 75, Color.parseColor(bgColors[2]), Color.parseColor(mStartColors[2])));
                    //        models.add(new ArcProgressStackView.Model("View", 100, Color.parseColor(bgColors[3]), Color.parseColor(mStartColors[3])));

                    ((StackTypeViewHolder) holder).mArc.setDrawWidthDimension(60 * ((StackTypeViewHolder) holder).mModels.size());
                    ((StackTypeViewHolder) holder).mArc.setModels(((StackTypeViewHolder) holder).mModels);
                    ((StackTypeViewHolder) holder).mArc.setIsShadowed(false);
                    ((StackTypeViewHolder) holder).mArc.setIsDragged(false);
                    ((StackTypeViewHolder) holder).mArc.setTypeface(mNew.typoRounded);
                    ((StackTypeViewHolder) holder).mArc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((StackTypeViewHolder) holder).mArc.animate();
                        }
                    });

                    break;
                case HomeFragmentModel.RUNNING_TYPE:

                    ((RunningTypeViewHolder) holder).minsNumber.setText(String.valueOf(object.typeNumber));
                    ((RunningTypeViewHolder) holder).timeType.setText(object.typeName);
                    break;
                case HomeFragmentModel.WATER_TYPE:

                    ((WaterTypeViewHolder) holder).mWave.setProgressValue((object.dailyWater / object.goalWater) * 10);

                    //((WaterTypeViewHolder) holder).typeNumber.setText(String.valueOf(object.goalWater));
                    //((WaterTypeViewHolder) holder).typeName.setText(object.waterType);

                    ((WaterTypeViewHolder) holder).mStepper.stepper.setMax(object.goalWater);
                    ((WaterTypeViewHolder) holder).mStepper.stepper.setMin(0);
                    ((WaterTypeViewHolder) holder).mStepper.stepper.setValue(object.dailyWater);
                    ((WaterTypeViewHolder) holder).mStepper.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(getA(), "Water Num : "+((WaterTypeViewHolder) holder).mStepper.stepper.getValue(),Toast.LENGTH_SHORT).show();
                            ((WaterTypeViewHolder) holder).mWave.setProgressValue((object.dailyWater / object.goalWater) * 10);
                        }
                    });

                    break;
                case HomeFragmentModel.WEIGHT_TYPE:
/*
                    ((WeightTypeViewHolder) holder).mImage.setImageResource(object.imageWeight);
                    ((WeightTypeViewHolder) holder).weightAmount.setText(String.valueOf(object.weight));*/

                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


}
