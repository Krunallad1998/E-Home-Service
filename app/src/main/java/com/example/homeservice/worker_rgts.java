package com.example.homeservice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static android.os.Build.ID;

public class worker_rgts extends AppCompatActivity {
    TextView txtView1;
    TextView txtView2;
    TextView txtView3;
    TextView txtView5;
    String gender;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    ProgressBar progressBar;
    EditText edittxt1, edittxt2, edittxt3, edittxt4, edittxt5, edittxt6, edittxt7, edittxt8, edittxt9;
    RadioButton rbtn1, rbtn2, rbtn3;
    private Uri filePath;
    private Uri photoURI;
    private FirebaseAuth firebaseAuth;
    private HttpCookie dataSnapshot;
    FirebaseFirestore firestore;
    private String TAG = null;
    String workerID;
    private Uri contentUri;
    private StorageReference mStorageRef;
    private DatabaseReference databaseref;
    private String gender1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_rgts);

        edittxt1 = findViewById(R.id.etxt_Name);
        edittxt2 = findViewById(R.id.etxt_midName);
        edittxt3 = findViewById(R.id.etxt_lastName);
        edittxt4 = findViewById(R.id.etxt_Addr1);
        edittxt5 = findViewById(R.id.etxt_Addr2);
        edittxt6 = findViewById(R.id.etxt_mobile);
        edittxt7 = findViewById(R.id.etxt_email);
        edittxt8 = findViewById(R.id.etxt_pwd);
        edittxt9 = findViewById(R.id.etxt_cpwd);
        rbtn1 = findViewById(R.id.rbtn_Male);
        rbtn2 = findViewById(R.id.rbtn_Female);
        rbtn3 = findViewById(R.id.rbtn_other);
        txtView1 = findViewById(R.id.txt_workcerti);
        txtView2 = findViewById(R.id.txt_livephoto);
        txtView3 = findViewById(R.id.txt_voterid);
        txtView5 = findViewById( R.id.txt_DOB );
        btn1 = findViewById(R.id.btn_upload);//upload workcerti
        btn2 = findViewById(R.id.btn_liveupload);
        btn3 = findViewById(R.id.btn_voterid);
        btn4 = findViewById(R.id.btn_email);
        btn5 = findViewById(R.id.btn_mob);
        btn6 = findViewById(R.id.btn_workerrgts);
        btn7 = findViewById(R.id.btn_workerlogin);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        databaseref = FirebaseDatabase.getInstance().getReference( "Worker" );
        mStorageRef = FirebaseStorage.getInstance().getReference();


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(worker_rgts.this, main_login.class);
                startActivity(mIntent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrationUser();


            }
        });
        DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d( TAG, "on DataSet: mm/dd/yyy: " + month + "/" + dayOfMonth + "/" + year );
                String date = month + "/" + dayOfMonth + "/" + year;
                txtView5.setText( date );
            }
        };






        //button for live capture photo
        btn2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Click button for livecapturephoto
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);


            }
        } );

        btn3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        } );



    }
    private void SelectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery" , "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder( worker_rgts.this);
        builder.setTitle( "Add Photo !" );
        builder.setItems( options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals( "Take Photo" ))
                {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 2);

                }else if(options[item].equals( "Choose from Gallery" )){
                    Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult( intent2 , 3 );

                }else if (options [item].equals( "Cancel" )){
                    dialog.dismiss();
                }

            }
        } );
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1 && resultCode == RESULT_OK ) {
            UploadImage((Bitmap)data.getExtras().get( "data" ));

        }
        if (requestCode == 2 && requestCode == RESULT_OK ){

            UploadImage( (Bitmap) data.getExtras().get( "data" ) );
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {

            // Get the Uri of data
            contentUri = data.getData();
            UploadImage1();
        }

    }

    private void UploadImage1() {
        if (contentUri != null) {
            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            // Defining the child of storageReference
            StorageReference ref = mStorageRef.child("images/"+ UUID.randomUUID().toString());
            // adding listeners on upload
            // or failure of image
            ref.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot){
                    // Image uploaded successfully
                    // Dismiss dialog
                    progressDialog.dismiss();
                    Toast.makeText(worker_rgts.this,"Image Uploaded!!",Toast.LENGTH_SHORT).show();
                }
            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(worker_rgts.this,"Failed " + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {
                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage( "Uploaded " + (int)progress + "%");
                                }
                            });
        }

    }

    private void UploadImage(Bitmap bitmap) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("WorkerdocumentImages").child("note");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = storageReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast
                        .makeText(worker_rgts.this,
                                "Image failed!!",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Toast
                        .makeText(worker_rgts.this,
                                "Image Uploaded!!",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.d("downloadUrl-->", "" );
            }
        });

    }


    public void registrationUser() {
        final String name = edittxt1.getText().toString().trim();
        final String lname = edittxt2.getText().toString();
        final String mname = edittxt3.getText().toString();
        final String add1 = edittxt4.getText().toString();
        final String add2 = edittxt5.getText().toString();
        final String dob = txtView5.getText().toString();
        final String workcertificate = txtView1.getText().toString();
        final String livecapturephoto = txtView2.getText().toString();
        final String voterid = txtView3.getText().toString();
        final Integer mobNo = edittxt6.getInputType();
        final String email = edittxt7.getText().toString();
        final String pwd = edittxt8.getText().toString();
        final String cpwd = edittxt9.getText().toString();
        final String finalGender = gender1;

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this,"Please Enter Name",Toast.LENGTH_LONG).show();
            edittxt1.setError("Name is Required.");
            return;
        }
        if (TextUtils.isEmpty(mname)) {
            Toast.makeText(this,"Please Enter Middle Name",Toast.LENGTH_LONG).show();
            edittxt2.setError("Middle Name is Required.");
            return;
        }
        if (TextUtils.isEmpty(lname)) {
            Toast.makeText(this,"Please Enter Last Name",Toast.LENGTH_LONG).show();
            edittxt3.setError("Last Name is Required.");
            return;
        }
        if (TextUtils.isEmpty(add1)) {
            edittxt4.setError("Permanent Address is Required.");
            return;
        }
        if (rbtn1.isChecked()) {

            gender = "Male";
        }
        if (rbtn2.isChecked()) {
            gender = "Female";
        }
        if (rbtn3.isChecked()) {
            gender = "Other";
        }
        if (TextUtils.isEmpty(email)) {
            edittxt7.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            edittxt8.setError("Password is Required.");
            return;
        }
        if (TextUtils.isEmpty(cpwd)) {
            edittxt9.setError("Confirmed Password is Required.");
            return;
        }
        if (pwd.length() < 6) {
            edittxt8.setError("Password must greater or equal to 6 character");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(worker_rgts.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(worker_rgts.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                            workerID = firebaseAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firestore.collection("workers").document(workerID);
                            // Create a new user with a first and last name
                            Map<String, Object> worker= new HashMap<>();
                            worker.put("First Name",name);
                            worker.put("Middle Name",mname);
                            worker.put("Last Name",lname);
                            worker.put("Permanent Address",add1);
                            worker.put("Local Address",add2);
                            worker.put( "Gender" , gender1 );
                            worker.put( "WorkerCertificate",workcertificate );
                            worker.put("LiveCapture",livecapturephoto);
                            worker.put( "VoterId",voterid );
                            worker.put("Mobile No.",mobNo);
                            worker.put("Email",email);
                            worker.put("Password",pwd);
                            worker.put("Password Confirm",cpwd);

// Add a new document with a generated ID
                            documentReference.collection("users")
                                    .add(worker)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "OnSuccess: Customer profile is Creater for " + workerID);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {


                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "Error adding document" + e.toString());
                                        }
                                    });
                            startActivity(new Intent(getApplicationContext(), main_login.class));
                        }else{
                            Toast.makeText(worker_rgts.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rbtn_Male:
                gender1 = "Male";
                break;
            case R.id.rbtn_Female:
                gender1 = "Female";
                break;
            case R.id.rbtn_other:
                gender1 = "Other";
                break;
        }
    }
}


