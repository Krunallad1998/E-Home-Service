package com.example.homeservice.User;


import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserPost {
    String Title, Description, New , Userid;

    public UserPost(){}

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getTitle() {
        return Title;
    }


    public String getDescription() {
        return Description;
    }

    public String getStatus() {
        return New;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setStatus(String status) {
        this.New = status;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Title", Title);
        result.put("Description", Description);
        result.put("Status", New);
        return result;
    }
}
