package com.example.homeservice.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.R;
import com.example.homeservice.main_login;

public class PasswordComplete extends AppCompatActivity {

    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_password_complete );

        btn1 = findViewById( R.id.set_new_password_btn);

        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (PasswordComplete.this, main_login.class );
                startActivity( intent );
            }
        } );
    }
}
