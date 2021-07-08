package com.example.homeservice.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.homeservice.R;

public class OpenFragment extends Fragment {
    Button btn_create;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate( R.layout.fragment_user_open,container,false);
        btn_create=view.findViewById( R.id.btncreate );

        btn_create.setOnClickListener( v -> {
            Intent intent = new Intent(getActivity(), CreateFiles.class);
            startActivity( intent );

        } );
        return view;

    }
}

