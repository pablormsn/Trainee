package com.coral.fithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.data.DatabaseHelper
import com.coral.fithub.adapter.RutinaAdapter

class RutinaListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina_list)

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRutinas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener rutinas desde la base de datos
        val dbHelper = DatabaseHelper(this)
        val rutinas = dbHelper.getAllRutinas()

        // Asignar adapter al RecyclerView
        val adapter = RutinaAdapter(rutinas)
        recyclerView.adapter = adapter
    }
}
