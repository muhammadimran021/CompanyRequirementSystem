package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.UserActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Studen_signin extends Fragment {
    private EditText Email, Password;
    private FirebaseAuth mAuth;
    private Button signin;
    DatabaseReference mdaDatabase;
    private ProgressDialog progressDialog;

    public Studen_signin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signin, container, false);
        mdaDatabase = FirebaseDatabase.getInstance().getReference();
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

            if (TextUtils.isEmpty(userEmail)) {
                Email.setError("Required");
            } else if (TextUtils.isEmpty(userPassword)) {
                Password.setError("Required");
            }

            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("Plz Wait");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnSuccessListener(getActivity(), authResult -> {
                String userid = authResult.getUser().getUid();
                mdaDatabase.child("Student-info").child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserInfoModel userinfo = dataSnapshot.getValue(UserInfoModel.class);
                        if (userinfo != null) {
                            Email.setText("");
                            Password.setText("");
                            Intent intent = new Intent(getActivity(), UserActivity.class);
                            startActivity(intent);
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Sorry Account Not Match", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });


            }).addOnFailureListener(e -> {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            });
        });
    }
}
