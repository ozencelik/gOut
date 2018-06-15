package com.example.betuldemirci.gout.Model;

public class OnBoardingModel {

    public static final int GOAL_TYPE = 0; // Be Healtier or Lose Weight or Gain Weight
    public static final int SEX_TYPE = 1;
    public static final int OTHER_INFO_TYPE = 2;

    public static int mType;

    private static String mSexType;//0:Male, 1:Female
    private static String mGoalType; // Be Healtier or Lose Weight or Gain Weight
    private static int mWeeksGoalWeight;
    private static double mAge;
    private static double mCurrentWeight;
    private static double mGoalWeight;
    private static double mHeight;


    public static String getmSexType() {
        return mSexType;
    }

    public static void setmSexType(String mSexType) {
        OnBoardingModel.mSexType = mSexType;
    }

    public static String getmGoalType() {
        return mGoalType;
    }

    public static void setmGoalType(String mGoalType) {
        OnBoardingModel.mGoalType = mGoalType;
    }

    public static int getmWeeksGoalWeight() {
        return mWeeksGoalWeight;
    }

    public static void setmWeeksGoalWeight(int mWeeksGoalWeight) {
        OnBoardingModel.mWeeksGoalWeight = mWeeksGoalWeight;
    }

    public static double getmAge() {
        return mAge;
    }

    public static void setmAge(double mAge) {
        OnBoardingModel.mAge = mAge;
    }

    public static double getmCurrentWeight() {
        return mCurrentWeight;
    }

    public static void setmCurrentWeight(double mCurrentWeight) {
        OnBoardingModel.mCurrentWeight = mCurrentWeight;
    }

    public static double getmGoalWeight() {
        return mGoalWeight;
    }

    public static void setmGoalWeight(double mGoalWeight) {
        OnBoardingModel.mGoalWeight = mGoalWeight;
    }

    public static double getmHeight() {
        return mHeight;
    }

    public static void setmHeight(double mHeight) {
        OnBoardingModel.mHeight = mHeight;
    }
}
