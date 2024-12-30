package com.coral.fithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EntrenamientoAdapter
import com.coral.fithub.adapter.EntrenamientoListaAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Entrenamiento
import com.coral.fithub.data.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EntrenamientosListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamientos_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerViewEntrenamientos = findViewById<RecyclerView>(R.id.recyclerViewEntrenamientos)
        recyclerViewEntrenamientos.layoutManager = LinearLayoutManager(this)

        val db = DatabaseProvider.getDatabase(this)
        val entrenamientoDao = db.entrenamientoDao()

        CoroutineScope(Dispatchers.IO).launch {
            val entrenamientos = entrenamientoDao.getAll()
            withContext(Dispatchers.Main) {
                recyclerViewEntrenamientos.adapter = EntrenamientoListaAdapter(entrenamientos,
                    onDeleteClickListener = { entrenamiento ->
                        deleteEntrenamiento(entrenamiento)
                    })
            }
        }
    }

    private fun deleteEntrenamiento(entrenamiento: Entrenamiento) {
        val db = DatabaseProvider.getDatabase(this)
        val entrenamientoDao = db.entrenamientoDao()

        CoroutineScope(Dispatchers.IO).launch {
            entrenamientoDao.delete(entrenamiento)
        }
    }
}