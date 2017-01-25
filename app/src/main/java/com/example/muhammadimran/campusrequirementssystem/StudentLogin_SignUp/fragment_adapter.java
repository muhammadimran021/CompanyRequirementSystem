package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/25/2017.
 */

public class fragment_adapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;


    public fragment_adapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String name = "";
        switch (position) {
            case 0:
                name = "Sign In";
                break;
            case 1:
                name = "Sign Up";
                break;
        }
        return name;
    }
}
