package com.example.betuldemirci.gout.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betuldemirci.gout.R;

public class NewChallenge extends Fragment {

    View v;
    FloatingActionButton mButton;

    public NewChallenge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.new_challenge, container, false);

        mButton = v.findViewById(R.id.new_challenge);

        return v;
    }
}
