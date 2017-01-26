package com.example.muhammadimran.campusrequirementssystem.Adapter_Company_Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.muhammadimran.campusrequirementssystem.R;
import com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp.UserInfoModel;

import java.util.ArrayList;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class StudentUserAdapter extends BaseAdapter {
    ArrayList<UserInfoModel> arrayList;
    Context context;

    public StudentUserAdapter(ArrayList<UserInfoModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.student_user_view, null);
        TextView studentname = (TextView) view1.findViewById(R.id.StudentUserName);
        TextView studentGrade = (TextView) view1.findViewById(R.id.StudentUserGrade);
        TextView studentEmail = (TextView) view1.findViewById(R.id.StudentUserEmail);
        TextView studentGender = (TextView) view1.findViewById(R.id.StudentUserGender);

        UserInfoModel userInfoModel = arrayList.get(i);
        studentname.setText("Name: " + userInfoModel.getFname() + " " + userInfoModel.getLname());
        studentGrade.setText("Grade: " + userInfoModel.getGrade());
        studentEmail.setText("Email: " + userInfoModel.getEmail());
        studentGender.setText("Gender: " + userInfoModel.getGender());


        return view1;
    }
}
