package com.example.homeservice.Work;

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
import androidx.fragment.app.Fragment;

import com.example.homeservice.R;

import java.util.ArrayList;

public class InProgressFragment extends Fragment {
    ListView listView;
    TextView tv1,tv2,tv3;
    public InProgressFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate( R.layout.fragment_worker_inprogress,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        arrayOfUsers.add(new User( "Shower Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","In Progress"));
        arrayOfUsers.add(new User( "Tap Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","In Progress"));
        arrayOfUsers.add(new User( "Pipe Fitting" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","Complete"));
        arrayOfUsers.add(new User( "Pipe Blockage" , "NEED TO CHANGE THE TAP OF THE ALL THE BATHROOMS","In Progress"));


        listView = view.findViewById( R.id.list);
        Myadapter myadapter = new Myadapter( this.getActivity(),arrayOfUsers );
        listView.setAdapter( myadapter );
    }

    class Myadapter extends ArrayAdapter<User> {
        Context context;

        Myadapter( Context context, ArrayList<User> users) {
            super( context, R.layout.row_layout ,users);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row=convertView;
            User user = getItem(position);
            if(convertView==null){
                LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
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
