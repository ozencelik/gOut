package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betuldemirci.gout.R;

import java.util.ArrayList;

public class ChallengeFragmentAdapter extends RecyclerView.Adapter<ChallengeFragmentAdapter.ViewHolder> {

    private Activity activity;
    private String[] strings;
    private Context mContext;


    private int[] images;

    public ChallengeFragmentAdapter(Activity activity, Context context, String[] strings, int[] images) {
        this.activity = activity;
        this.strings = strings;
        this.images = images;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_challenge_cards, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(strings[position]);

        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        holder.mRec.setLayoutManager(mLayoutManager);
        ChallengeCardAdapter mAdapter = new ChallengeCardAdapter(activity, mContext, images);
        holder.mRec.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RecyclerView mRec;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text);
            mRec = view.findViewById(R.id.avatar_recyclerview);
        }
    }
}
