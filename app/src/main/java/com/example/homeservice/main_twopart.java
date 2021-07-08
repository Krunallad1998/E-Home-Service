package com.example.homeservice;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class main_twopart extends AppCompatActivity {
    Button btncust, btnworker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twopart);

        btncust = findViewById(R.id.cus_btn);
        btnworker = findViewById(R.id.wor_btn);

        btncust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(main_twopart.this,cust_rgts.class);
                startActivity(myintent);
            }
        });

        btnworker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myi = new Intent(main_twopart.this,worker_rgts.class);
                startActivity(myi);
            }
        });
    }
}
