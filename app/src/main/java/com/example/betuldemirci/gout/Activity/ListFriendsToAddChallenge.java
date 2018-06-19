package com.example.betuldemirci.gout.Activity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.betuldemirci.gout.Model.FirebaseUserInformation;
import com.example.betuldemirci.gout.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ListFriendsToAddChallenge extends AppCompatActivity {

    private  static  final String TAG = "ListFriendsToAddChallenge";

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
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("TEXT WATCHER", "AFTER TEXT CHANGED");

                input = search.getText().toString();

                //query = mFirebaseDatabaseReference.child("Images").orderByChild("lecture").startAt(input).endAt(input+"\uf8ff");
                query = mFirebaseDatabaseReference.child("Users").startAt(input).endAt(input+"\uf8ff");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot sp : dataSnapshot.getChildren()) {

                            FirebaseUserInformation mUInfo = sp.getValue(FirebaseUserInformation.class);

                            userList = findViewById(R.id.friends_list_recyclerview);
                            userList.setHasFixedSize(true);
                            LinearLayoutManager mLayoutManager = new LinearLayoutManager(ListFriendsToAddChallenge.this);
                            mLayoutManager.setReverseLayout(true);
                            mLayoutManager.setStackFromEnd(true);
                            userList.setLayoutManager(mLayoutManager);







                            /*

                            FirebaseRecyclerAdapter<FirebaseUserInformation, ListFriendsToAddChallenge.PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FirebaseUserInformation, ListFriendsToAddChallenge.PostViewHolder>(
                                    FirebaseUserInformation.class,
                                    R.layout.item_list_friends,
                                    FirebaseUserInformation.PostViewHolder.class,
                                    query

                            ) {
                                @Override
                                protected void populateViewHolder(FirebaseUserInformation.PostViewHolder viewHolder, FirebaseUserInformation model, int position) {

                                    final String key = getRef(position).getKey();



                                    viewHolder.setName(model.getmName());

                                    viewHolder.view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent i = new Intent(getActivity(), ShowPost.class);
                                            i.putExtra("key", key);
                                            startActivity(i);
                                        }
                                    });
                                }
                            };*/











                            //userList.setAdapter(firebaseRecyclerAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


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

}
