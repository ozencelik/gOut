package com.example.betuldemirci.gout.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.betuldemirci.gout.Model.FirebaseUserInformation;
import com.example.betuldemirci.gout.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ListFriendsToAddChallenge extends AppCompatActivity {

    private  static  final String TAG = "ListFriends";

    private EditText search ;
    private RecyclerView userList;
    private String input;
    private DatabaseReference mFirebaseDatabaseReference;
    private Query query;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_to_add_challenge);

        search = findViewById(R.id.search_view);
        userList = findViewById(R.id.friends_list_recyclerview);
        userList.setHasFixedSize(true);
        userList.setLayoutManager(new LinearLayoutManager(this));
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    protected void onStart() {
        super.onStart();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("TEXT WATCHER", "BEFORE TEXT CHANGED");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("TEXT WATCHER", "ON TEXT CHANGED");

                input = search.getText().toString();
                Log.i(TAG , input);

                if(!input.equals("")) firebaseSearch(input);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("TEXT WATCHER", "AFTER TEXT CHANGED");

            }
        });


    }

    public void firebaseSearch(String search){

        query = mFirebaseDatabaseReference.child("Users").orderByChild("mName").startAt(search).endAt(search+"\uf8ff");

        FirebaseRecyclerAdapter<FirebaseUserInformation, PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FirebaseUserInformation, PostViewHolder>(
                FirebaseUserInformation.class,
                R.layout.item_list_friends,
                PostViewHolder.class,
                query
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, FirebaseUserInformation model, int position) {
                viewHolder.setDetails(getApplicationContext() , model.getmName(), model.getmGoalType(), model.getmImageUrl(),model.getmUserId() , position);
            }
        };

        userList.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        View view;

        public PostViewHolder(View itemView) {
            super(itemView);

            view = itemView;
        }

        @SuppressLint("ClickableViewAccessibility")
        public void setDetails(final Context ctx, String name, String goalType, final String imageUrl, final String uid , final int pos){

            final LinearLayout root = view.findViewById(R.id.contact_item);
            TextView user_name = view.findViewById(R.id.name);
            TextView goal_type = view.findViewById(R.id.goal_type);
            CircleImageView post_image = view.findViewById(R.id.image);

            user_name.setText(name);
            goal_type.setText(goalType);
            Picasso.get().load(imageUrl).into(post_image);

            root.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        Toast.makeText( ctx, "Long click! : " + pos, Toast.LENGTH_SHORT).show();

                        ctx.startActivity(new Intent(ctx, AddNewChallengeActivity.class)
                                .putExtra("uid", String.valueOf(uid))
                                .putExtra("img", imageUrl));

                        return true;
                    }

                });
        }
    }

}
