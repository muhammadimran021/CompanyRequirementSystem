package com.example.muhammadimran.campusrequirementssystem.AdminPanel;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.muhammadimran.campusrequirementssystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminSignIn extends Fragment {
    EditText adminEmail, password;
    Button signIn;

    public AdminSignIn() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        adminEmail = (EditText) view.findViewById(R.id.AdminEmail);
        password = (EditText) view.findViewById(R.id.AdminPassword);
        signIn = (Button) view.findViewById(R.id.Admin_loginButton);
        SignIn();

        return view;
    }

    public void SignIn() {
        signIn.setOnClickListener(view -> {
            String email = adminEmail.getText().toString();
            String Password = password.getText().toString();

            if (email.equals("imran021@gmail.com") && Password.equals("khan123")) {
                adminEmail.setText("");
                password.setText("");
                Intent inte = new Intent(getActivity(), AdminActivity.class);
                startActivity(inte);
            }else {
                Toast.makeText(getContext(), "Sorry Access Denied!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
