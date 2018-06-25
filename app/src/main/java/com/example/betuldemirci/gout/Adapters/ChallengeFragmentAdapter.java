package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Fragments.ChallengeFragment;
import com.example.betuldemirci.gout.Model.Challenges;
import com.example.betuldemirci.gout.Model.FirebaseUserInformation;
import com.example.betuldemirci.gout.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChallengeFragmentAdapter extends RecyclerView.Adapter<ChallengeFragmentAdapter.ViewHolder> {

    private static String TAG = "ChallengeFragmentAdapter";

    private Activity activity;
    private String[] strings;
    private Context mContext;
    private List<Challenges> mChallengeList;
    private List<String> mChallengeUserList;
    private List<String> mImgUrl;
    private String mUserId;
    private DatabaseReference mDatabaseRef;

    private ViewHolder mVHolder;
    private LinearLayoutManager mLayoutManager;

    private int[] images;

    public ChallengeFragmentAdapter(Activity activity, Context context, List<Challenges> mList) {
        this.activity = activity;
        this.strings = strings;
        this.images = images;
        this.mContext = context;
        this.mChallengeList = mList;
        this.mChallengeUserList = new ArrayList<>();
        this.mImgUrl = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_challenge_cards, parent, false);

        mUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        mImgUrl.clear();

        mVHolder = holder;

        mVHolder.name.setText(mChallengeList.get(position).getmChallengeName());
        mVHolder.type.setText(mChallengeList.get(position).getmChallengeType());
        mVHolder.dates.setText(mChallengeList.get(position).getmStartDate()+"  --  "+mChallengeList.get(position).getmEndDate());
        mVHolder.number_people.setText(String.valueOf(mChallengeList.get(position).getmNumberOfUser()));

        mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);

        mChallengeUserList = mChallengeList.get(position).getmAllUsersId();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    for(int i = 0; i < mChallengeUserList.size(); i++) {

                        mImgUrl.add(ds.child(mChallengeUserList.get(i)).child("mImageUrl").getValue(String.class));

                     }
                }
                fonk();//Önemli bu silme
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void fonk(){
        mVHolder.mRec.setLayoutManager(mLayoutManager);
        ChallengeCardAdapter mAdapter = new ChallengeCardAdapter(activity, mContext, mImgUrl);
        mVHolder.mRec.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return mChallengeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, type, dates, number_people;
        private RecyclerView mRec;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.challenge_name);
            type = view.findViewById(R.id.challenge_type);
            dates = view.findViewById(R.id.start_end_date);
            number_people = view.findViewById(R.id.number_people);
            mRec = view.findViewById(R.id.avatar_recyclerview);
        }
    }
}
