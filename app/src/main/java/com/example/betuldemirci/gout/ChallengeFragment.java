package com.example.betuldemirci.gout;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Objects;

import static com.example.betuldemirci.gout.R.id.viewpager_id;


public class ChallengeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private View v;

    private TabLayout tablayout;
    private ViewPager viewpager;
    private ViewPagerAdapter adapter;

    private final Handler handler = new Handler();
    private Runnable runPager;

    private OnFragmentInteractionListener mListener;

    LinearLayout l;

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
        v = inflater.inflate(R.layout.fragment_home, container, false);

        l = v.findViewById(R.layout.fragment_home);

        tablayout = v.findViewById(R.id.tablayout_id);
        viewpager = v.findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getFragmentManager());

        //Add Fragment
        adapter.AddFragment(new NewChallenge(), "New");
        adapter.AddFragment(new PastChallenge(), "Past");

//        runPager = new Runnable() {
//
//            @Override
//            public void run()
//            {
//                getFragmentManager().beginTransaction().addFragment(R.layout.past_challenge, ChallengeFragment.newInstance()).commit();
//            }
//        };
//        handler.post(runPager);

        viewpager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewpager);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * @see android.support.v4.app.Fragment#onPause()
     */
    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runPager);
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
