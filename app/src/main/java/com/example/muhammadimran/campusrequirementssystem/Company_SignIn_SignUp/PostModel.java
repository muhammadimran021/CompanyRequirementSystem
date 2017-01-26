package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class PostModel {
    private String description;
    private String imageUrl;

    public PostModel() {
    }

    public PostModel(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

