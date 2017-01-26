package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.example.muhammadimran.campusrequirementssystem.UserActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Company_SignUp_Login extends Fragment {
    private TextView nothave_Accoutn;
    private EditText companyEmail, password;
    private Button CompanySignIn;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    DatabaseReference mdaDatabaseReference;

    public Company_SignUp_Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company__sign_up__login, container, false);
        mdaDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        companyEmail = (EditText) view.findViewById(R.id.Company_Email);
        password = (EditText) view.findViewById(R.id.Company_Password);
        CompanySignIn = (Button) view.findViewById(R.id.Company_loginButton);
        nothave_Accoutn = (TextView) view.findViewById(R.id.nothaveAccount);
        SignIn();
        notHaveAccount();
        return view;
    }

    public void notHaveAccount() {
        nothave_Accoutn.setOnClickListener(view -> getFragmentManager().beginTransaction().add(R.id.Company_frame, new Company_SignUp()).commit());
    }

    public void SignIn() {
        CompanySignIn.setOnClickListener(view -> {
            dialog = new ProgressDialog(getContext());
            dialog.setMessage("plz wait!");
            dialog.show();
            String email = companyEmail.getText().toString();
            String cpassword = password.getText().toString();
            mAuth.signInWithEmailAndPassword(email, cpassword).addOnSuccessListener(getActivity(), authResult -> {

                String userid = authResult.getUser().getUid();
                mdaDatabaseReference.child("company-info").child(userid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        CompanyModel userinfo = dataSnapshot.getValue(CompanyModel.class);
                        if (userinfo != null) {
                            companyEmail.setText("");
                            password.setText("");
                            Intent intent = new Intent(getContext(), CompanyUser.class);
                            startActivity(intent);

                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "Sorry Account Not Match", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }).addOnFailureListener(runnable -> {
                        Toast.makeText(getActivity(), runnable.getMessage(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
            );

        });
    }
}
