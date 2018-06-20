package com.example.betuldemirci.gout.Model;

import java.util.ArrayList;

public class Challenges {
    private static final String SIMPLE_DATE_FORMAT = "dd-MMM-yyyy";

    private String mChallengeName, mAdminUserId, mChallengeType;// 1v1 or 1vN (Individual Challenge, Group Challenge)
    private ArrayList<String> mAllUsersId;
    private int mNumberOfUser, mNumberOfStep;

    private String mStartDate, mEndDate;
    private boolean isChallengeActive;


    public Challenges(){
        this.mChallengeName = "";
        this.mChallengeType = "";
        this.mStartDate = "";
        this.mEndDate = "";
        this.isChallengeActive = true;
        this.mAdminUserId = "";
        this.mAllUsersId = new ArrayList<>();
        this.mNumberOfUser = 0;
    }

    public Challenges(String CName, String CType, String CStartDate, String CEndDate, boolean CIsActive, String CAdminUserId, ArrayList<String> CAllUserId, int CNumberOfUsers, int mNumberOfStep){

        this.mChallengeName = CName;
        this.mChallengeType = CType;
        this.mStartDate = CStartDate;
        this.mEndDate = CEndDate;
        this.isChallengeActive = CIsActive;
        this.mAdminUserId = CAdminUserId;
        this.mAllUsersId = CAllUserId;
        this.mNumberOfUser = CNumberOfUsers;
        this.mNumberOfStep = mNumberOfStep;
    }

    public String getmChallengeName() {
        return mChallengeName;
    }

    public void setmChallengeName(String mChallengeName) {
        this.mChallengeName = mChallengeName;
    }

    public String getmAdminUserId() {
        return mAdminUserId;
    }

    public void setmAdminUserId(String mAdminUserId) {
        this.mAdminUserId = mAdminUserId;
    }

    public String getmChallengeType() {
        return mChallengeType;
    }

    public void setmChallengeType(String mChallengeType) {
        this.mChallengeType = mChallengeType;
    }

    public ArrayList<String> getmAllUsersId() {
        return mAllUsersId;
    }

    public void setmAllUsersId(ArrayList<String> mAllUsersId) {
        this.mAllUsersId = mAllUsersId;
    }

    public int getmNumberOfUser() {
        return mNumberOfUser;
    }

    public void setmNumberOfUser(int mNumberOfUser) {
        this.mNumberOfUser = mNumberOfUser;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public boolean isChallengeActive() {
        return isChallengeActive;
    }

    public void setChallengeActive(boolean challengeActive) {
        isChallengeActive = challengeActive;
    }

    public int getmNumberOfStep() {
        return mNumberOfStep;
    }

    public void setmNumberOfStep(int mNumberOfStep) {
        this.mNumberOfStep = mNumberOfStep;
    }
}
