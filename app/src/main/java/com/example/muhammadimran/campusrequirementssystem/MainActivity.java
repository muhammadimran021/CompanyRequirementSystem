package com.example.muhammadimran.campusrequirementssystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.StudentLogin_signUp;

public class MainActivity extends AppCompatActivity {
    CardView company, student, admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        company = (CardView) findViewById(R.id.card_viewCompany);
        student = (CardView) findViewById(R.id.card_viewStudent);
        admin = (CardView) findViewById(R.id.card_viewAdmin);
        OnStudentClick();


    }

    public void OnStudentClick() {
        student.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().add(R.id.mainframe, new StudentLogin_signUp()).commit());
    }
}
