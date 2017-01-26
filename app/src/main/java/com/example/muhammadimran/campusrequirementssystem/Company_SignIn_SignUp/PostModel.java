package com.example.muhammadimran.campusrequirementssystem.Company_SignIn_SignUp;

/**
 * Created by muhammad imran on 1/26/2017.
 */

public class PostModel {
    private String name;
    private String description;
    private String imageUrl;

    public PostModel() {
    }

    public PostModel(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

