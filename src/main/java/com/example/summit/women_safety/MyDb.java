package com.example.summit.women_safety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Summit on 2/11/2018.
 */

public class MyDb extends SQLiteOpenHelper
{

    static String t_name="userdata";
    static String col1="name";
    static String col2="username";
    static String col3="email";
    static String col4="phone";
    static String col5="password";
    static String col6="phone";
    static String col7="phone";
    static String col8="phone";

    static String createQuery="create table "+t_name+"("+col1+" varchar(30),"+col2+" varchar(30) PRIMARY KEY,"+col3+" varchar(30),"+col4+" number(10),"+col5+" varchar(20),"+col6+" number(10),"+col7+" number(10),"+col8+" number(10));";


    public MyDb(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    long insert_db(String name, String username,String email, String phone ,String password)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,name);
        contentValues.put(col2,username);
        contentValues.put(col3,email);
        contentValues.put(col4,phone);
        contentValues.put(col5,password);
        contentValues.put(col6,phone);
        contentValues.put(col7,phone);
        contentValues.put(col8,phone);


        long insert=sqLiteDatabase.insert(t_name,null,contentValues);
        return insert;
    }

    Cursor logIn(String username , String password)
    {
        String[] col={col1,col2,col3,col4,col5,col6,col7,col8};
        String authenticate=col2+"=? and "+col5+"=?";
        String[] arg={username,password};

        Cursor cursor=getWritableDatabase().query(t_name,col,authenticate,arg,null,null,null);
        return cursor;
    }
}
