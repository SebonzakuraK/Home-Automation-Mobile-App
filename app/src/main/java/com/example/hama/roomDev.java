package com.example.hama;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class roomDev extends SQLiteOpenHelper {

    public roomDev( Context context) {
        super(context,"Automation.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DAB) {
        DAB.execSQL("create table LivingRoom( Item TEXT,DateTime TEXT)");
        DAB.execSQL("create table Garage( Item TEXT,DateTime TEXT)");
        DAB.execSQL("create table SecCameras( Item TEXT,DateTime TEXT)");
        DAB.execSQL("create table SecLights( Item TEXT,DateTime TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase DAB, int i, int i1) {

        DAB.execSQL("drop Table if exists LivingRoom");
        DAB.execSQL("drop Table if exists Garaage");
        DAB.execSQL("drop Table if exists SecCameras");
        DAB.execSQL("drop Table if exists SecLights");


    }

//Insert Into Table LivingRoom
        public Boolean insertLV(String Item, String DateTime) {
            SQLiteDatabase DAB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Item", Item);
            contentValues.put("DateTime", DateTime);

//select row to insert into
            long result = DAB.insert("LivingRoom", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                //inserted
                return true;
            }


        }
//Insert Into Table Garage
            public Boolean insertGD(String Item, String DateTime) {
                SQLiteDatabase DAB = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("Item", Item);
                contentValues.put("DateTime", DateTime);


                //Selecting the row
                long result = DAB.insert("Garage", null, contentValues);
                if (result == -1) {
                    //not inserted
                    return false;
                } else {
                    return true;
                }


            }
//Insert Into Table SecCameras

        public Boolean insertSC(String Item, String DateTime) {
            SQLiteDatabase DAB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Item", Item);
            contentValues.put("DateTime", DateTime);


            //Selecting the row
            long result = DAB.insert("SecCameras", null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
//Insert Into Table SecLights


    public Boolean insertSL(String Item, String DateTime) {
        SQLiteDatabase DAB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Item", Item);
        contentValues.put("DateTime", DateTime);


        //Selecting the row
        long result = DAB.insert("SecLights", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    }

