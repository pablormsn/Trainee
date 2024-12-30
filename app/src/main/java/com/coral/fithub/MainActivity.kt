package com.coral.fithub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botones
        val routinesButton: ImageButton = findViewById(R.id.routinesButton)
        val gymButton: ImageButton = findViewById(R.id.gymButton)

        // Eventos de clic
        routinesButton.setOnClickListener {
            val intent = Intent(this, RutinaListActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Mostrando tus rutinas", Toast.LENGTH_SHORT).show()
        }

        gymButton.setOnClickListener {
            val intent = Intent(this, EjerciciosListActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Mostrando lista de ejercicios", Toast.LENGTH_SHORT).show()
        }
    }
}