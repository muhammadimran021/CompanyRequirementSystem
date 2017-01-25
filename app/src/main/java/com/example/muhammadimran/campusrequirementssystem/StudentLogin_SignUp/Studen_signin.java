package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.UserActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class Studen_signin extends Fragment {
    private EditText Email, Password;
    private FirebaseAuth mAuth;
    private Button signin;
    private ProgressDialog progressDialog;

    public Studen_signin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signin, container, false);
        mAuth = FirebaseAuth.getInstance();
        Email = (EditText) view.findViewById(R.id.Student_email);
        Password = (EditText) view.findViewById(R.id.Student_password);
        signin = (Button) view.findViewById(R.id.Student_loginButton);

        SignIn();
        return view;
    }

    public void SignIn() {
        signin.setOnClickListener(view -> {
            String userEmail = Email.getText().toString();
            String userPassword = Password.getText().toString();
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener(getActivity(), authResult -> {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Plz Wait");
                progressDialog.show();
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                progressDialog.dismiss();
                getActivity().finish();
            });
        });
    }
}
