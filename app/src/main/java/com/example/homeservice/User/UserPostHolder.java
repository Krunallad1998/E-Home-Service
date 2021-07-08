package com.example.homeservice.User;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeservice.R;

public class UserPostHolder extends RecyclerView.ViewHolder {
    TextView tv1,tv2,tv3;
    public UserPostHolder(@NonNull View itemView) {
        super( itemView );
        System.out.println("hello5");
        System.out.println("hello5");
        System.out.println("hello5");
        System.out.println("hello5");
        tv1 = itemView.findViewById( R.id.textTitle );
        tv2 = itemView.findViewById( R.id.textDesc );
        tv3 = itemView.findViewById( R.id.tv_status );
        System.out.println("hello5");
        System.out.println("hello5");
        System.out.println("hello5");
        System.out.println("hello5");
    }
}
