package com.example.muhammadimran.campusrequirementssystem.AdminPanel;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.PostModel;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.PostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostByCompany extends Fragment {
    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    private ListView usersList;
    private PostAdapter adapter;
    private List<PostModel> arrayList = new ArrayList<>();


    public PostByCompany() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_by_company, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        RecyclerView recList = (RecyclerView) view.findViewById(R.id.PostListAdminView);
        recList.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new PostAdapter(arrayList, getContext());
        recList.setAdapter(adapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        recList.setItemAnimator(itemAnimator);
        CompanyPost();
        return view;
    }

    public void CompanyPost() {

        firebaseDatabase.child("company-post").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("TAG", dataSnapshot.getValue().toString());
                PostModel postModel = dataSnapshot.getValue(PostModel.class);
                arrayList.add(postModel);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
