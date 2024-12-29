package com.coral.fithub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.adapter.EjerciciosRutinaAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowRutinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_rutina)

        val idRutina = intent.getIntExtra("idRutina", -1)

        if (idRutina != -1) {
            val db = DatabaseProvider.getDatabase(this)
            val rutinaDao = db.rutinaDao()
            val rutinaEjercicioDao = db.rutinaEjercicioDao()
            val ejercicioDao = db.ejercicioDao()

            CoroutineScope(Dispatchers.IO).launch {
                val rutina = rutinaDao.get(idRutina)
                val ejerciciosR = rutinaEjercicioDao.getbyIdRutina(idRutina)
                val ejercicios = ejerciciosR.map { re -> ejercicioDao.get(re.idEjercicio) }

                withContext(Dispatchers.Main) {
                    val textViewNombre = findViewById<TextView>(R.id.textRutinaNombre)
                    val textViewDescripcion = findViewById<TextView>(R.id.textRutinaDescripcion)
                    val recyclerViewEjercicios = findViewById<RecyclerView>(R.id.recyclerViewEjerciciosRutina)

                    textViewNombre.text = rutina.nombre
                    textViewDescripcion.text = rutina.descripcion

                    recyclerViewEjercicios.layoutManager = LinearLayoutManager(this@ShowRutinaActivity)
                    recyclerViewEjercicios.adapter = EjerciciosRutinaAdapter(ejercicios)
                }
            }
        }
    }
}