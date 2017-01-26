package com.example.muhammadimran.campusrequirementssystem.AdminPanel;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.muhammadimran.campusrequirementssystem.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    adminFragmentViewAdapter adapter;
    ArrayList<Fragment> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        tabLayout = (TabLayout) findViewById(R.id.Admin_sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.Admin_viewpager);
        arrayList = new ArrayList<>();

        arrayList.add(new CompanyUsers());
        arrayList.add(new StudentUsers());

        tabLayout.addTab(tabLayout.newTab().setText("Company User"));
        tabLayout.addTab(tabLayout.newTab().setText("Students Users"));

        adapter = new adminFragmentViewAdapter(getSupportFragmentManager(), arrayList);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        new CompanyUsers();
                        break;
                    case 1:
                        new StudentUsers();
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

    }
}
