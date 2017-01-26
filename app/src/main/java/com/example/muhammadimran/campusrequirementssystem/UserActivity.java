package com.example.muhammadimran.campusrequirementssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.CompanyAdapter;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.CompanyModel;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    private ListView usersList;
    private CompanyAdapter adapter;
    private ArrayList<CompanyModel> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        //String CurrentUserId = mAuth.getCurrentUser().getUid();

        usersList = (ListView) findViewById(R.id.companyUsersList);
        adapter = new CompanyAdapter(arrayList, this);
        usersList.setAdapter(adapter);


        firebaseDatabase.child("company-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("TAG", dataSnapshot.getValue().toString());
                //for (DataSnapshot data : dataSnapshot.getChildren()) {
                CompanyModel companyModel = dataSnapshot.getValue(CompanyModel.class);
                arrayList.add(new CompanyModel(companyModel.getFname(), companyModel.getLname(), companyModel.getEmail(), companyModel.getPassword(), companyModel.getConfermpassword(), companyModel.getPosition(), companyModel.getGender(), companyModel.getContact()));
                adapter.notifyDataSetChanged();

                //}


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
