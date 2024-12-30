package com.coral.fithub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.Rutina
import com.coral.fithub.data.model.RutinaEjercicio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddRutinaActivity : AppCompatActivity() {

    private var selectedEjercicios: List<Ejercicio> = emptyList()
    private lateinit var textViewEjerciciosAnadidos: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_rutina)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val editTextNombre = findViewById<EditText>(R.id.editRutinaNombre)
        val editTextDescripcion = findViewById<EditText>(R.id.editRutinaDescripcion)
        val buttonAddEjercicios = findViewById<Button>(R.id.buttonAddEjercicios)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
        textViewEjerciciosAnadidos = findViewById(R.id.textViewEjerciciosAnadidos)


        textViewEjerciciosAnadidos.visibility = View.VISIBLE
        textViewEjerciciosAnadidos.text = "No se han seleccionado ejercicios"

        buttonAddEjercicios.setOnClickListener {
            val intent = Intent(this, SelectEjerciciosActivity::class.java)
            intent.putParcelableArrayListExtra("selectedEjercicios", ArrayList(selectedEjercicios))
            startActivityForResult(intent, 1)
        }

        buttonGuardar.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            val descripcion = editTextDescripcion.text.toString()

            if (nombre.isNotEmpty()) {
                val db = DatabaseProvider.getDatabase(this)
                val rutinaDao = db.rutinaDao()

                CoroutineScope(Dispatchers.IO).launch {
                    val rutina = Rutina(0, nombre, descripcion)
                    rutinaDao.insert(rutina)

                    val rutinaId = rutinaDao.getAll().last().idRutina


                    // Asignar ejercicios a la rutina
                    selectedEjercicios.forEach { ejercicio ->
                        val rutinaEjercicio = RutinaEjercicio(rutinaId, ejercicio.idEjercicio)
                        db.rutinaEjercicioDao().insert(rutinaEjercicio)
                    }
                    setResult(Activity.RESULT_OK)
                    finish() // Cerrar la actividad después de guardar
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            selectedEjercicios = data?.getParcelableArrayListExtra("selectedEjercicios") ?: emptyList()
            textViewEjerciciosAnadidos.visibility = View.VISIBLE
            if (selectedEjercicios.isEmpty()) {
                textViewEjerciciosAnadidos.text = "No se han seleccionado ejercicios"
            } else {
                textViewEjerciciosAnadidos.text = "Ejercicios añadidos:\n" + selectedEjercicios.joinToString("\n") { it.nombre }
            }
        }
    }

}