package com.example.admin.movieappv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.gesture.GestureOverlayView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Admin on 28/07/2017.
 */

public class Helper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "TODODATA";
    static int VERSION = 4;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + Contract.table_name + " ( " + Contract.MovieId_column + " TEXT " +","+Contract.MovieTitle_col+ " TEXT "+
                "," + " TEXT " + "," + Contract.MovieOverView_col + " TEXT " +
                "," + Contract.MovieDate_col + " TEXT " + ","+Contract.MovieImageURL_col+" TEXT "+ ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + Contract.table_name + " ;");
        onCreate(sqLiteDatabase);
    }

    public void Insert(String id, String title, String overview, String dateRelease ,String ImageURL) {
        SQLiteDatabase sql = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Contract.MovieId_column, id);
        cv.put(Contract.MovieTitle_col, title);
        cv.put(Contract.MovieOverView_col, overview);
        cv.put(Contract.MovieDate_col, dateRelease);
        cv.put(Contract.MovieImageURL_col, ImageURL);

        sql.insert(Contract.table_name, null, cv);
    }

    public ArrayList<Movie> select() {
        SQLiteDatabase sql = getReadableDatabase();
        ArrayList<Movie> list = new ArrayList<>();
        Movie movie;
        Cursor c = sql.query(Contract.table_name, new String[]{Contract.MovieId_column, Contract.MovieTitle_col, Contract.MovieOverView_col
                , Contract.MovieDate_col,Contract.MovieImageURL_col}, null, null, null, null, null);
        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {
            movie = new Movie();
            movie.setMovie_id(c.getString(0));
            movie.setName(c.getString(1));
            movie.setOverview(c.getString(2));
            movie.setRelease_date(c.getString(3));
            movie.setImageUrl(c.getString(4));
            list.add(movie);
            c.moveToNext();
        }
        return list;
      /*  sql.execSQL("select * from "+Contract.table_name);*/
    }

    public void delet(String del_data) {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("delete " + Contract.table_name + " where " + Contract.MovieId_column + " = " + del_data);
    }

    public boolean IsMovieExist(String id) {
        SQLiteDatabase sql = getReadableDatabase();
        Movie movie;
        Cursor c = sql.query(Contract.table_name, new String[]{Contract.MovieId_column}, null, null, null, null, null);

        c.moveToFirst();

        for (int i = 0; i < c.getCount(); i++) {

            if (id.equals(c.getString(0))){return true;}

            c.moveToNext();
        }
        return false;
    }


}
