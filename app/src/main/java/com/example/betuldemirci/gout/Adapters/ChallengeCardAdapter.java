package com.example.betuldemirci.gout.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ChallengeCardAdapter extends RecyclerView.Adapter<ChallengeCardAdapter.ViewHolder> {

    private Activity activity;
    private int[] images;
    private Dialog mDialog;
    private Context mContext;


    public ChallengeCardAdapter(Activity activity, Context context, int[] images) {
        this.activity = activity;
        this.images = images;
        this.mContext = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_image_cards, parent, false);

        final ViewHolder vHolder = new ViewHolder(view);

        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_challenge_card);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.mCircleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = mDialog.findViewById(R.id.dialog_name);
                TextView dialog_phone_tv = mDialog.findViewById(R.id.dialog_phone);
                ImageView dialog_contact_img = mDialog.findViewById(R.id.dialog_img);

                //dialog_contact_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());
                dialog_contact_img.setImageResource(images[vHolder.getAdapterPosition()]);


                Toast.makeText(mContext, "Clicked "+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                mDialog.show();
            }
        });

        return vHolder;
        //return new ViewHolder(view);
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