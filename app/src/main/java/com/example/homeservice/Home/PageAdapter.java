package com.example.homeservice.Home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.homeservice.Chat.MsgFragament;
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

        switch (position) {
            case 0:
                return new TaskFragment();
            case 1:
                return  new MsgFragament();
            case 2:
                return  new SearchFragament();
            case 3:
                return  new ProfileFragament( );
            default:
        return null;
    }
    }

    @Override
    public int getCount() {

        return tabsNumber;
    }
}
