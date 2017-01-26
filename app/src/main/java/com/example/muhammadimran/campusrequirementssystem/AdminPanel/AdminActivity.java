package com.example.muhammadimran.campusrequirementssystem.AdminPanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.PostJobsByCompany;
import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.UserActivity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    adminFragmentViewAdapter adapter;
    ArrayList<Fragment> arrayList;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAuth = FirebaseAuth.getInstance();
        tabLayout = (TabLayout) findViewById(R.id.Admin_sliding_tabs);
        viewPager = (ViewPager) findViewById(R.id.Admin_viewpager);
        arrayList = new ArrayList<>();

        arrayList.add(new CompanyUsers());
        arrayList.add(new StudentUsers());
        arrayList.add(new PostByCompany());

        tabLayout.addTab(tabLayout.newTab().setText("Company User"));
        tabLayout.addTab(tabLayout.newTab().setText("Students Users"));
        tabLayout.addTab(tabLayout.newTab().setText("Post"));

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
                    case 2:
                        new PostByCompany();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Adminlogout:
                mAuth.signOut();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
