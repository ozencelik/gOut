package com.example.betuldemirci.gout.Fragments;

import android.app.Dialog;
import android.app.DialogFragment;
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
import android.app.FragmentTransaction;
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
import com.google.firebase.database.DatabaseReference;


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
    private Challenges mNewChallenge;

    /////////////////////////////////
    private FloatingActionMenu mChallengeFam;
    private FloatingActionButton mAddNewChallenge;
    /////////////////////////////////

    private Dialog mDialog;


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

        mNewChallenge = new Challenges();

        mDialog = new Dialog(getContext());
        mDialog.setContentView(R.layout.dialog_challenge_card);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setCancelable(true);
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


        mNewAdapter = new ChallengeFragmentAdapter(getActivity(),getContext(), asiaCountries, aImg);

        mNewRecyclerView.setAdapter(mNewAdapter);
        mNewRecyclerView.setHasFixedSize(true);
        mNewRecyclerView.setNestedScrollingEnabled(false);

        mPastAdapter = new ChallengeFragmentAdapter(getActivity(), getContext(), europeCountries, bImg);

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

}
