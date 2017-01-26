package com.example.muhammadimran.campusrequirementssystem.AdminPanel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student.StudentUserAdapter;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentUsers extends Fragment {
    private DatabaseReference mDatabase;
    private ArrayList<UserInfoModel> arrayList = new ArrayList<>();
    private StudentUserAdapter adapter;
    private ListView StudentsList;

    public StudentUsers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_users, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        StudentsList = (ListView) view.findViewById(R.id.StudentList);
        adapter = new StudentUserAdapter(arrayList,getContext());
        StudentsList.setAdapter(adapter);

        fetchStudentList();
        return view;
    }

    public void fetchStudentList(){
        mDatabase.child("Student-info").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInfoModel users = dataSnapshot.getValue(UserInfoModel.class);
                arrayList.add(new UserInfoModel(users.getFname(), users.getLname(), users.getEmail(), users.getPassword(), users.getcPassword(), users.getGrade(), users.getGender(), users.getContact()));
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
