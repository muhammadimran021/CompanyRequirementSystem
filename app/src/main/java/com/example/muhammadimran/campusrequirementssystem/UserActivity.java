package com.example.muhammadimran.campusrequirementssystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.CompanyAdapter;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.CompanyModel;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.PostModel;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.PostAdapter;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.StudentLogin_signUp;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    private ListView usersList;
    private PostAdapter adapter;
    private List<PostModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        adapter = new PostAdapter(arrayList, UserActivity.this);
        RecyclerView recList = (RecyclerView) findViewById(R.id.postrecyclerViewList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        recList.setAdapter(adapter);

        //String CurrentUserId = mAuth.getCurrentUser().getUid();


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Adminlogout:
                mAuth.signOut();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
