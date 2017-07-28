package com.example.admin.movieappv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 28/07/2017.
 */

public class Helper extends SQLiteOpenHelper     {
    static String DATABASE_NAME="TODODATA";
    static int VERSION=1;

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Contract.table_name+" ( "+Contract.MovieId_column+" TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+Contract.table_name+" ;");
        onCreate(sqLiteDatabase);
    }

    public void Insert(String txt){
        SQLiteDatabase sql= getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(Contract.MovieId_column,txt);
        sql.insert(Contract.table_name,null,cv);
    }

    public ArrayList<String> select(){
        SQLiteDatabase sql=getReadableDatabase() ;
        ArrayList<String> list=new ArrayList<>();
        Cursor c=sql.query(Contract.table_name,new String[]{Contract.MovieId_column},null,null,null,null,null);
        c.moveToFirst();

        for (int i=0;i<c.getCount();i++){
            list.add(c.getString(0));
            c.moveToNext();
        }
        return list;
      /*  sql.execSQL("select * from "+Contract.table_name);*/
    }

    public void delet(String del_data){
        SQLiteDatabase sql=getWritableDatabase() ;
        sql.execSQL("delete "+Contract.table_name+" where "+Contract.MovieId_column+" = "+del_data);
    }


}
