package com.coral.fithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.RutinaAdapter
import com.coral.fithub.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RutinaListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina_list)

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRutinas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener rutinas desde la base de datos
        val db = DatabaseProvider.getDatabase(this)
        val ejercicioDao = db.ejercicioDao()

        // Ejemplo de uso
        CoroutineScope(Dispatchers.IO).launch {
            val ejercicios = ejercicioDao.getAll()
        }
    }
}
