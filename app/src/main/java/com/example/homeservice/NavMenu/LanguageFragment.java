package com.example.homeservice.NavMenu;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.homeservice.R;

import java.util.Locale;

public class LanguageFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        loadLocale();
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle( getResources().getString( R.string.app_name ) );
        setContentView( R.layout.fragment_language );*/
/*

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
*/
/*

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
*/

        showChangeLanguageDailog();

    }

    private void showChangeLanguageDailog() {
        final String[] listItems = {"English", "Hindi", "Gujarati", "Urdu"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder( LanguageFragment.this );
        mBuilder.setTitle( "Choose Language....." );
        mBuilder.setSingleChoiceItems( listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    setLocale( "En" );
                    recreate();
                }
                if (i == 1) {
                    setLocale( "hi" );
                    recreate();
                }
                if (i == 2) {
                    setLocale( "gu" );
                    recreate();
                } else if (i == 3) {
                    setLocale( "ur" );
                    recreate();
                }

                Dialog dialogInterface = null;
                dialogInterface.dismiss();
            }
        } );

        AlertDialog mDailog = mBuilder.create();
        mDailog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale( lang );
        Locale.setDefault( locale );
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration( config, getBaseContext().getResources().getDisplayMetrics() );
        SharedPreferences.Editor editor = getSharedPreferences( "Setting", MODE_PRIVATE ).edit();
        editor.putString( "My_Lang", lang );
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences( "Setting", Activity.MODE_PRIVATE );
        String language = prefs.getString( "My_Lang", "" );
        setLocale( language );
    }
}