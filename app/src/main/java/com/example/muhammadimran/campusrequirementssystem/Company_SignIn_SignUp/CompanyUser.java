package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.StudentUserAdapter;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.auth.FirebaseAuth;
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
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_user);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setIcon(R.drawable.add);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        studentsUsers = (ListView) findViewById(R.id.StudentUsersList);
        adapter = new StudentUserAdapter(arrayList, this);
        studentsUsers.setAdapter(adapter);

        fetchStudents();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.additems, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addPost:
                startActivity(new Intent(this, PostJobsByCompany.class));
                break;
            case R.id.Companylogout:
                mAuth.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchStudents() {
        mDatabase.child("Student-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfoModel users = dataSnapshot.getValue(UserInfoModel.class);
                arrayList.add(new UserInfoModel(users.getFname(), users.getLname(), users.getEmail(),
                        users.getPassword(), users.getcPassword(), users.getGrade(), users.getGender(), users.getContact()));
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
