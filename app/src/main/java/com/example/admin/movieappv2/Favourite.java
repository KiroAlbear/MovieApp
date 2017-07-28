package com.example.admin.movieappv2;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.Serializable;
import java.util.ArrayList;

public class Favourite extends AppCompatActivity {

    Helper helper;
    Adapter adapter;
    RecyclerView recyclerView;
    ArrayList<Movie> FavouriteList;
    Toolbar toolbar;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        toolbar = (Toolbar) findViewById(R.id.FavouriteToolBar);
        recyclerView = (RecyclerView) findViewById(R.id.favourit_recycleView);

        sqLiteDatabase = openOrCreateDatabase(Helper.DATABASE_NAME,sqLiteDatabase.CREATE_IF_NECESSARY,null);

        setTitle("Favourits");

        setSupportActionBar(toolbar);
        helper = new Helper(this);

       Show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favourite_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.EmptyList) {
            helper.onUpgrade(sqLiteDatabase, 0, 0);
            adapter.notifyDataSetChanged();
            Show();
        }
        return super.onOptionsItemSelected(item);
    }
    public void Show(){
        FavouriteList = helper.select();

        adapter = new Adapter(FavouriteList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

}
