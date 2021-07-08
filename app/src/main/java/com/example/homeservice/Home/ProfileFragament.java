package com.example.homeservice.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.homeservice.Profile.ProfileDetail;
import com.example.homeservice.Profile.ProfileReview;
import com.example.homeservice.R;
import com.example.homeservice.Task.PageAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProfileFragament extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;


    public ProfileFragament(){

        }
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate( R.layout.fragment_profile,container,false);
            tabLayout = view.findViewById(R.id.tabLayout3);
            viewPager = view.findViewById( R.id.profile_viewpager3 );


            com.example.homeservice.Profile.PageAdapter adapter3 = new com.example.homeservice.Profile.PageAdapter( getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount() );
            viewPager.setAdapter( adapter3);

            tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem( tab.getPosition() );
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            } );
            viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );
            return view;
        }
    }

