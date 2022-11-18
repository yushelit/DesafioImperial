package com.example.desafioimperial

import Conexiones.Conexion
import Modelo.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isNotEmpty
import com.example.desafioimperial.databinding.ActivityAgregarNaveBinding

class AgregarNave : AppCompatActivity() {
    lateinit var binding : ActivityAgregarNaveBinding
    lateinit var tipo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = resources.getStringArray(R.array.tiposNaves)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        binding.spnTipo.adapter = adaptador
        binding.spnTipo.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(lista[position] == "Caza"){
                    binding.imagenNave.setImageResource(R.drawable.caza)
                    tipo = "caza"
                    binding.switchCarga.isEnabled = false
                }else if(lista[position]=="Bombardero"){
                    binding.imagenNave.setImageResource((R.drawable.bombardero))
                    tipo= "bombardero"
                    binding.switchCarga.isEnabled = true
                }else{
                    binding.imagenNave.setImageResource(R.drawable.lanzadera)
                    tipo = "lanzadera"
                    binding.switchCarga.isEnabled = true
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) { }

        }

        binding.btnGuardarNave.setOnClickListener {
            var n : Nave
            if(binding.etnMatricula.text.isNotEmpty() && binding.spnTipo.isNotEmpty()){
                if(tipo == "Caza"){
                    n = Nave(binding.etnMatricula.text.toString().toInt(), "caza", "no", "caza")
                    Conexion.addNave(this, n)
                    Toast.makeText(this, "Nave Insertada", Toast.LENGTH_SHORT).show()
                }else if (tipo == "Bombardero"){
                    if(binding.switchCarga.isChecked){
                        n = Nave(binding.etnMatricula.text.toString().toInt(), "bombardero", "si","bombardero")
                        Conexion.addNave(this, n)
                        Toast.makeText(this, "Nave Insertada", Toast.LENGTH_SHORT).show()
                    }else{
                        n = Nave(binding.etnMatricula.text.toString().toInt(), "bombardero", "no","bombardero")
                        Conexion.addNave(this, n)
                        Toast.makeText(this, "Nave Insertada", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    if(binding.switchCarga.isChecked){
                        n = Nave(binding.etnMatricula.text.toString().toInt(), "lanzadera", "si","lanzadera")
                        Conexion.addNave(this, n)
                        Toast.makeText(this, "Nave Insertada", Toast.LENGTH_SHORT).show()
                    }else{
                        n = Nave(binding.etnMatricula.text.toString().toInt(), "lanzadera", "no","lanzadera")
                        Conexion.addNave(this, n)
                        Toast.makeText(this, "Nave Insertada", Toast.LENGTH_SHORT).show()
                    }
                }
                finish()
            }
        }
    }
}