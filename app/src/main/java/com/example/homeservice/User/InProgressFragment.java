package com.example.homeservice.User;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homeservice.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;


public class InProgressFragment extends Fragment {
    RecyclerView listView;
    FirestoreRecyclerAdapter adapter;

    public InProgressFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate( R.layout.fragment_user_inprogress, container, false );
    }



    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState)  {
        super.onViewCreated( view, savedInstanceState );
        FirebaseFirestore.getInstance();
        Query query = FirebaseFirestore.getInstance()
                .collection("posts");
        FirestoreRecyclerOptions<UserPost> options =
                new FirestoreRecyclerOptions.Builder<UserPost>()
                        .setQuery(query, UserPost.class)
                        .build();


        adapter=new MyPostAdapter( options );



        listView = view.findViewById( R.id.recycle_list );
        LinearLayoutManager llm= new LinearLayoutManager(getContext());
        llm.setOrientation( LinearLayoutManager.VERTICAL );
        listView.setLayoutManager( llm );
        listView.setAdapter( adapter );

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}



