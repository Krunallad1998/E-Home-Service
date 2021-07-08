package com.example.homeservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.homeservice.NavMenu.HomeFragment;
import com.example.homeservice.NavMenu.AccountFragment;
import com.example.homeservice.NavMenu.LanguageFragment;
import com.example.homeservice.NavMenu.PrivacyFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView naviView;
    private RatingFragment Ratefragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Ratefragment= new RatingFragment(this);
        Ratefragment.appLaunched();


        drawerLayout = findViewById(R.id.drawer);
        naviView = findViewById(R.id.nav_view);
        naviView.setNavigationItemSelectedListener( this );

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace( R.id.frame,new HomeFragment()).commit();
        }

    }



    @Override
    public void onBackPressed(){
        DrawerLayout drawerLayout = findViewById( R.id.drawer );
        if (drawerLayout.isDrawerOpen( GravityCompat.START )){
            drawerLayout.closeDrawer( GravityCompat.START );
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main_right, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){


        int id = menuItem.getItemId();
        if(id == R.id.item_help) {
            Intent intent  = new Intent(HomeActivity.this,HelpFragment.class);
            startActivity( intent );
            return true;
        } else if (id == R.id.item_share) {
            Intent intent  = new Intent(HomeActivity.this,ShareFragment.class);
            startActivity( intent );
            return true;
        }else if (id == R.id.item_rate) {
            Intent intent  = new Intent(HomeActivity.this,RatingFragment.class);
            startActivity( intent );
            return true;
        }else if (id == R.id.item_about) {
            Intent intent  = new Intent(HomeActivity.this,AboutFragment.class);
            startActivity( intent );
            return true;
        }

        return super.onOptionsItemSelected( menuItem );
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer( GravityCompat.START );
        switch (item.getItemId()){
            case R.id.item_home:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame,new HomeFragment() ).commit();
                break;
            case R.id.item_account:
                Intent intent  = new Intent(HomeActivity.this, AccountFragment.class);
                startActivity( intent );
                break;
            case R.id.item_language:
                Intent intent1  = new Intent(HomeActivity.this, LanguageFragment.class);
                startActivity( intent1 );
                break;
            case R.id.item_privacy:
                Intent intent2  = new Intent(HomeActivity.this, PrivacyFragment.class);
                startActivity( intent2 );
                break;
            case R.id.item_logout:
                logout();
                break;

            default:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame,new HomeFragment() ).commit();
        }
        return true;
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(),main_login.class);
        intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        Toast.makeText( getApplicationContext(),"Sucessfully Logout",Toast.LENGTH_SHORT ).show();
        startActivity(intent);

    }


}
