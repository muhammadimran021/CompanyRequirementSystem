package com.example.muhammadimran.campusrequirementssystem.StudentLogin_SignUp;

/**
 * Created by muhammad imran on 1/25/2017.
 */

public class UserInfoModel {
    private String FName;
    private String Lname;
    private String Email;
    private String Password;
    private String CPassword;
    private String Grade;
    private String Gender;
    private String Contact;

    public UserInfoModel() {
    }

    public UserInfoModel(String FName, String lname, String email, String password, String CPassword, String grade, String gender, String contact) {
        this.FName = FName;
        Lname = lname;
        Email = email;
        Password = password;
        this.CPassword = CPassword;
        Grade = grade;
        Gender = gender;
        Contact = contact;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCPassword() {
        return CPassword;
    }

    public void setCPassword(String CPassword) {
        this.CPassword = CPassword;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
