package com.coral.fithub

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
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

    private lateinit var entrenamientoAdapter: EntrenamientoListaAdapter
    private lateinit var textViewNoEntrenamientos: TextView
    private lateinit var recyclerViewEntrenamientos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamientos_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textViewNoEntrenamientos = findViewById(R.id.textViewNoEntrenamientos)
        recyclerViewEntrenamientos = findViewById(R.id.recyclerViewEntrenamientos)
        recyclerViewEntrenamientos.layoutManager = LinearLayoutManager(this)

        loadEntrenamientos()
    }

    private fun loadEntrenamientos() {
        val db = DatabaseProvider.getDatabase(this)
        val entrenamientoDao = db.entrenamientoDao()

        CoroutineScope(Dispatchers.IO).launch {
            val entrenamientos = entrenamientoDao.getAll()
            withContext(Dispatchers.Main) {
                if (entrenamientos.isEmpty()) {
                    textViewNoEntrenamientos.visibility = TextView.VISIBLE
                    recyclerViewEntrenamientos.visibility = RecyclerView.GONE
                } else {
                    textViewNoEntrenamientos.visibility = TextView.GONE
                    recyclerViewEntrenamientos.visibility = RecyclerView.VISIBLE
                    entrenamientoAdapter = EntrenamientoListaAdapter(entrenamientos.toMutableList(),
                        onDeleteClickListener = { entrenamiento ->
                            deleteEntrenamiento(entrenamiento)
                        },
                        onItemClickListener = { entrenamiento ->
                            val intent = Intent(this@EntrenamientosListActivity, ShowEntrenamientoActivity::class.java)
                            intent.putExtra("idEntrenamiento", entrenamiento.idEntrenamiento)
                            startActivity(intent)
                        })
                    recyclerViewEntrenamientos.adapter = entrenamientoAdapter
                }
            }
        }
    }

    private fun deleteEntrenamiento(entrenamiento: Entrenamiento) {
        val db = DatabaseProvider.getDatabase(this)
        val entrenamientoDao = db.entrenamientoDao()

        CoroutineScope(Dispatchers.IO).launch {
            entrenamientoDao.delete(entrenamiento)
            loadEntrenamientos()
        }
    }
}