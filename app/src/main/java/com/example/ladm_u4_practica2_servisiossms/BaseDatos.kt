package com.example.ladm_u4_practica2_servisiossms

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int)
    : SQLiteOpenHelper(context, name, factory, version){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
                "CREATE TABLE Comida (" +
                        "Lugar VARCHAR(200),"+
                        "Platillo VARCHAR(200)"+
                        ")"
        )//create
        db.execSQL(
                "INSERT INTO Comida (Lugar, Platillo) VALUES " +
                        "('WIPIZ','hamburguesa'),"+
                        "('WIPIZ','hotDog'),"+
                        "('NORMIS','tacos'),"+
                        "('PIO','Hamburgesa carbon')"


        )//insert
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}