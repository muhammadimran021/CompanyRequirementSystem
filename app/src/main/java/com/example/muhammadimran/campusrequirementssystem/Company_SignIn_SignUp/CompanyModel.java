package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class CompanyModel {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String confermpassword;
    private String position;
    private String gender;
    private String contact;

    public CompanyModel() {
    }

    public CompanyModel(String fname, String lname, String email, String password, String confermpassword, String position, String gender, String contact) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.confermpassword = confermpassword;
        this.position = position;
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

    public String getConfermpassword() {
        return confermpassword;
    }

    public void setConfermpassword(String confermpassword) {
        this.confermpassword = confermpassword;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
