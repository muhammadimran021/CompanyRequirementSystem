package com.example.muhammadimran.campusrequirementssystem.AdminPanel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class adminFragmentViewAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentArrayList;

    public adminFragmentViewAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
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
                name = "Company Users";
                break;
            case 1:
                name = "Student User";
                break;
            case 2:
                name = "Post";
                break;
        }
        return name;
    }
}
