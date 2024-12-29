package com.coral.fithub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectEjerciciosActivity : AppCompatActivity() {

    private lateinit var ejercicioAdapter: EjercicioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_ejercicios)

        val recyclerViewEjercicios = findViewById<RecyclerView>(R.id.recyclerViewEjercicios)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardarEjercicios)

        recyclerViewEjercicios.layoutManager = LinearLayoutManager(this)

        val db = DatabaseProvider.getDatabase(this)
        val ejercicioDao = db.ejercicioDao()

        CoroutineScope(Dispatchers.IO).launch {
            val ejercicios = ejercicioDao.getAll()
            withContext(Dispatchers.Main) {
                ejercicioAdapter = EjercicioAdapter(ejercicios) { ejercicio, isChecked ->
                    if (isChecked) {
                        ejercicioAdapter.selectedEjercicios.add(ejercicio)
                    } else {
                        ejercicioAdapter.selectedEjercicios.remove(ejercicio)
                    }
                }
                recyclerViewEjercicios.adapter = ejercicioAdapter
            }
        }

        buttonGuardar.setOnClickListener {
            val selectedEjercicios = ejercicioAdapter.getSelectedEjercicios()
            val resultIntent = Intent()
            resultIntent.putExtra("selectedEjercicios", ArrayList(selectedEjercicios))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}