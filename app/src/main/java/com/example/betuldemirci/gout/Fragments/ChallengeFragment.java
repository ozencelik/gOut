package com.example.betuldemirci.gout.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betuldemirci.gout.Activity.AddNewChallengeActivity;
import com.example.betuldemirci.gout.Adapters.ChallengeFragmentAdapter;
import com.example.betuldemirci.gout.Model.Challenges;
import com.example.betuldemirci.gout.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChallengeFragment extends Fragment {

    private static final String TAG = "CHALLENGE FRAGMENT :  ";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private int TOTAL_CHALLENGES_COUNTER;

    private View v;

    private String[] asiaCountries = {"Vietnam"/*, "China", "Japan", "Korea", "India", "Singapore", "Thailand", "Malaysia"*/};
    private String[] europeCountries = {"France", "Germany"/*, "Sweden", "Denmark", "England", "Spain", "Portugal", "Norway"*/};

    private int[] aImg = {R.drawable.ab, R.drawable.b, R.drawable.c, R.drawable.d};
    private int[] bImg = {R.drawable.ab, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c,
            R.drawable.ab, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c,
            R.drawable.a, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c};

    private RecyclerView mNewRecyclerView, mPastRecyclerView;
    private RecyclerView.LayoutManager mNewLayoutManager, mPastLayoutManager;
    private ChallengeFragmentAdapter mNewAdapter, mPastAdapter;

    private static final String CHILD_CHALLENGES = "Challenges";
    private FirebaseAuth mAuth;
    private String mUserId;
    private DatabaseReference mDatabaseRef;
    private Challenges mNewChl;
    private Challenges mPastChl;
    private ArrayList<Challenges> mListActiveChallenges;
    private ArrayList<String> mAllActiveUsersId;

    private ArrayList<Challenges> mListPastChallenges;
    private ArrayList<String> mAllPastUsersId;

    private boolean isChallengeActive = true;

    /////////////////////////////////
    private FloatingActionMenu mChallengeFam;
    private FloatingActionButton mAddNewChallenge;
    /////////////////////////////////

    private Dialog mDialog;

    int i = 0;


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
        v = inflater.inflate(R.layout.fragment_challenge, container, false);
        Log.i(TAG, "onCreateView");

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        editor = preferences.edit();

        mNewRecyclerView = v.findViewById(R.id.new_challenges);
        mNewLayoutManager = new LinearLayoutManager(getActivity());
        mNewRecyclerView.setLayoutManager(mNewLayoutManager);


        mPastRecyclerView = v.findViewById(R.id.past_challenges);
        mPastLayoutManager = new LinearLayoutManager(getActivity());
        mPastRecyclerView.setLayoutManager(mPastLayoutManager);

        mChallengeFam = v.findViewById(R.id.material_design_android_floating_action_menu);
        mAddNewChallenge = v.findViewById(R.id.material_design_floating_action_menu_item1);

        mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_challenge_card);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(true);

        mListActiveChallenges = new ArrayList<>();
        mAllActiveUsersId = new ArrayList<>();

        mListPastChallenges = new ArrayList<>();
        mAllPastUsersId = new ArrayList<>();

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");


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

        getChallengesToList();

        mNewAdapter = new ChallengeFragmentAdapter(getActivity(),getContext(), mListActiveChallenges);

        mNewRecyclerView.setAdapter(mNewAdapter);
        mNewRecyclerView.setHasFixedSize(true);
        mNewRecyclerView.setNestedScrollingEnabled(false);


        mPastAdapter = new ChallengeFragmentAdapter(getActivity(), getContext(), mListPastChallenges);

        mPastRecyclerView.setAdapter(mPastAdapter);
        mPastRecyclerView.setHasFixedSize(true);
        mPastRecyclerView.setNestedScrollingEnabled(false);


        mAddNewChallenge.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), AddNewChallengeActivity.class));


            }
        });
//        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu second item clicked
//
//            }
//        });
//        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//
//            }
//        });
//        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu third item clicked
//
//            }
//        });

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach");
    }


    public void getChallengesToList(){

        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mUserId).child("mChallengeList");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    isChallengeActive = ds.child("challengeActive").getValue(boolean.class);
                    if(ds != null){




                        if (isChallengeActive){

                            mNewChl = new Challenges();
                            mNewChl.setmChallengeName(ds.child("mChallengeName").getValue(String.class));
                            mNewChl.setChallengeActive(ds.child("challengeActive").getValue(boolean.class));
                            mNewChl.setmChallengeType(ds.child("mChallengeType").getValue(String.class));
                            mNewChl.setmStartDate(ds.child("mStartDate").getValue(String.class));
                            mNewChl.setmEndDate(ds.child("mEndDate").getValue(String.class));
                            mNewChl.setmNumberOfUser(ds.child("mNumberOfUser").getValue(int.class));

                            //mAllUsersId.add(ds.child("mAllUsersId").child("0").getValue(String.class));
                            //mAllUsersId.add(ds.child("mAllUsersId").child("1").getValue(String.class));

                            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot dsi: dataSnapshot.getChildren()){

                                        for(int a = 0; a < 5; a++){

                                            if(dsi.child("mAllUsersId").child(String.valueOf(a)).getValue(String.class) != null){
                                                mAllActiveUsersId.add(dsi.child("mAllUsersId").child(String.valueOf(a)).getValue(String.class));
                                            }else break;
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            mNewChl.setmAllUsersId(mAllActiveUsersId);
                            mListActiveChallenges.add(mNewChl);
                            mNewAdapter.notifyDataSetChanged();

                        }else{

                            mPastChl = new Challenges();
                            mPastChl.setmChallengeName(ds.child("mChallengeName").getValue(String.class));
                            mPastChl.setChallengeActive(ds.child("challengeActive").getValue(boolean.class));
                            mPastChl.setmChallengeType(ds.child("mChallengeType").getValue(String.class));
                            mPastChl.setmStartDate(ds.child("mStartDate").getValue(String.class));
                            mPastChl.setmEndDate(ds.child("mEndDate").getValue(String.class));
                            mPastChl.setmNumberOfUser(ds.child("mNumberOfUser").getValue(int.class));

                            //mAllUsersId.add(ds.child("mAllUsersId").child("0").getValue(String.class));
                            //mAllUsersId.add(ds.child("mAllUsersId").child("1").getValue(String.class));

                            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for(DataSnapshot dsi: dataSnapshot.getChildren()){

                                        for(int a = 0; a < 5; a++){

                                            if(dsi.child("mAllUsersId").child(String.valueOf(a)).getValue(String.class) != null){
                                                mAllPastUsersId.add(dsi.child("mAllUsersId").child(String.valueOf(a)).getValue(String.class));
                                            }else break;
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            mPastChl.setmAllUsersId(mAllPastUsersId);
                            mListPastChallenges.add(mPastChl);
                            mPastAdapter.notifyDataSetChanged();

                        }

                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
