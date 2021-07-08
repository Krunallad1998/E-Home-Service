package com.example.homeservice.NavMenu;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;

public class PrivacyFragment extends AppCompatActivity {
    WebView webView1;
    public String filename="privacy.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.fragment_privacy);
        webView1 = (WebView) findViewById(R.id.privacyid);
        webView1.getSettings().getJavaScriptEnabled();
        webView1.loadUrl("file:///android_assets/" + filename);
    }
}
