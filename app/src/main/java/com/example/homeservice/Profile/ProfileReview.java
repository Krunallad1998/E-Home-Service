package com.example.homeservice.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homeservice.R;

public class ProfileReview extends androidx.fragment.app.Fragment {
    public ProfileReview(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate( R.layout.fragment_profile_review,container,false);
    }
}
