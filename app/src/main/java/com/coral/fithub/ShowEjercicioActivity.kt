package com.coral.fithub

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowEjercicioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_ejercicio)

        val idEjercicio = intent.getIntExtra("idEjercicio", -1)

        if (idEjercicio != -1) {
            val db = DatabaseProvider.getDatabase(this)
            val ejercicioDao = db.ejercicioDao()
            val musculoEjercicioDao = db.musculoEjercicioDao()
            val musculoDao = db.musculoDao()

            CoroutineScope(Dispatchers.IO).launch {
                var ejercicio = ejercicioDao.get(idEjercicio)
                val musculosEjercicio = musculoEjercicioDao.getbyIdEjercicio(idEjercicio)
                val musculos = musculosEjercicio.map { me -> musculoDao.get(me.idMusculo) }

                withContext(Dispatchers.Main) {
                    val textViewNombre = findViewById<TextView>(R.id.textEjerNombre)
                    val textViewMejorMarca = findViewById<TextView>(R.id.textEjerMejorMarca)
                    val textViewMusculos = findViewById<TextView>(R.id.textEjerMusculos)
                    val editTextNuevaMejorMarca = findViewById<TextView>(R.id.editTextNuevaMejorMarca)
                    val buttonGuardarMejorMarca = findViewById<TextView>(R.id.buttonGuardarMejorMarca)
                    val buttonEditarMejorMarca = findViewById<TextView>(R.id.buttonEditarMejorMarca)

                    textViewNombre.text = ejercicio.nombre
                    textViewMejorMarca.text = if (ejercicio.mejorMarca != null) {
                        "Mejor marca: ${ejercicio.mejorMarca}kg"
                    } else {
                        "Mejor marca: N/A"
                    }
                    textViewMusculos.text = musculos.joinToString { it.nombre }

                    buttonEditarMejorMarca.setOnClickListener {
                        (editTextNuevaMejorMarca.text as Editable).clear()
                        editTextNuevaMejorMarca.visibility = View.VISIBLE
                        buttonGuardarMejorMarca.visibility = View.VISIBLE
                    }

                    buttonGuardarMejorMarca.setOnClickListener {
                        val nuevaMejorMarca = editTextNuevaMejorMarca.text.toString().toFloatOrNull()
                        if (nuevaMejorMarca != null) {
                            ejercicio.mejorMarca = nuevaMejorMarca
                            CoroutineScope(Dispatchers.IO).launch {
                                ejercicioDao.update(ejercicio)
                                withContext(Dispatchers.Main) {
                                    textViewMejorMarca.text = "Mejor marca: ${nuevaMejorMarca}kg"
                                    editTextNuevaMejorMarca.visibility = View.GONE
                                    buttonGuardarMejorMarca.visibility = View.GONE
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}