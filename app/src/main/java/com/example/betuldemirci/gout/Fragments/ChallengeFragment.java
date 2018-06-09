package com.example.betuldemirci.gout.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betuldemirci.gout.Adapters.ChallengeFragmentAdapter;
import com.example.betuldemirci.gout.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class ChallengeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private View v;
    private OnFragmentInteractionListener mListener;


    private String[] asiaCountries = {"Vietnam", "China", "Japan", "Korea", "India", "Singapore", "Thailand", "Malaysia"};
    private String[] europeCountries = {"France", "Germany", "Sweden", "Denmark", "England", "Spain", "Portugal", "Norway"};

    private int[] aImg = {R.drawable.ab, R.drawable.b, R.drawable.c, R.drawable.d};
    private int[] bImg = {R.drawable.ab, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c,
            R.drawable.ab, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c,
            R.drawable.a, R.drawable.d, R.drawable.e, R.drawable.e, R.drawable.b, R.drawable.c};

    private RecyclerView mNewRecyclerView, mPastRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayoutManager layoutManager;
    private static String LOG_TAG = "Multi_RecyclerView";


    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4;

    public ChallengeFragment() {

    }


    public static ChallengeFragment newInstance(String param1, String param2) {
        ChallengeFragment fragment = new ChallengeFragment();
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
        v = inflater.inflate(R.layout.fragment_challenge, container, false);

        mNewRecyclerView = v.findViewById(R.id.new_challenges);
        RecyclerView.LayoutManager firstLayoutManager = new LinearLayoutManager(getActivity());
        mNewRecyclerView.setLayoutManager(firstLayoutManager);
        ChallengeFragmentAdapter firstAdapter = new ChallengeFragmentAdapter(getActivity(), asiaCountries, aImg);
        mNewRecyclerView.setAdapter(firstAdapter);
        mNewRecyclerView.setHasFixedSize(true);


        mPastRecyclerView = v.findViewById(R.id.past_challenges);
        RecyclerView.LayoutManager secondLayoutManager = new LinearLayoutManager(getActivity());
        mPastRecyclerView.setLayoutManager(secondLayoutManager);
        ChallengeFragmentAdapter secondAdapter = new ChallengeFragmentAdapter(getActivity(), europeCountries, bImg);
        mPastRecyclerView.setAdapter(secondAdapter);
        mPastRecyclerView.setHasFixedSize(true);

        mNewRecyclerView.setNestedScrollingEnabled(false);
        mPastRecyclerView.setNestedScrollingEnabled(false);


        materialDesignFAM = v.findViewById(R.id.material_design_android_floating_action_menu);
        floatingActionButton1 = v.findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 =  v.findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 =  v.findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = v.findViewById(R.id.material_design_floating_action_menu_item4);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                //untuk aksi ketika di klik
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked

            }
        });

        return v;
    }


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
