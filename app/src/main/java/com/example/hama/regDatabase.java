package com.example.hama;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class regDatabase extends SQLiteOpenHelper {


    public regDatabase( Context context) {
        super(context,"UserData.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table UserDetails(Name TEXT primary key, Email TEXT, Password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists UserDetails");

    }
        //Saving New Users
    public Boolean insertUserdata(String Name, String Email, String Password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);

        //Selecting the row

            long result = DB.insert("UserDetails", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }

    }

        //Forgot Password
    public Boolean updateUserdata(String Name, String Email, String Password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //only update the password field, forgot password.

        contentValues.put("Password",Password);
        long results =  DB.update("UserDetails",contentValues, "Name=?", new String[] {Name});
        if(results == -1){
            return false;
        }else{
            return true;
        }
    }
        //Remove User
    public Boolean deleteUserdata(String Name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails where Name = ?", new String[]{Name});
        if (cursor.getCount() > 0) {
            DB = this.getWritableDatabase();
            long results = DB.delete("UserDetails", "Name=?", new String[]{Name});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
    else {
            return false;
        }
    }

    //Compare Password where Username = ?
    public Boolean getPassword(String Name, String Password){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails where Name = ? AND Password = ? ",new String[] {Name,Password});
        if(cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from UserDetails", null);
        return cursor;
    };
}

