package com.example.betuldemirci.gout.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.betuldemirci.gout.Adapters.ChallengeFragmentAdapter;
import com.example.betuldemirci.gout.Adapters.RecyclerViewAdapter;
import com.example.betuldemirci.gout.Contact;
import com.example.betuldemirci.gout.R;

import java.util.ArrayList;
import java.util.List;

public class PastChallenge extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private List<Contact> lstContact;

    Button but;


    public PastChallenge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.past_challenge,container,false);

        but = v.findViewById(R.id.but);

        lstContact = new ArrayList<>();
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Amelia Kendrick", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("John Doe", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Cloe Loren", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Amelia Kendrick", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("John Doe", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Cloe Loren", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));
        lstContact.add(new Contact("Aaron Jones", "(111)1242144", R.drawable.indir));

        recyclerView = v.findViewById(R.id.past_challenge_recyclerview);
        ChallengeFragmentAdapter recyclerAdapter = new ChallengeFragmentAdapter(getContext(), lstContact);
        recyclerView.setLayoutManager(new LinearLayoutManager((getActivity())));
        recyclerView.setAdapter(recyclerAdapter);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);


    }
}
