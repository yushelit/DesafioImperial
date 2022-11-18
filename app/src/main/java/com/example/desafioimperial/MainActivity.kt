package com.example.desafioimperial

import Conexiones.*
import Modelo.*
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.desafioimperial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var admin = Conexion.buscarAdmin(this, "DarthVader")
        if(admin == null ){
            var vader = Administradores("DarthVader","sith")
            Conexion.addAdmin(this, vader)
        }




        binding.btnLog.setOnClickListener {
            if(binding.etUsername.text.isNotEmpty()){
                var a:Administradores? = Conexion.buscarAdmin(this, binding.etUsername.text.toString())
                var p:Piloto? = Conexion.buscarPiloto(this, binding.etUsername.text.toString())

                if(a!=null && binding.etUsername.text.toString() == a.nombre && a.password == binding.editTextTextPassword.text.toString()){
                    startActivity(Intent(this,MenuVader::class.java))
                    Toast.makeText(this, "Bienvenido Lord Vader", Toast.LENGTH_SHORT).show()
                }else if(p!=null && binding.etUsername.text.toString() == p.nombre  && p.password == binding.editTextTextPassword.text.toString()){

                    Toast.makeText(this, "Bienvenido ${binding.etUsername.text}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, EditorPilotos::class.java)
                    intent.putExtra("keyname", p)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Loggin no encontrado", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Rellena los campos de loggin", Toast.LENGTH_SHORT).show()
            }
        }
    }
}