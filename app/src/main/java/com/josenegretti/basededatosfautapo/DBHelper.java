package com.josenegretti.basededatosfautapo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by José on 03/11/2015.
 */
public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table beneficiarios (id integer primary key, nombre text, apellidos text, telefono integer, direccion text, capacitador text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists beneficiarios");
        db.execSQL("create table beneficiarios (id integer primary key, nombre text, apellidos text, telefono integer, direccion text, capacitador text)");

    }
}
