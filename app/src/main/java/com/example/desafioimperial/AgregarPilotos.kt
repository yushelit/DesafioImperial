package com.example.desafioimperial

import Conexiones.Conexion
import Modelo.Piloto
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.desafioimperial.R.drawable
import com.example.desafioimperial.databinding.ActivityAgregarPilotosBinding
import java.io.ByteArrayOutputStream

class AgregarPilotos : AppCompatActivity() {
    lateinit var photo_aux: ByteArray
    lateinit var binding: ActivityAgregarPilotosBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarPilotosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.radioGroup.setOnCheckedChangeListener {radioGroup, i ->
            if(binding.rbPrincipiante.isChecked){
                binding.seekBar.isEnabled = true
                binding.seekBar.min = 0
                binding.seekBar.max = 50
            }
            if(binding.rdAmateur.isChecked){
                binding.seekBar.isEnabled = true
                binding.seekBar.min = 50
                binding.seekBar.max = 100
            }
            if (binding.rbExperto.isChecked) {
                binding.seekBar.isEnabled = false
                binding.numero.text = "100"
            }
        }

        val seek = findViewById<SeekBar>(R.id.seekBar)
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                binding.numero.text = progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        binding.btnRegistrar.setOnClickListener {
            //Password por defecto de los Pilotos es StormTrooper cuando inicien sesion por primera vez
            // tienen que cambiar la contraseña
            if(binding.nombrePiloto.text.isNotEmpty() && binding.txtEdad.text.isNotEmpty() && binding.numero.text.toString().toInt()>0){

                val stream = ByteArrayOutputStream()
                var imagen = BitmapFactory.decodeResource(resources, R.drawable.piloto)
                imagen.compress(Bitmap.CompressFormat.PNG, 100, stream)
                photo_aux = stream.toByteArray()

                var p : Piloto =
                Piloto(binding.nombrePiloto.text.toString(), "a",binding.txtEdad.text.toString().toInt(),
                    binding.numero.text.toString().toInt(), photo_aux,"si")
                Conexion.addPiloto(this,p)
                Toast.makeText(this, "Piloto agregado", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show()
            }
        }
    }


}