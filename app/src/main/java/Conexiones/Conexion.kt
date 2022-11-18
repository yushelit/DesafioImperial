package Conexiones

import Modelo.Administradores
import Modelo.Nave
import Modelo.Piloto
import android.content.ContentValues
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    var nombreBD = "Imperio.db3"

    fun cambiarBD(nombreBD:String){
        this.nombreBD = nombreBD
    }

    fun addPiloto(contexto: AppCompatActivity, p:Piloto){
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre",p.nombre)
        registro.put("password",p.password)
        registro.put("edad", p.edad)
        registro.put("experencia",p.experiencia)
        registro.put("imagen",p.imagen)
        registro.put("primerLog","si")
        bd.insert("pilotos", null, registro)
        bd.close()
    }
    fun addAdmin(contexto: AppCompatActivity, a:Administradores){
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre", a.nombre)
        registro.put("password", a.password)
        bd.insert("Administradores", null, registro)
        bd.close()
    }
    fun buscarAdmin(contexto: AppCompatActivity, nombre:String):Administradores? {
        var a:Administradores? = null
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select nombre,password from administradores where nombre='${nombre}'",
            null
        )
        if (fila.moveToFirst()) {
            a = Administradores(nombre, fila.getString(1))
        }
        bd.close()
        return a
    }
    fun addNave(contexto: AppCompatActivity, n:Nave){
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("matricula",n.matricula)
        registro.put("tipo", n.tipo)
        bd.insert("naves", null, registro)
        bd.close()
    }

    fun buscarPiloto(contexto: AppCompatActivity, nombre:String):Piloto? {
        var p:Piloto? = null
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val fila = bd.rawQuery(
            "select nombre, password, edad, experencia, imagen ,primerLog from pilotos where nombre='${nombre}'",
            null
        )
        if (fila.moveToFirst()) {
            p = Piloto(nombre, fila.getString(1), fila.getInt(2), fila.getInt(3), fila.getBlob(4), fila.getString(5))
        }
        bd.close()
        return p
    }

    fun modPiloto(contexto:AppCompatActivity, nombre: String, p:Piloto):Int {
        val admin = AdminSQLiteConexion(contexto, nombreBD, null, 1)
        val bd = admin.writableDatabase
        val registro = ContentValues()
        registro.put("nombre", p.nombre)
        registro.put("edad", p.edad)
        registro.put("password", p.password)
        registro.put("primerLog", "no")
        registro.put("imagen", p.imagen)
        val cant = bd.update("pilotos", registro, "nombre='${nombre}'", null)
        bd.close()
        return cant
    }
}