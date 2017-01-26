package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Company_SignUp extends Fragment {
    EditText f_name, l_name, company_email, password, conferm_password, position, gender, contact;
    FirebaseAuth mAuth;
    DatabaseReference mdaDatabase;
    private Button companySignUp;
    private ProgressDialog progressDialog;

    public Company_SignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company__sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();
        mdaDatabase = FirebaseDatabase.getInstance().getReference();

        f_name = (EditText) view.findViewById(R.id.c_fname_text);
        l_name = (EditText) view.findViewById(R.id.c_lname_text);
        company_email = (EditText) view.findViewById(R.id.c_email_text);
        password = (EditText) view.findViewById(R.id.c_Password);
        conferm_password = (EditText) view.findViewById(R.id.c_ConferPassword);
        position = (EditText) view.findViewById(R.id.c_Position);
        gender = (EditText) view.findViewById(R.id.c_gender);
        contact = (EditText) view.findViewById(R.id.c_Company_contact);
        companySignUp = (Button) view.findViewById(R.id.c_SignUp);
        onSignUp();
        return view;
    }

    public void onSignUp() {
        companySignUp.setOnClickListener(view ->
                {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Signing Up");
                    progressDialog.show();
                    String fname = f_name.getText().toString();
                    String lname = l_name.getText().toString();
                    String companyEmail = company_email.getText().toString();
                    String Password = password.getText().toString();
                    String ConfermPassword = conferm_password.getText().toString();
                    String Position = position.getText().toString();
                    String Gender = gender.getText().toString();
                    String Contact = contact.getText().toString();

                    if (Password.equals(ConfermPassword)) {
                        mAuth.createUserWithEmailAndPassword(companyEmail, Password).addOnSuccessListener(getActivity(), authResult -> {
                            String companyId = authResult.getUser().getUid();
                            CompanyModel companyModel = new CompanyModel(fname, lname, companyEmail, Password, ConfermPassword, Position, Gender, Contact);
                            mdaDatabase.child("company-info").child(companyId).setValue(companyModel);
                            f_name.setText("");
                            l_name.setText("");
                            company_email.setText("");
                            password.setText("");
                            conferm_password.setText("");
                            position.setText("");
                            gender.setText("");
                            contact.setText("");
                            progressDialog.dismiss();
                            getFragmentManager().beginTransaction().add(R.id.Company_frame, new Company_SignUp_Login()).commit();
                        });
                    } else {
                        Toast.makeText(getActivity(), "Sorry password not match", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
