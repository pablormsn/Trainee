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

class EntrenamientoActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_entrenamiento)

       val idRutina = intent.getIntExtra("idRutina", -1)

       if (idRutina != -1) {
           val db = DatabaseProvider.getDatabase(this)
              val rutinaDao = db.rutinaDao()
              val rutinaEjercicioDao = db.rutinaEjercicioDao()
              val ejercicioDao = db.ejercicioDao()

              CoroutineScope(Dispatchers.IO).launch {
                  val rutina = rutinaDao.get(idRutina)
                  val ejerciciosR = rutinaEjercicioDao.getbyIdRutina(idRutina)
                  val ejercicios = ejercicioDao.getEjerciciosPorRutina(idRutina)

                  withContext(Dispatchers.Main) {
                      val textViewNombre = findViewById<TextView>(R.id.textRutinaNombre)
                      val recyclerViewEjercicios =
                          findViewById<RecyclerView>(R.id.recyclerViewEjercicios)

                      textViewNombre.text = rutina.nombre

                      recyclerViewEjercicios.layoutManager =
                          LinearLayoutManager(this@EntrenamientoActivity)
                      recyclerViewEjercicios.adapter = EjercicioEntrenamientoAdapter(ejercicios, rutina.nombre)
                  }
              }
       }
   }
}