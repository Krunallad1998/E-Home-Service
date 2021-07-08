package com.example.homeservice;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homeservice.User.AllFragment;

import java.util.ArrayList;

public class ServiceContent extends AppCompatActivity {
    ListView listView;
    TextView tv1,tv2,tv3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_content);

        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        arrayOfUsers.add(new User( "Tap Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","New"));
        arrayOfUsers.add(new User( "Shower Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","New"));
        arrayOfUsers.add(new User( "Pipe Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","New"));
        arrayOfUsers.add(new User( "Pipe Blockage" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","New"));


        listView = findViewById( R.id.list);
        Myadapter myadapter = new Myadapter( this,arrayOfUsers );
        listView.setAdapter( myadapter );
    }
    class Myadapter extends ArrayAdapter<User> {
        Context context;

        Myadapter( Context context, ArrayList<User> users) {
            super( context, R.layout.worker_row_layout ,users);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row=convertView;
            User user = getItem(position);
            if(convertView==null){
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                row = layoutInflater.inflate( R.layout.row_layout,parent,false );
            }
            tv1 = row.findViewById( R.id.tv_title );
            tv2 = row.findViewById( R.id.tv_descrip );
            tv3 = row.findViewById( R.id.tv_status );
            tv1.setText( user.title );
            tv2.setText( user.description );
            tv3.setText( user.status );


            return row;
        }
    }
    class User{
        String title,description,status;

        public User(String title, String description, String status) {
            this.title = title;
            this.description = description;
            this.status = status;
        }
    }

}
