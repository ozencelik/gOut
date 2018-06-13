package com.example.betuldemirci.gout.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.betuldemirci.gout.Activity.SplashActivity;
import com.example.betuldemirci.gout.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    private static final String TAG = "PROFILE FRAGMENT :  ";

    private ViewFlipper mViewFlipper;
    private ImageView logoutImage;

    private int images[] = {R.drawable.bicycle, R.drawable.hop_woman, R.drawable.pist
            , R.drawable.ski_man, R.drawable.running_man_woman, R.drawable.man_with_foot};


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
        Log.i(TAG, "onAttach");
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutImage = v.findViewById(R.id.logout);
        mViewFlipper = v.findViewById(R.id.image_flipper);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");
        for (int imageId : images) flipperImages(imageId);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        logoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(getActivity(), "Succesfully Logged Out", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getActivity(), SplashActivity.class));
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
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
        Log.i(TAG, "ondDetach");
    }

    public void flipperImages(int image){

        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        //imageView.setImageResource(image);
//        R.color.transparent_white
        mViewFlipper.addView(imageView);
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.setAutoStart(true);

        //Animation
        mViewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

}
