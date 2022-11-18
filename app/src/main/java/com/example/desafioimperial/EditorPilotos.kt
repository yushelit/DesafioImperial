package com.example.desafioimperial

import Conexiones.Conexion
import Modelo.Piloto
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.desafioimperial.databinding.ActivityEditorPilotosBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class EditorPilotos : AppCompatActivity() {
    lateinit var binding: ActivityEditorPilotosBinding
    var mediaPlayer: MediaPlayer? = null
    private val cameraRequest = 1888
    lateinit var foto: Bitmap
    lateinit var foto_aux: ByteArray
    val piloto :Piloto = intent.getSerializableExtra("keyname") as Piloto

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditorPilotosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.caza)



        binding.nombrePiloto.setText(piloto.nombre)
        binding.txtEdad.setText(piloto.edad.toString())

        if(piloto.primer_log == "si"){
            mediaPlayer!!.start()
        }

        binding.btnSalir.setOnClickListener{
            finish()
        }

        binding.btnCambiar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    cameraRequest
                )
            } else {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, cameraRequest)
            }
        }
        binding.btnGuardar.setOnClickListener {
            if(binding.nombrePiloto.text.isNotEmpty() && binding.txtEdad.text.isNotEmpty() && binding.editTextTextPassword2.text.isNotEmpty()){
                var nuevoPiloto =
                    Piloto(binding.nombrePiloto.text.toString(), binding.editTextTextPassword2.text.toString(), binding.txtEdad.text.toString().toInt(),
                    piloto.experiencia, foto_aux, "no")
                Conexion.modPiloto(this, piloto.nombre, nuevoPiloto)
                Toast.makeText(this, "Se han guardado los cambios", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show()
            }
        }

    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            try {
                if (requestCode == cameraRequest) {

                    foto = data?.extras?.get("data") as Bitmap
                    val stream = ByteArrayOutputStream()
                    foto.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    foto_aux = stream.toByteArray()
                    binding.imageView2.setImageBitmap(foto)

                    var fotoFichero =
                        File(getExternalFilesDir(null), piloto.nombre)

                    var uri = Uri.fromFile(fotoFichero)
                    var fileOutStream = FileOutputStream(fotoFichero)
                    foto.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream);
                    fileOutStream.flush();
                    fileOutStream.close();

                }
            } catch (e: java.lang.Exception) {
                Log.e("Yushe", e.toString())
            }
        }
}