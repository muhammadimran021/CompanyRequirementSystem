package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;

/**
 * Created by muhammad imran on 1/25/2017.
 */

public class UserInfoModel {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String cPassword;
    private String grade;
    private String gender;
    private String contact;

    public UserInfoModel() {
    }

    public UserInfoModel(String fname, String lname, String email, String password, String cPassword, String grade, String gender, String contact) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.cPassword = cPassword;
        this.grade = grade;
        this.gender = gender;
        this.contact = contact;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
