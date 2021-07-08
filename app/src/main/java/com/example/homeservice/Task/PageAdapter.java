package com.example.homeservice.Task;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.auth.FirebaseAuth;

public class PageAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public PageAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super( fm, behavior );
        this.tabsNumber=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        String userid =  firebaseAuth.getCurrentUser().getUid();

        if(userid.equals( "9NxnuXyL9TO7Ae1r0tZ42yAjI6L2" )){
            return  new WorkerFragment();
        }
        else if( userid.equals( "OF6ctY0sdHRwjA1bfTOYbOiOHG93"))  return new UserFragment();
        else{
            switch (position) {
                case 0:
                    return new UserFragment();
                case 1:
                    return  new WorkerFragment();
                default:
                    return null;
            }
        }


    }

    @Override
    public int getCount() {

        return tabsNumber;
    }
}
