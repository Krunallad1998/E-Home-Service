package com.example.homeservice.NavMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.homeservice.Home.PageAdapter;
import com.example.homeservice.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private FragmentManager supportFragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public HomeFragment(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate( R.layout.fragment_home1,container,false);
        tabLayout = view.findViewById(R.id.tabLayout1);
        viewPager = view.findViewById( R.id.viewpager );
        

            PageAdapter adapter = new PageAdapter( getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount() );
        viewPager.setAdapter( adapter );

        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {
                viewPager.setCurrentItem( tabs.getPosition() );
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
