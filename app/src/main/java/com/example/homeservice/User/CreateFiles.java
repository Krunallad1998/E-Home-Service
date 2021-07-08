package com.example.homeservice.User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.FillEventHistory;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homeservice.HomeActivity;
import com.example.homeservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class CreateFiles extends AppCompatActivity {
    ImageView imageView;
    EditText edtxt_title, edttxt_description;
    Button btn_submit;
    private DatabaseReference databaseref;
    private StorageReference mStorageRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    String userid;
    Uri imageUri;
    private Bitmap bitmap;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fragment_create_file );
        imageView = findViewById( R.id.imageBtn );
        edtxt_title = findViewById( R.id.textTitle );
        edttxt_description = findViewById( R.id.textDesc );
        btn_submit = findViewById( R.id.btnpost );
        firebaseAuth=FirebaseAuth.getInstance();
        databaseref = FirebaseDatabase.getInstance().getReference( );
        mStorageRef = FirebaseStorage.getInstance().getReference();
        userid =  firebaseAuth.getCurrentUser().getUid();
        firestore = FirebaseFirestore.getInstance();


        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines( CropImageView.Guidelines.ON ).setAspectRatio( 20,20 ).start( CreateFiles.this );
            }
        } );

        btn_submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog=new ProgressDialog(CreateFiles.this);
                progressDialog.setMessage( "Storing data" );
                progressDialog.show();
                final String desc = edttxt_description.getText().toString();
                final String title = edtxt_title.getText().toString();


                if (!TextUtils.isEmpty(title)  && !TextUtils.isEmpty(desc) && imageUri!=null){
                    File newFile= new File( imageUri.getPath() );
                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile( newFile ) );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress( Bitmap.CompressFormat.JPEG ,100,byteArrayOutputStream);
                    byte[] thumb= byteArrayOutputStream.toByteArray();

                    int x = (int) ( 1 + Math.random()*1000);
                    String s = String.valueOf( x );


                    UploadTask image_path=mStorageRef.child( "post_img" ).child( userid+".jpg" ).putBytes( thumb );
                    image_path.addOnCompleteListener( new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                storePostData(task,title,desc);

                            }else {
                                String err=task.getException().getMessage();
                                Toast.makeText(CreateFiles.this, "Error: "+err, Toast.LENGTH_SHORT  ).show();
                                progressDialog.dismiss();
                            }

                    } });

                }
                else{
                    Toast.makeText( CreateFiles.this, "Fill All Fields", Toast.LENGTH_SHORT ).show();
                }
            }
        } );


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result= CropImage.getActivityResult( data );
            if(resultCode==RESULT_OK){
                imageUri=result.getUri();
                imageView.setImageURI( imageUri );
            }
            else if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                Exception error = result.getError() ;
            }
        }
    }



    private void storePostData(Task<UploadTask.TaskSnapshot> task, String title, String desc) {
        Uri downloadUri=imageUri;

        if(task!=null){
            downloadUri = task.getResult().getUploadSessionUri();
        }else{
            downloadUri = imageUri;
        }


        Map<String,String> userData = new HashMap<>();
        userData.put( "Title",title );
        userData.put( "Description",desc );
        userData.put( "New","New" );
        userData.put( "User_id",userid);
        userData.put( "Post_User_Image",downloadUri.toString() );
        firestore.collection( "posts" ).add( userData ).addOnSuccessListener( new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                progressDialog.dismiss();
                Toast.makeText(CreateFiles.this,"Posting Data is Sucessful" ,Toast.LENGTH_SHORT).show();
                startActivity( new Intent(CreateFiles.this, HomeActivity.class) );
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText( CreateFiles.this,"Firestore Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        } );

    }

    class Post {

        String title,desc,userid;
        Uri downloadUri;

        public Post(String title, String desc, String userid, Uri downloadUri) {
            this.title = title;
            this.desc = desc;
            this.userid = userid;
            this.downloadUri = downloadUri;
        }

    }
}
