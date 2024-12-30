// filepath: /c:/Users/probl/Desktop/FitHub/app/src/main/java/com/coral/fithub/EjerciciosListActivity.kt
package com.coral.fithub

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.adapter.EjercicioListAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EjerciciosListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_list)

        val recyclerViewEjercicios = findViewById<RecyclerView>(R.id.recyclerViewEjerciciosList)
        recyclerViewEjercicios.layoutManager = LinearLayoutManager(this)

        val db = DatabaseProvider.getDatabase(this)
        val ejercicioDao = db.ejercicioDao()

        CoroutineScope(Dispatchers.IO).launch {
            val ejercicios = ejercicioDao.getAll()
            withContext(Dispatchers.Main) {
                recyclerViewEjercicios.adapter = EjercicioListAdapter(ejercicios) { ejercicio ->
                    val intent = Intent(this@EjerciciosListActivity, ShowEjercicioActivity::class.java)
                    intent.putExtra("idEjercicio", ejercicio.idEjercicio)
                    startActivity(intent)
                }
            }
        }
    }
}