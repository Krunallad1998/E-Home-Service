package com.example.homeservice.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;

public class Email_id extends AppCompatActivity {

    Button btn_sendOTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_email_id );

        btn_sendOTP = findViewById( R.id.email_btn_sendotp);

        btn_sendOTP.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Email_id.this, Email_OTP.class);
                startActivity( intent );
            }
        } );

    }
}
