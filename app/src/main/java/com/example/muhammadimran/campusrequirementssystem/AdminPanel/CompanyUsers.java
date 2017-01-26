package com.example.muhammadimran.campusrequirementssystem.AdminPanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.CompanyAdapter;
import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.CompanyModel;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyUsers extends Fragment {

    DatabaseReference mDatabase;
    ArrayList<CompanyModel> companyModels = new ArrayList<>();
    ListView comapnylist;
    CompanyAdapter adapter;

    public CompanyUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_users, container, false);
        comapnylist = (ListView) view.findViewById(R.id.ComapnyViewuser);
        adapter = new CompanyAdapter(companyModels, getContext());
        comapnylist.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fetchData();
        return view;
    }

    public void fetchData() {
        mDatabase.child("company-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyModel model = dataSnapshot.getValue(CompanyModel.class);
                companyModels.add(new CompanyModel(model.getFname(), model.getLname(), model.getEmail(), model.getPassword(), model.getConfermpassword(), model.getPosition(), model.getGender(), model.getContact()));
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