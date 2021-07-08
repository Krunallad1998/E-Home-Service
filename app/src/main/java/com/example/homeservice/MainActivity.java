package com.example.homeservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {


    private CardView linea1,linea2,linea3,linea4,linea5,linea6,linea7,linea8,linea9,linea10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_service);

        linea1= findViewById(R.id.linea1);
        linea1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea2 = findViewById(R.id.linea2);
        linea2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea3 = findViewById(R.id.linea3);
        linea3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea4 = findViewById(R.id.linea4);
        linea4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea5 = findViewById(R.id.linea5);
        linea5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea6 = findViewById(R.id.linea6);
        linea6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea7 = findViewById(R.id.linea7);
        linea7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea8 = findViewById(R.id.linea8);
        linea8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea9 = findViewById(R.id.linea9);
        linea9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
        linea10 = findViewById(R.id.linea10);
        linea10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MainActivity.this, ServiceContent.class);
                startActivity(mIntent);
            }
        });
    }
}