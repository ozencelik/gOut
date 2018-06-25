package com.example.betuldemirci.gout.Model;

public class ExerciseData {

    private int mDailyStep, mCalori;
    private int mDailyStepGoal = 6000;

    private int mDailyWater;
    private int mDailyWaterGoal = 10;

    private int mTotalStep;
    private double mMaxDistance;//km
    private int mNumberOfActivities;

    private int mMaxStepInOneDay, mMaxDurationInOneDay, mMaxCalorieInOneDay, mMaxElevationInOneDay;
    private double mLongestDistanceInOneDay, mFastestSpeedInOneDay;

    public ExerciseData(){

    }

    public ExerciseData(int mDailyStep, int mCalori, int mDailyStepGoal, int mDailyWater, int mDailyWaterGoal, int mTotalStep, double mMaxDistance, int mNumberOfActivities, int mMaxStepInOneDay, int mMaxDurationInOneDay, int mMaxCalorieInOneDay, int mMaxElevationInOneDay, double mLongestDistanceInOneDay, double mFastestSpeedInOneDay) {
        this.mDailyStep = mDailyStep;
        this.mCalori = mCalori;
        this.mDailyStepGoal = mDailyStepGoal;
        this.mDailyWater = mDailyWater;
        this.mDailyWaterGoal = mDailyWaterGoal;
        this.mTotalStep = mTotalStep;
        this.mMaxDistance = mMaxDistance;
        this.mNumberOfActivities = mNumberOfActivities;
        this.mMaxStepInOneDay = mMaxStepInOneDay;
        this.mMaxDurationInOneDay = mMaxDurationInOneDay;
        this.mMaxCalorieInOneDay = mMaxCalorieInOneDay;
        this.mMaxElevationInOneDay = mMaxElevationInOneDay;
        this.mLongestDistanceInOneDay = mLongestDistanceInOneDay;
        this.mFastestSpeedInOneDay = mFastestSpeedInOneDay;
    }

    public int getmDailyStep() {
        return mDailyStep;
    }

    public void setmDailyStep(int mDailyStep) {
        this.mDailyStep = mDailyStep;
    }

    public int getmCalori() {
        return mCalori;
    }

    public void setmCalori(int mCalori) {
        this.mCalori = mCalori;
    }

    public int getmDailyStepGoal() {
        return mDailyStepGoal;
    }

    public void setmDailyStepGoal(int mDailyStepGoal) {
        this.mDailyStepGoal = mDailyStepGoal;
    }

    public int getmDailyWater() {
        return mDailyWater;
    }

    public void setmDailyWater(int mDailyWater) {
        this.mDailyWater = mDailyWater;
    }

    public int getmDailyWaterGoal() {
        return mDailyWaterGoal;
    }

    public void setmDailyWaterGoal(int mDailyWaterGoal) {
        this.mDailyWaterGoal = mDailyWaterGoal;
    }

    public int getmTotalStep() {
        return mTotalStep;
    }

    public void setmTotalStep(int mTotalStep) {
        this.mTotalStep = mTotalStep;
    }

    public double getmMaxDistance() {
        return mMaxDistance;
    }

    public void setmMaxDistance(double mMaxDistance) {
        this.mMaxDistance = mMaxDistance;
    }

    public int getmNumberOfActivities() {
        return mNumberOfActivities;
    }

    public void setmNumberOfActivities(int mNumberOfActivities) {
        this.mNumberOfActivities = mNumberOfActivities;
    }

    public int getmMaxStepInOneDay() {
        return mMaxStepInOneDay;
    }

    public void setmMaxStepInOneDay(int mMaxStepInOneDay) {
        this.mMaxStepInOneDay = mMaxStepInOneDay;
    }

    public int getmMaxDurationInOneDay() {
        return mMaxDurationInOneDay;
    }

    public void setmMaxDurationInOneDay(int mMaxDurationInOneDay) {
        this.mMaxDurationInOneDay = mMaxDurationInOneDay;
    }

    public int getmMaxCalorieInOneDay() {
        return mMaxCalorieInOneDay;
    }

    public void setmMaxCalorieInOneDay(int mMaxCalorieInOneDay) {
        this.mMaxCalorieInOneDay = mMaxCalorieInOneDay;
    }

    public int getmMaxElevationInOneDay() {
        return mMaxElevationInOneDay;
    }

    public void setmMaxElevationInOneDay(int mMaxElevationInOneDay) {
        this.mMaxElevationInOneDay = mMaxElevationInOneDay;
    }

    public double getmLongestDistanceInOneDay() {
        return mLongestDistanceInOneDay;
    }

    public void setmLongestDistanceInOneDay(double mLongestDistanceInOneDay) {
        this.mLongestDistanceInOneDay = mLongestDistanceInOneDay;
    }

    public double getmFastestSpeedInOneDay() {
        return mFastestSpeedInOneDay;
    }

    public void setmFastestSpeedInOneDay(double mFastestSpeedInOneDay) {
        this.mFastestSpeedInOneDay = mFastestSpeedInOneDay;
    }
}
