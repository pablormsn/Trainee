package com.coral.fithub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Serie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSerieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_serie)

        val idEjercicio = intent.getIntExtra("idEjercicio", -1)
        val idEntrenamiento = intent.getIntExtra("idEntrenamiento", -1)

        val editTextPeso = findViewById<EditText>(R.id.editTextPeso)
        val editTextRepeticiones = findViewById<EditText>(R.id.editTextRepeticiones)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardarSerie)

        buttonGuardar.setOnClickListener {
            val peso = editTextPeso.text.toString().toFloatOrNull()
            val repeticiones = editTextRepeticiones.text.toString().toIntOrNull()

            if (peso != null && repeticiones != null) {
                val db = DatabaseProvider.getDatabase(this)
                val serieDao = db.serieDao()

                CoroutineScope(Dispatchers.IO).launch {
                    val serie = Serie(0, peso, repeticiones, idEjercicio, idEntrenamiento)
                    serieDao.insert(serie)
                    finish()
                }
            }
        }
    }
}