package com.coral.fithub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioEntrenamientoAdapter
import com.coral.fithub.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowEntrenamientoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_entrenamiento)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idEntrenamiento = intent.getIntExtra("idEntrenamiento", -1)

        if (idEntrenamiento != -1) {
            val db = DatabaseProvider.getDatabase(this)
            val entrenamientoDao = db.entrenamientoDao()
            val rutinaDao = db.rutinaDao()
            val ejercicioDao = db.ejercicioDao()

            CoroutineScope(Dispatchers.IO).launch {
                val entrenamiento = entrenamientoDao.get(idEntrenamiento)
                val rutina = rutinaDao.get(entrenamiento.idRutina)
                val ejercicios = ejercicioDao.getEjerciciosPorRutina(entrenamiento.idRutina)

                withContext(Dispatchers.Main) {
                    val textViewNombre = findViewById<TextView>(R.id.textRutinaNombreEntrenamiento)
                    val recyclerViewEjercicios = findViewById<RecyclerView>(R.id.recyclerViewEjerciciosEntrenamiento)

                    textViewNombre.text = rutina.nombre

                    recyclerViewEjercicios.layoutManager = LinearLayoutManager(this@ShowEntrenamientoActivity)
                    recyclerViewEjercicios.adapter = EjercicioEntrenamientoAdapter(ejercicios, idEntrenamiento, false)
                }
            }
        }
    }
}