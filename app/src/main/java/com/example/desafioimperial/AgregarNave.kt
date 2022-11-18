package com.example.desafioimperial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.desafioimperial.databinding.ActivityAgregarNaveBinding

class AgregarNave : AppCompatActivity() {
    lateinit var binding : ActivityAgregarNaveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarNaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lista = resources.getStringArray(R.array.tiposNaves)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        binding.spnTipo.adapter = adaptador

        binding
    }
}