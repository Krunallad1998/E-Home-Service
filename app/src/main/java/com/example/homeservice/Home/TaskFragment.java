package com.example.homeservice.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.homeservice.R;
import com.example.homeservice.Task.PageAdapter;
import com.example.homeservice.Task.UserFragment;
import com.example.homeservice.Task.WorkerFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class TaskFragment extends Fragment {
    private FragmentManager supportFragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public TaskFragment(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate( R.layout.fragment_task,container,false);
        tabLayout = view.findViewById(R.id.tabLayout2);
        viewPager = view.findViewById( R.id.viewPager1 );
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        String userid =  firebaseAuth.getCurrentUser().getUid();
        System.out.println("Userid"+userid);

        if(userid.equals("9NxnuXyL9TO7Ae1r0tZ42yAjI6L2")) {
            ((ViewGroup) tabLayout.getChildAt( 0 )).getChildAt( 0 ).setVisibility( View.GONE );
            //Set the next  tab as selected tab
            TabLayout.Tab tab = tabLayout.getTabAt( 1 );
            tab.select();
        }
        if( userid.equals("OF6ctY0sdHRwjA1bfTOYbOiOHG93")) {
            ((ViewGroup) tabLayout.getChildAt( 0 )).getChildAt( 1 ).setVisibility( View.GONE );
            //Set the next  tab as selected tab
            TabLayout.Tab tab = tabLayout.getTabAt( 0 );
            tab.select();
        }


        com.example.homeservice.Task.PageAdapter adapter1 = new PageAdapter( getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount() );
        viewPager.setAdapter( adapter1 );

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
