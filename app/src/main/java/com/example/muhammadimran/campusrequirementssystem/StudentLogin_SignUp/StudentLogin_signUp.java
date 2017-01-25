package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muhammadimran.campusrequirementssystem.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentLogin_signUp extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    fragment_adapter adapter;
    ArrayList<Fragment> arrayList;


    public StudentLogin_signUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_student_login_sign_up, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        arrayList = new ArrayList<>();

        arrayList.add(new Studen_signin());
        arrayList.add(new Student_signUp());

        tabLayout.addTab(tabLayout.newTab().setText("Sign In"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign Up"));

        adapter = new fragment_adapter(getActivity().getSupportFragmentManager(), arrayList);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        new Studen_signin();
                        break;
                    case 1:
                        new Student_signUp();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;

    }

}
