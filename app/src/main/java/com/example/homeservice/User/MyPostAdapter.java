package com.example.homeservice.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeservice.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MyPostAdapter extends FirestoreRecyclerAdapter<UserPost,MyPostAdapter.myviewholder> {

    public MyPostAdapter( FirestoreRecyclerOptions<UserPost> options) {
        super( options );
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserPost model) {
        holder.tv1.setText( model.getTitle() );
        holder.tv2.setText( model.getDescription());
        holder.tv3.setText( model.getStatus() );

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.row_layout,parent,false );
        return new myviewholder( view );
    }


    static class myviewholder  extends RecyclerView.ViewHolder{

        TextView tv1,tv2,tv3;
        public myviewholder( View itemView) {
            super( itemView );

            tv1 = itemView.findViewById( R.id.tv_title );
            tv2 = itemView.findViewById( R.id.tv_descrip );
            tv3 = itemView.findViewById( R.id.tv_status );

        }
    }}