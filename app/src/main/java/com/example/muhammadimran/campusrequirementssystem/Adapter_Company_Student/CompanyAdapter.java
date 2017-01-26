package com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp.CompanyModel;
import com.example.muhammadimran.campusrequirementssystem.R;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class CompanyAdapter extends BaseAdapter {

    ArrayList<CompanyModel> companyModels;
    Context context;

    public CompanyAdapter(ArrayList<CompanyModel> companyModels, Context context) {
        this.companyModels = companyModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return companyModels.size();
    }

    @Override
    public Object getItem(int i) {
        return companyModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.company_user_iew, null);
        TextView username = (TextView) view1.findViewById(R.id.CompanyUserName);
        TextView userposition = (TextView) view1.findViewById(R.id.CompanyUserPosition);
        TextView useremail = (TextView) view1.findViewById(R.id.UserCompanyEmail);
        TextView usergender = (TextView) view1.findViewById(R.id.UserCompanyGender);

        CompanyModel model = companyModels.get(i);
        username.setText("Name: " + model.getFname());
        userposition.setText("Position: " + model.getPosition());
        useremail.setText("Email: " + model.getEmail());
        usergender.setText("Gender: " + model.getGender());


        return view1;
    }
}
