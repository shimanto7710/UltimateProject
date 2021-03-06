package com.example.ultimateproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ultimateproject.R;
import com.example.ultimateproject.RecyclerView.MyDividerItemDecoration;
import com.example.ultimateproject.RecyclerView.RecyclerItemClickListener;
import com.example.ultimateproject.RecyclerView.RecyclerViewAdapter;
import com.example.ultimateproject.RecyclerView.RecyclerViewModel;
import com.example.ultimateproject.RecyclerView.SwipeToDeleteCallback;
import com.example.ultimateproject.db.DatabaseConstant;
import com.example.ultimateproject.db.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;

public class EmptyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<RecyclerViewModel> user_list = new ArrayList<>();

    ConstraintLayout constraintLayout;

    DatabaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        //initialize database
        myDbHelper = new DatabaseHelper(getApplicationContext());
        initializeDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EmptyActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        constraintLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout);


//        user_list.add(new RecyclerViewModel(1, "shimanto", "abc", "xyz"));
//        user_list.add(new RecyclerViewModel(1, "shimanto", "abc", "xyz"));
//        user_list.add(new RecyclerViewModel(1, "ahmed", "abc", "xyz"));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView11);
        recyclerViewAdapter = new RecyclerViewAdapter(this, user_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.setAdapter(recyclerViewAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("aaa", "clicked");

            }

            @Override
            public void onItemLongClick(View view, int position) {


            }
        }));

        enableSwipeToDeleteAndUndo();

        getDataFromDatabase();

//        ContentValues contentValues=new ContentValues();
//        contentValues.put(DatabaseConstant.COL_2_TITLE,"Akkas");
//        contentValues.put(DatabaseConstant.COL_3_SUBTITLE,"aaaa");
//        // write data
//        myDbHelper.writeData(contentValues,DatabaseConstant.TABLE_NAME);


        //update database
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseConstant.COL_2_TITLE,"12345");
        contentValues.put(DatabaseConstant.COL_3_SUBTITLE,"00000");
        String selection="id=?";
        String[] strings=new String[]{"4"};
        myDbHelper.updateData(contentValues,selection,strings,DatabaseConstant.TABLE_NAME);


        String whereCls="id=?";
        String[] whereArg=new String[]{"4"};
        myDbHelper.deleteItem(selection,strings,DatabaseConstant.TABLE_NAME);



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.getFilter().filter(newText);
                }

                return true;
            }
        });
        return true;
    }


    /**
     * swipe to delete from recyclerview
     */
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final RecyclerViewModel item = recyclerViewAdapter.getData().get(position);

                recyclerViewAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(constraintLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        recyclerViewAdapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    /**
     * create database or if already created then open it
     */
    public void initializeDatabase() {

        try {
            myDbHelper.createDataBase();
//            Toast.makeText(MainActivitya.this, "Create Success", Toast.LENGTH_SHORT).show();
            Log.d("xxx ", "createDataBase(): success");
        } catch (IOException ioe) {
            Log.d("xxx ", "createDataBase(): failed");
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
            Log.d("xxx ", "openDataBase(): success");
//            Toast.makeText(MainActivitya.this, "open Success", Toast.LENGTH_SHORT).show();
        } catch (SQLException sqle) {
            Log.d("xxx ", "openDataBase(): faild");
            throw sqle;
        }

    }

    /**
     * get data from database
     */
    public void getDataFromDatabase() {
        //        Cursor cursor1 = myDbHelper.getWord(new String[]{"id","title","subTitle"}, "mood=? or mood=?", new String[]{String.valueOf(1), String.valueOf(3)});
        Cursor cursor = myDbHelper.getInfo(new String[]{DatabaseConstant.COL_1_ID, DatabaseConstant.COL_2_TITLE, DatabaseConstant.COL_3_SUBTITLE}, null, null, DatabaseConstant.TABLE_NAME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            RecyclerViewModel recyclerViewModel=new RecyclerViewModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
            user_list.add(recyclerViewModel);
            cursor.moveToNext();
        }
        cursor.close();
    }




}
