package com.example.betuldemirci.gout.Adapters;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Contact;
import com.example.betuldemirci.gout.R;

import java.util.List;


public class ChallengeFragmentAdapter extends RecyclerView.Adapter<ChallengeFragmentAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mData;
    Dialog mDialog;

    public ChallengeFragmentAdapter(Context mContext, List<Contact> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);

        final MyViewHolder vHolder = new MyViewHolder(v);

        //Dialog Initialize
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_contact);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        vHolder.item_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = mDialog.findViewById(R.id.dialog_name);
                TextView dialog_phone_tv = mDialog.findViewById(R.id.dialog_phone);
                ImageView dialog_contact_img = mDialog.findViewById(R.id.dialog_img);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                dialog_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
                dialog_contact_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                Toast.makeText(mContext, "Clicked "+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                mDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_phone.setText(mData.get(position).getPhone());
        holder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_contact;
        private TextView tv_name;
        private TextView tv_phone;
        private ImageView img;


        public MyViewHolder(View itemView){
            super(itemView);

            item_contact = itemView.findViewById(R.id.contact_item);
            tv_name = itemView.findViewById(R.id.name_contact);
            tv_phone = itemView.findViewById(R.id.phone_contact);
            img = itemView.findViewById(R.id.img_contact);
        }
    }
}
