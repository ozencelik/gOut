package com.example.betuldemirci.gout.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.betuldemirci.gout.Model.FirebaseUserInformation;
import com.example.betuldemirci.gout.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    private static final String TAG = "PROFILE FRAGMENT :  ";

    private ViewFlipper mViewFlipper;
    private ImageView logoutImage;
    private TextView mUserName, mUserEmail;
    private Button mSex, mWeight, mHeight;

    private int images[] = {R.drawable.bicycle, R.drawable.hop_woman, R.drawable.pist
            , R.drawable.ski_man, R.drawable.running_man_woman, R.drawable.man_with_foot};

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private String mUserId;



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

        mUserName = v.findViewById(R.id.user_name); mUserEmail = v.findViewById(R.id.user_email);
        mSex = v.findViewById(R.id.gender); mWeight = v.findViewById(R.id.weight); mHeight = v.findViewById(R.id.height);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();

        mUserId = mUser.getUid();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Toast.makeText(getContext(), ds.child(mUserId).getValue(FirebaseUserInformation.class).getmName(), Toast.LENGTH_SHORT).show();

                    FirebaseUserInformation mUserInfo = new FirebaseUserInformation();

                    mUserInfo.setmName(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmName());
                    mUserInfo.setmSurname(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmSurname());
                    mUserInfo.setmSex(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmSex());
                    mUserInfo.setmWeight(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmWeight());
                    mUserInfo.setmHeight(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmHeight());

                    mUserName.setText(mUserInfo.getmName() + mUserInfo.getmSurname());
                    mUserEmail.setText(mUser.getEmail());
                    mSex.setText(mUserInfo.getmSex());
                    mWeight.setText(String.valueOf(mUserInfo.getmWeight()));
                    mHeight.setText(String.valueOf(mUserInfo.getmHeight()));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

        mUserName.setText("b");
        /*
        mHelper.mDatabaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    FirebaseUserInformation mUserInfo = new FirebaseUserInformation();

                    //Toast.makeText(getActivity(), "Data : "+ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmName(), Toast.LENGTH_SHORT).show();


                    mUserInfo.setmName(ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmName());
                    mUserInfo.setmSurname(ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmSurname());
                    mUserInfo.setmSex(ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmSex());
                    mUserInfo.setmWeight(ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmWeight());
                    mUserInfo.setmHeight(ds.child(mHelper.getUserId()).getValue(FirebaseUserInformation.class).getmHeight());

                    mUserName.setText(mUserInfo.getmName() + mUserInfo.getmSurname());
                    mUserEmail.setText(mHelper.mUser.getEmail());

                    //mHelper.mUser.getEmail();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/



        for(int i = 0; i < images.length; i++) flipperImages(images[i]);

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
