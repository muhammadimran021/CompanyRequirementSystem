package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.StudentUserAdapter;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CompanyUser extends AppCompatActivity {

    private ArrayList<UserInfoModel> arrayList = new ArrayList<>();
    private ListView studentsUsers;
    private StudentUserAdapter adapter;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        studentsUsers = (ListView) findViewById(R.id.StudentUsersList);
        adapter = new StudentUserAdapter(arrayList, this);
        studentsUsers.setAdapter(adapter);

        fetchStudents();

    }


    public void fetchStudents() {
        mDatabase.child("Student-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfoModel users = dataSnapshot.getValue(UserInfoModel.class);
                arrayList.add(new UserInfoModel(users.getFname(), users.getLname(), users.getEmail(), users.getPassword(), users.getcPassword(), users.getGrade(), users.getGender(), users.getContact()));
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
