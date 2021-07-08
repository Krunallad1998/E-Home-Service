package com.example.homeservice.Task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.homeservice.R;
import com.google.android.material.tabs.TabLayout;

public class WorkerFragment extends Fragment {
    private FragmentManager supportFragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public WorkerFragment(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate( R.layout.fragment_worker,container,false);
        tabLayout = view.findViewById(R.id.tabLayout5);
        viewPager = view.findViewById( R.id.viewPager3);


        com.example.homeservice.Work.PageAdapter adapter3 = new com.example.homeservice.Work.PageAdapter( getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount() );
        viewPager.setAdapter( adapter3 );

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
