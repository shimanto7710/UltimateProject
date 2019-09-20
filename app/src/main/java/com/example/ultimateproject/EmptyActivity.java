package com.example.ultimateproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ultimateproject.R;
import com.example.ultimateproject.RecyclerView.RecyclerItemClickListener;
import com.example.ultimateproject.RecyclerView.RecyclerViewAdapter;
import com.example.ultimateproject.RecyclerView.RecyclerViewModel;

import java.util.ArrayList;

public class EmptyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewModel> user_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EmptyActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        user_list.add(new RecyclerViewModel(1, "shimanto", "abc", "xyz"));
        user_list.add(new RecyclerViewModel(1, "shimanto", "abc", "xyz"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView11);
        recyclerViewAdapter = new RecyclerViewAdapter(this, user_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("aaa","clicked");

            }

            @Override
            public void onItemLongClick(View view, int position) {


            }
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
