package com.example.homeservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ShareFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        ShareAppLink();

    }

    private void ShareAppLink() {
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType( "text/plain" );
       String shareBody = "\n Let me Recommend you this application\n\n";
       String shareMessage = shareBody + "http://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID+"\n\n";
       myIntent.putExtra( Intent.EXTRA_SUBJECT,shareMessage );
       myIntent.putExtra( Intent.EXTRA_TEXT,shareBody );
       startActivity( Intent.createChooser( myIntent,"Share Using" ) );
    }
}
