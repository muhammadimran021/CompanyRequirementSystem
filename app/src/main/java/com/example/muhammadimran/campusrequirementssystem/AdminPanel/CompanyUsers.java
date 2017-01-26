package com.example.muhammadimran.campusrequirementssystem.AdminPanel;


import android.app.ProgressDialog;
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

    private DatabaseReference mDatabase;
    private ArrayList<CompanyModel> companyModels = new ArrayList<>();
    private ListView comapnylist;
    private CompanyAdapter adapter;
    private ProgressDialog dialog;

    public CompanyUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company_users, container, false);
        dialog = new ProgressDialog(getActivity());
        comapnylist = (ListView) view.findViewById(R.id.ComapnyViewuser);
        adapter = new CompanyAdapter(companyModels, getContext());
        comapnylist.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        fetchData();
        return view;
    }

    public void fetchData() {
        dialog.setMessage("Loading...");
        dialog.show();
        mDatabase.child("company-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                CompanyModel model = dataSnapshot.getValue(CompanyModel.class);
                companyModels.add(model);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
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

    @Override
    public void onStart() {
        super.onStart();
    }
}
