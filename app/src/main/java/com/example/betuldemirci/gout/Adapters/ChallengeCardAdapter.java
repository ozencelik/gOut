package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.betuldemirci.gout.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChallengeCardAdapter extends RecyclerView.Adapter<ChallengeCardAdapter.ViewHolder> {

    private Activity activity;
    private int[] images;


    public ChallengeCardAdapter(Activity activity, int[] images) {
        this.activity = activity;
        this.images = images;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_image_cards, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCircleImg.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private CircleImageView mCircleImg;

        public ViewHolder(View view) {
            super(view);
            mCircleImg = view.findViewById(R.id.profile_image);
        }
    }
}