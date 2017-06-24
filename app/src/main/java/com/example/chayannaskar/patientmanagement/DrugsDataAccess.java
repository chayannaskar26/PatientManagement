package com.example.chayannaskar.patientmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by chayannaskar on 23/06/17.
 */

public class DrugsDataAccess extends SQLiteOpenHelper {

    public DrugsDataAccess(Context context, int version) {
        super(context, "mydb2", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("DDA", "Creation ");
        db.execSQL("create table Prescription (disease text, drug text)");
        //db.execSQL("insert into Prescription (disease, drug) values ('Fever','P 500')");
        //db.execSQL("insert into Prescription (disease, drug) values ('Fever','Naproxen oral')");

        Log.v("DDA", "Creation Successful");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getAllDrugs(String disese){
        SQLiteDatabase db = getReadableDatabase();
        disese = disese.toUpperCase();
        Cursor c = db.rawQuery("select DISTINCT drug from Prescription where disease=?", new String[]{disese});
        Log.v("DA", "disease");
        ArrayList<String> drugslist = null;
        if (c.moveToFirst()) {
            drugslist = new ArrayList<>();
            do{

                Log.v("DDA","cursor : "+c.getString(0));
                drugslist.add(c.getString(0));
                Log.v("DDA","entered");


            }while (c.moveToNext());

        }
        db.close();
        return drugslist;
    }

    public ArrayList<String> getAllAfliction(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select DISTINCT disease from Prescription", null);
        ArrayList<String> diseaselist = null;
        if (c.moveToFirst()) {
            diseaselist = new ArrayList<>();
            do{

                Log.v("DDA","cursor : "+c.getString(0));
                diseaselist.add(c.getString(0));
                Log.v("DDA","entered");


            }while (c.moveToNext());

        }
        db.close();
        return diseaselist;
    }

    public void addToDrugList(Prescription prescription){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        String disease = prescription.getDisease();
        disease = disease.toUpperCase();
        cv.put("disease", disease);
        String drug = prescription.getDrugs();
        drug = drug.toUpperCase();
        cv.put("drug", drug);
        db.insert("Prescription", null, cv);
        db.close();
    }
}

