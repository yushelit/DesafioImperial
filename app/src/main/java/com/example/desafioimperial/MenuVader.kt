package com.example.desafioimperial

import Conexiones.Conexion
import Modelo.*
import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.example.desafioimperial.databinding.ActivityMenuVaderBinding

class MenuVader : AppCompatActivity() {
    lateinit var binding: ActivityMenuVaderBinding
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuVaderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.sableluz)
        mediaPlayer!!.start()

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                var p: Piloto = data!!.getSerializableExtra("keyname") as Piloto

            }
        }
        binding.btnPiloto.setOnClickListener {
            startActivity(Intent(this,AgregarPilotos::class.java))
        }
        binding.btnNave.setOnClickListener {
            startActivity(Intent(this,AgregarNave::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.vadermenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.close -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}