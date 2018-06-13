package com.example.betuldemirci.gout.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FirebaseUserInformation {
    private static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";

    private String mImageUrl, mName, mSurname, mSex, mUserType, mBirthdayText;
    private double mWeight, mHeight;
    private int mAge, mFriendsNumber;
    private Date mBirthday;

    private SimpleDateFormat  mDateFormat;


    public FirebaseUserInformation(String mImageUrl, String mName, String mSurname, String mSex, String mUserType, double mWeight, double mHeight, int mAge, int mFriendsNumber, Date mBirthday) {
        this.mImageUrl = mImageUrl;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mSex = mSex;
        this.mUserType = mUserType;
        this.mWeight = mWeight;
        this.mHeight = mHeight;
        this.mAge = mAge;
        this.mFriendsNumber = mFriendsNumber;
        this.mBirthday = mBirthday;
        //this.mBirthday = Calendar.getInstance().getTime();

        mDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        mBirthdayText = mDateFormat.format(mBirthday);
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

    public double getmWeight() {
        return mWeight;
    }

    public void setmWeight(double mWeight) {
        this.mWeight = mWeight;
    }

    public double getmHeight() {
        return mHeight;
    }

    public void setmHeight(double mHeight) {
        this.mHeight = mHeight;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public int getmFriendsNumber() {
        return mFriendsNumber;
    }

    public void setmFriendsNumber(int mFriendsNumber) {
        this.mFriendsNumber = mFriendsNumber;
    }

    public Date getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(Date mBirthday) {
        this.mBirthday = mBirthday;
    }
}
