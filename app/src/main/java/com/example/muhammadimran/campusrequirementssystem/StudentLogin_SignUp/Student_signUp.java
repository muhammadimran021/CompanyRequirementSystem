package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Student_signUp extends Fragment {
    private EditText fname, lname, email, password, confermpassword, grade, gender, contact;
    private DatabaseReference database;
    private FirebaseAuth mAuth;
    private Button signup;
    private ProgressDialog progressDialog;
    private HashMap<String, String> hashMap;

    public Student_signUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_sign_up, container, false);
        database = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        fname = (EditText) view.findViewById(R.id.fname_text);
        lname = (EditText) view.findViewById(R.id.lname_text);
        email = (EditText) view.findViewById(R.id.email_text);
        password = (EditText) view.findViewById(R.id.Password);
        confermpassword = (EditText) view.findViewById(R.id.ConferPassword);
        grade = (EditText) view.findViewById(R.id.grade);
        gender = (EditText) view.findViewById(R.id.gender);
        contact = (EditText) view.findViewById(R.id.student_contact);
        signup = (Button) view.findViewById(R.id.SignUp);
        signUp();

        return view;
    }

    public void signUp() {
        signup.setOnClickListener(view -> {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
            progressDialog.setMessage("Loading");
            String Fname = fname.getText().toString();
            String Lname = lname.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();
            String Cpassword = confermpassword.getText().toString();
            String Grade = grade.getText().toString();
            String Gender = gender.getText().toString();
            String Contact = contact.getText().toString();

//error handle
            if (TextUtils.isEmpty(Fname)) {
                fname.setError("Required");
            } else if (TextUtils.isEmpty(Lname)) {
                lname.setError("Required");
            } else if (TextUtils.isEmpty(Email)) {
                email.setError("Required");
            } else if (TextUtils.isEmpty(Password)) {
                password.setError("Required");
            } else if (TextUtils.isEmpty(Cpassword)) {
                confermpassword.setError("Required");
            } else if (TextUtils.isEmpty(Grade)) {
                grade.setError("Required");
            } else if (TextUtils.isEmpty(Gender)) {
                gender.setError("Required");
            } else if (TextUtils.isEmpty(Contact)) {
                contact.setError("Required");
            }

            if (Password.equals(Cpassword)) {
                mAuth.createUserWithEmailAndPassword(Email, Cpassword).addOnCompleteListener(getActivity(), task -> {
//                Toast.makeText(getActivity(), "Complete", Toast.LENGTH_SHORT).show();

                    String getCurrentKey = task.getResult().getUser().getUid();

                    //firebase Database
                    hashMap = new HashMap<>();
                    hashMap.put("FName", Fname);
                    hashMap.put("Lname", Lname);
                    hashMap.put("Email", Email);
                    hashMap.put("Password", Password);
                    hashMap.put("CPassword", Cpassword);
                    hashMap.put("Grade", Grade);
                    hashMap.put("Gender", Gender);
                    hashMap.put("Contact", Contact);

                    database.child("Student-info").child(getCurrentKey).setValue(hashMap);
                    progressDialog.dismiss();
                    new Studen_signin();
                }).addOnFailureListener(getActivity(), e -> {
                    Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                });
            } else {
                Toast.makeText(getActivity(), "Password Not Match!!!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            fname.setText("");
            lname.setText("");
            email.setText("");
            password.setText("");
            confermpassword.setText("");
            grade.setText("");
            gender.setText("");
            contact.setText("");



        });


    }

}
