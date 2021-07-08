package com.example.homeservice.Profile;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PageAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public PageAdapter( FragmentManager fm, int behavior, int tabs) {
        super( fm, behavior );
        this.tabsNumber=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileDetail();
            case 1:
                return  new ProfileReview();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return false;
    }
}
