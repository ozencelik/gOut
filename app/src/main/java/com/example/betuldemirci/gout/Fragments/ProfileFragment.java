package com.example.betuldemirci.gout.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.betuldemirci.gout.Activity.SplashActivity;
import com.example.betuldemirci.gout.Model.FirebaseUserInformation;
import com.example.betuldemirci.gout.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private static final String TAG = "PROFILE FRAGMENT :  ";
    private static final int PICK_IMAGE_REQUEST = 71;

    private View v;

    private ViewFlipper mViewFlipper;
    private ImageView logoutImage;
    private TextView mUserName, mUserEmail, mUserFriend, mBirthday;
    private Button mSex, mWeight, mHeight;
    private CircleImageView mProfileImage;

    private Bitmap mBitmap;

    private int images[] = {R.drawable.bicycle, R.drawable.hop_woman, R.drawable.pist
            , R.drawable.ski_man, R.drawable.running_man_woman, R.drawable.man_with_foot};

    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private String mUserId;

    private FirebaseStorage mStorage;
    private StorageReference mStorageReference, REFERENCE;
    private Uri mFilePath;

    private StorageTask mStorageTask;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onAttach");
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutImage = v.findViewById(R.id.logout);
        mViewFlipper = v.findViewById(R.id.image_flipper);
        for(int i = 0; i < images.length; i++) flipperImages(images[i]);

        mUserName = v.findViewById(R.id.user_name); mUserEmail = v.findViewById(R.id.user_email); mUserFriend = v.findViewById(R.id.user_friends); mBirthday = v.findViewById(R.id.birthday);
        mSex = v.findViewById(R.id.gender); mWeight = v.findViewById(R.id.weight); mHeight = v.findViewById(R.id.height);
        mProfileImage = v.findViewById(R.id.profile_image);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference();
        mUserId = mUser.getUid();

        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference("images");

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

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    final FirebaseUserInformation mUserInfo = new FirebaseUserInformation();

                    /*

                    mUserInfo.setmName(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmName());
                    mUserInfo.setmSurname(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmSurname());
                    mUserInfo.setmSex(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmSex());
                    mUserInfo.setmWeight(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmWeight());
                    mUserInfo.setmHeight(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmHeight());
                    mUserInfo.setmImageUrl(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmImageUrl());
                    mUserInfo.setmAge(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmAge());
                    mUserInfo.setmFriendsNumber(ds.child(mUserId).getValue(FirebaseUserInformation.class).getmFriendsNumber());

                    mUserName.setText(mUserInfo.getmName() + " " + mUserInfo.getmSurname());
                    mUserEmail.setText("  " + mUser.getEmail() + "  ");
                    mSex.setText(mUserInfo.getmSex());
                    mWeight.setText(String.valueOf(mUserInfo.getmWeight()) + " kg");
                    mHeight.setText(String.valueOf(mUserInfo.getmHeight()) + " cm");
                    mBirthday.setText(String.valueOf(mUserInfo.getmAge()) + " years old");
                    mUserFriend.setText(String.valueOf(mUserInfo.getmFriendsNumber()));

                    */

                    mStorageReference.child(mUserId).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Picasso.get()
                                    .load(uri)
                                    .fit()
                                    .centerCrop()
                                    .into(mProfileImage);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mStorageTask != null && mStorageTask.isInProgress()){

                }else chooseImage();

            }
        });

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

        mViewFlipper.addView(imageView);
        mViewFlipper.setFlipInterval(2000);
        mViewFlipper.setAutoStart(true);

        mViewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        mViewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    public void uploadImage(){

        if(mFilePath != null){

            final ProgressDialog mDialog = new ProgressDialog(getActivity());
            mDialog.setTitle("Uploading...");
            mDialog.show();

            REFERENCE = mStorageReference.child(mUserId);

            //mStorageReference = mStorageReference.child(mUserId);

            mStorageTask = REFERENCE.putFile(mFilePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    REFERENCE.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Uri downloadUrl = uri;
                            mDatabaseReference.child("Users").child(mUserId).child("mImageUrl").setValue(downloadUrl.toString());
                        }
                    });

                    mDialog.dismiss();
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                    mProfileImage.setImageBitmap(mBitmap);

                    //mDatabaseReference.child("Users").child(mUserId).child("mImageUrl").setValue(mUserId);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = 100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount();
                    mDialog.setMessage("Uploaded "+(int)progress+"%");
                }
            });
        }
    }


    public void chooseImage(){
        Intent mIntent = new Intent();
        mIntent.setType("image/*");
        mIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(mIntent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            mFilePath = data.getData();
            try{
                mBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), mFilePath);
                uploadImage();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
