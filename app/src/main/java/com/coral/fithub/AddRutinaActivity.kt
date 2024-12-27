package com.coral.fithub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRutinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rutina)

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val editTextDescripcion = findViewById<EditText>(R.id.editTextDescripcion)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)

        buttonGuardar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty()) {
                val db = DatabaseProvider.getDatabase(this)
                val rutinaDao = db.rutinaDao()

                CoroutineScope(Dispatchers.IO).launch {
                    val rutina = Rutina(0, nombre, descripcion)
                    rutinaDao.insert(rutina)
                    finish() // Cerrar la actividad después de guardar
                }
            }
        }
    }
}