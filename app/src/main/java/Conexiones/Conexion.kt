package Conexiones

import Modelo.Piloto
import androidx.appcompat.app.AppCompatActivity

object Conexion {
    var nombreBD = "adminPilotos.db3"

    fun cambiarBD(nombreBD:String){
        this.nombreBD = nombreBD
    }

    fun addPiloto(contexto: AppCompatActivity, p:Piloto){
        val admin = PilotoSQLiteConexion(contexto, nombreBD, null,1)
    }
}