package Conexiones

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AdminSQLiteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table administradores(nombre text primary key,password text)")
        db.execSQL("create table pilotos(nombre text primary key,password text, edad int, experencia int, imagen blob, primerLog text)")
        db.execSQL("create table naves(matricula int primary key,tipo text, aptoCarga text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {  }

}
