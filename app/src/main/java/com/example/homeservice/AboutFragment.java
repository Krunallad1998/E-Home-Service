package com.example.homeservice;


import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

        import com.example.homeservice.R;

public class AboutFragment extends Fragment {
    WebView webView1;
    public String filename="about.html";
    public AboutFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        webView1.findViewById(R.id.aboutid);
        webView1.getSettings().getJavaScriptEnabled();
        webView1.loadUrl("file:///android_assets/" + filename);
        return inflater.inflate( R.layout.fragment_about,container,false);
    }
}


