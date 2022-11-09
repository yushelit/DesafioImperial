package Conexiones

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class PilotoSQLiteConexion(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int)
    : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) { db.execSQL("create table pilotos(nombre text primary key, edad int, experencia int)") }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {  }

}
