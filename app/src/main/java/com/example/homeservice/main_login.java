package com.example.homeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.homeservice.ForgotPassword.MakeSelection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class main_login extends AppCompatActivity {
    Button btn1,btn2,btn3;
    EditText edtxt1,edtxt2;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    public static String current_user_id;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        btn1 = findViewById( R.id.login_forgot );
        btn2 = findViewById(R.id.login_Btn);
        btn3 = findViewById(R.id.btn_partition);
        edtxt1 = findViewById(R.id.login_Name);
        edtxt2 = findViewById(R.id.login_password);


        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(main_login.this, main_twopart.class);
                startActivity(mIntent);
            }
        });

        btn1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(main_login.this, MakeSelection.class);
                startActivity(mIntent);

            }
        } );


    }

    public static String getCurrent_user_id() {
        return current_user_id;
    }

    public void LoginUser(){

        final String email = edtxt1.getText().toString().trim();
        final String pwd = edtxt2.getText().toString();


        if (TextUtils.isEmpty(email)) {
                //Toast.makeText(this,"Please Enter Name").show();
                edtxt1.setError("Email is Required.");
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                //Toast.makeText(this,"Please Enter Middle Name",Toast.LENGTH_LONG).show();
                edtxt2.setError("Password is Required.");
                return;
            }


        fAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(main_login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                current_user_id = fAuth.getCurrentUser().getUid();
                              System.out.println("current usere iid" + current_user_id + "\n\n\n\n\n\n");
                                Toast.makeText(main_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            }else{
                                Toast.makeText(main_login.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT);
                            }
                        }
                    });



        }

}