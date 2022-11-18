package com.example.desafioimperial

import Conexiones.Conexion
import adaptadores.NavesAdaptadorRecycler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioimperial.databinding.ActivityListadoNavesBinding

class ListadoNaves : AppCompatActivity() {

    lateinit var binding: ActivityListadoNavesBinding
    lateinit var naveRecyclerView : RecyclerView
    var hangar = Conexion.obtenerNaves(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoNavesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        naveRecyclerView = binding.rvNaves
        naveRecyclerView.setHasFixedSize(true)
        naveRecyclerView.layoutManager = LinearLayoutManager(this)
        var miAdapter = NavesAdaptadorRecycler(hangar, this)
        naveRecyclerView.adapter = miAdapter
    }
}