package com.coral.fithub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeDatabase {
            println("Database initialized")
        }
        var db = DatabaseProvider.getDatabase(this)
        var ejercicioDao = db.ejercicioDao()

        CoroutineScope(Dispatchers.IO).launch {
            var llamada = ejercicioDao.getAll()
        }


        // Botones
        val routinesButton: ImageButton = findViewById(R.id.routinesButton)
        val gymButton: ImageButton = findViewById(R.id.gymButton)
        val trainingsButton: ImageButton = findViewById(R.id.trainingsButton)

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

        trainingsButton.setOnClickListener {
            val intent = Intent(this, EntrenamientosListActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Mostrando lista de entrenamientos", Toast.LENGTH_SHORT).show()
        }
    }

    fun initializeDatabase(onInitialized: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            DatabaseProvider.getDatabase(applicationContext)
            onInitialized()
        }
    }
}