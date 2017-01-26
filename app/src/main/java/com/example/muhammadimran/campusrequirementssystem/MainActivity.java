package com.example.muhammadimran.campusrequirementssystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import com.example.muhammadimran.campusrequirementssystem.AdminPanel.AdminSignIn;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.Company_SignUp_Login;
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
        OnCompanyClick();
        OnAdminClick();
    }

    public void OnStudentClick() {
        student.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().add(R.id.mainframe, new StudentLogin_signUp()).commit());
    }

    public void OnCompanyClick() {
        company.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().add(R.id.mainframe, new Company_SignUp_Login()).commit());
    }

    public void OnAdminClick() {
        admin.setOnClickListener(view -> getSupportFragmentManager().beginTransaction().add(R.id.mainframe, new AdminSignIn()).commit());
    }
}
