package com.example.betuldemirci.gout.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FirebaseUserInformation {
    private static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";

    private String mImageUrl, mName, mSurname, mSex, mUserType, mBirthdayText, mGoalType;
    private double mWeight, mGoalWeight, mHeight, mAge;
    private int mFriendsNumber, mWeeksGoalWeight;

    private SimpleDateFormat  mDateFormat;


    public FirebaseUserInformation(String mGoalType, int mWeeksGoalWeight, String mImageUrl, String mName,
                                   String mSurname, String mSex, String mUserType, double mWeight,
                                   double mGoalWeight, double mHeight, double mAge, int mFriendsNumber, Date mBirthday) {
        this.mWeeksGoalWeight = mWeeksGoalWeight;
        this.mGoalType = mGoalType;
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mSex = mSex;
        this.mUserType = mUserType;
        this.mWeight = mWeight;
        this.mGoalWeight = mGoalWeight;
        this.mHeight = mHeight;
        this.mAge = mAge;
        this.mFriendsNumber = mFriendsNumber;
        //this.mBirthday = Calendar.getInstance().getTime();
        mDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        this.mBirthdayText = mDateFormat.format(mBirthday);
    }

    public FirebaseUserInformation(){
        mImageUrl = "";
        mName = "";
        mSurname = "";
        mSex = "";
        mUserType = "";
        mBirthdayText = "";
        mGoalType = "";
    }

    public FirebaseUserInformation(String name, String surname){
        this.mName = name;
        this.mSurname = surname;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public String getmUserType() {
        return mUserType;
    }

    public void setmUserType(String mUserType) {
        this.mUserType = mUserType;
    }

    public String getmBirthdayText() {
        return mBirthdayText;
    }

    public void setmBirthdayText(String mBirthdayText) {
        this.mBirthdayText = mBirthdayText;
    }

    public String getmGoalType() {
        return mGoalType;
    }

    public void setmGoalType(String mGoalType) {
        this.mGoalType = mGoalType;
    }

    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public double getmGoalWeight() {
        return mGoalWeight;
    }

    public void setmGoalWeight(double mGoalWeight) {
        this.mGoalWeight = mGoalWeight;
    }

    public double getmHeight() {
        return mHeight;
    }

    public void setmHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public double getmAge() {
        return mAge;
    }

    public void setmAge(double mAge) {
        this.mAge = mAge;
    }

    public int getmFriendsNumber() {
        return mFriendsNumber;
    }

    public void setmFriendsNumber(int mFriendsNumber) {
        this.mFriendsNumber = mFriendsNumber;
    }

    public int getmWeeksGoalWeight() {
        return mWeeksGoalWeight;
    }

    public void setmWeeksGoalWeight(int mWeeksGoalWeight) {
        this.mWeeksGoalWeight = mWeeksGoalWeight;
    }
}
