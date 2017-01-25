package com.example.muhammadimran.campusrequirementssystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference firebaseDatabase;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        String CurrentUserId = mAuth.getCurrentUser().getUid();

        textView = (TextView) findViewById(R.id.username);


        firebaseDatabase.child("Student-info").child(CurrentUserId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("TAG", dataSnapshot.getValue().toString());
                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    UserInfoModel userInfoModel = data.getValue(UserInfoModel.class);
//                    //UserInfoModel user = new UserInfoModel(userInfoModel.getFName(), userInfoModel.getLname(), userInfoModel.getEmail(), userInfoModel.getPassword(), userInfoModel.getCPassword(), userInfoModel.getGrade(), userInfoModel.getGender(), userInfoModel.getContact());
//                    textView.setText(userInfoModel.getFName());
                }


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
