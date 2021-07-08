package com.example.homeservice.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;

public class Mobile_OTP extends AppCompatActivity {

    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_otp_page );

        btn1 = findViewById( R.id.verify_OTP );

        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mobile_OTP.this, NewPassword.class);
                startActivity( intent );
            }
        } );

    }
}
