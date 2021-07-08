package com.example.homeservice.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;

public class MakeSelection extends AppCompatActivity {
     Button btn_mobile_otp,btn_email_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_password_partition );

        btn_mobile_otp = findViewById( R.id.mobile_btn );
        btn_email_otp = findViewById( R.id.mail_btn );

        btn_mobile_otp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeSelection.this,Mobile_Number.class);
                startActivity( intent );
            }
        } );

        btn_email_otp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MakeSelection.this,Email_id.class);
                startActivity( intent1 );
            }
        } );


    }


}

