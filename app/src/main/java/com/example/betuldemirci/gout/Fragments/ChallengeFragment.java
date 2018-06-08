package com.example.betuldemirci.gout.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betuldemirci.gout.Adapters.ChallengeFragmentAdapter;
import com.example.betuldemirci.gout.Adapters.ViewPagerAdapter;
import com.example.betuldemirci.gout.R;



public class ChallengeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private View v;

    private TabLayout tablayout;
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;

    private OnFragmentInteractionListener mListener;

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


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_challenge, container, false);

        tablayout = v.findViewById(R.id.tablayout_id);
        viewpager = v.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getChildFragmentManager());

        //Add Fragment
        adapter.AddFragment(new PastChallenge(), "Past");
        adapter.AddFragment(new NewChallenge(), "New");


        viewpager.setAdapter(adapter);
        viewpager.setOffscreenPageLimit(2);
        tablayout.setupWithViewPager(viewpager);

        tablayout.getTabAt(0).setIcon(R.drawable.past);
        tablayout.getTabAt(1).setIcon(R.drawable.future);

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
