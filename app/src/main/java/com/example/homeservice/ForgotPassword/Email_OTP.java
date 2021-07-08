package com.example.homeservice.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;

public class Email_OTP extends AppCompatActivity {

    Button btn_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_otp_page );

        btn_verify = findViewById( R.id.verify_OTP );

        btn_verify.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Email_OTP.this, NewPassword.class);
                startActivity( intent );
            }
        } );

    }
}
