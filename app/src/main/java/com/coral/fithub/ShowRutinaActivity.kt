package com.coral.fithub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.adapter.EjerciciosRutinaAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.Rutina
import com.coral.fithub.data.model.RutinaEjercicio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowRutinaActivity : AppCompatActivity() {

    private lateinit var textViewNombre: TextView
    private lateinit var textViewDescripcion: TextView
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var buttonGuardarNombre: Button
    private lateinit var buttonCancelarNombre: Button
    private lateinit var buttonGuardarDescripcion: Button
    private lateinit var buttonCancelarDescripcion: Button
    private lateinit var layoutNombre: LinearLayout
    private lateinit var layoutDescripcion: LinearLayout
    private lateinit var textViewNoEjercicios: TextView
    private lateinit var recyclerViewEjercicios: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_rutina)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idRutina = intent.getIntExtra("idRutina", -1)

        if (idRutina != -1) {
            val db = DatabaseProvider.getDatabase(this)
            val rutinaDao = db.rutinaDao()
            val rutinaEjercicioDao = db.rutinaEjercicioDao()
            val ejercicioDao = db.ejercicioDao()

            textViewNombre = findViewById(R.id.textRutinaNombre)
            textViewDescripcion = findViewById(R.id.textRutinaDescripcion)
            editTextNombre = findViewById(R.id.editTextRutinaNombre)
            editTextDescripcion = findViewById(R.id.editTextRutinaDescripcion)
            buttonGuardarNombre = findViewById(R.id.buttonGuardarNombre)
            buttonCancelarNombre = findViewById(R.id.buttonCancelarNombre)
            buttonGuardarDescripcion = findViewById(R.id.buttonGuardarDescripcion)
            buttonCancelarDescripcion = findViewById(R.id.buttonCancelarDescripcion)
            layoutNombre = findViewById(R.id.layoutNombre)
            layoutDescripcion = findViewById(R.id.layoutDescripcion)
            textViewNoEjercicios = findViewById(R.id.textViewNoEjercicios)
            recyclerViewEjercicios = findViewById(R.id.recyclerViewEjerciciosRutina)

            val buttonEditarNombre = findViewById<Button>(R.id.buttonEditarRutinaNombre)
            val buttonEditarDescripcion = findViewById<Button>(R.id.buttonEditarRutinaDescripcion)
            val buttonEditarEjercicios = findViewById<Button>(R.id.buttonEditarEjerciciosRutina)

            buttonEditarNombre.setOnClickListener {
                editTextNombre.text.clear()
                layoutNombre.visibility = View.VISIBLE
            }

            buttonEditarDescripcion.setOnClickListener {
                editTextDescripcion.text.clear()
                layoutDescripcion.visibility = View.VISIBLE
            }

            buttonGuardarNombre.setOnClickListener {
                val nuevoNombre = editTextNombre.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val rutina = rutinaDao.get(idRutina)
                    if (nuevoNombre.isNotEmpty()) {
                        rutina.nombre = nuevoNombre
                        rutinaDao.update(rutina)
                    }

                    withContext(Dispatchers.Main) {
                        textViewNombre.text = rutina.nombre
                        layoutNombre.visibility = View.GONE
                    }
                }
            }

            buttonCancelarNombre.setOnClickListener {
                layoutNombre.visibility = View.GONE
            }

            buttonGuardarDescripcion.setOnClickListener {
                val nuevaDescripcion = editTextDescripcion.text.toString()

                CoroutineScope(Dispatchers.IO).launch {
                    val rutina = rutinaDao.get(idRutina)
                    if (nuevaDescripcion.isNotEmpty()) {
                        rutina.descripcion = nuevaDescripcion
                        rutinaDao.update(rutina)
                    }

                    withContext(Dispatchers.Main) {
                        textViewDescripcion.text = if (rutina.descripcion.isNullOrEmpty()) {
                            "No hay descripción"
                        } else {
                            rutina.descripcion
                        }
                        layoutDescripcion.visibility = View.GONE
                    }
                }
            }

            buttonCancelarDescripcion.setOnClickListener {
                layoutDescripcion.visibility = View.GONE
            }

            buttonEditarEjercicios.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    val ejerciciosR = rutinaEjercicioDao.getbyIdRutina(idRutina)
                    val ejerciciosSeleccionados = ejerciciosR.map { re -> ejercicioDao.get(re.idEjercicio) }

                    withContext(Dispatchers.Main) {
                        val intent = Intent(this@ShowRutinaActivity, SelectEjerciciosActivity::class.java)
                        intent.putParcelableArrayListExtra("selectedEjercicios", ArrayList(ejerciciosSeleccionados))
                        startActivityForResult(intent, 1)
                    }
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                val rutina = rutinaDao.get(idRutina)
                val ejerciciosR = rutinaEjercicioDao.getbyIdRutina(idRutina)
                val ejercicios = ejerciciosR.map { re -> ejercicioDao.get(re.idEjercicio) }

                withContext(Dispatchers.Main) {
                    textViewNombre.text = rutina.nombre
                    textViewDescripcion.text = if (rutina.descripcion.isNullOrEmpty()) {
                        "No hay descripción"
                    } else {
                        rutina.descripcion
                    }
                    if (ejercicios.isEmpty()) {
                        textViewNoEjercicios.visibility = View.VISIBLE
                        recyclerViewEjercicios.visibility = View.GONE
                    } else {
                        textViewNoEjercicios.visibility = View.GONE
                        recyclerViewEjercicios.visibility = View.VISIBLE
                        recyclerViewEjercicios.layoutManager = LinearLayoutManager(this@ShowRutinaActivity)
                        recyclerViewEjercicios.adapter = EjerciciosRutinaAdapter(ejercicios) { ejercicio ->
                            val intent = Intent(this@ShowRutinaActivity, ShowEjercicioActivity::class.java)
                            intent.putExtra("idEjercicio", ejercicio.idEjercicio)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val selectedEjercicios = data?.getParcelableArrayListExtra<Ejercicio>("selectedEjercicios") ?: return
            val idRutina = intent.getIntExtra("idRutina", -1)

            if (idRutina != -1) {
                val db = DatabaseProvider.getDatabase(this)
                val rutinaEjercicioDao = db.rutinaEjercicioDao()

                CoroutineScope(Dispatchers.IO).launch {
                    rutinaEjercicioDao.deleteByRutina(idRutina)
                    selectedEjercicios.forEach { ejercicio ->
                        val rutinaEjercicio = RutinaEjercicio(idRutina, ejercicio.idEjercicio)
                        rutinaEjercicioDao.insert(rutinaEjercicio)
                    }

                    withContext(Dispatchers.Main) {
                        // Refresh the list of exercises
                        val ejerciciosR = rutinaEjercicioDao.getbyIdRutina(idRutina)
                        val ejercicios = ejerciciosR.map { re -> db.ejercicioDao().get(re.idEjercicio) }

                        if (ejercicios.isEmpty()) {
                            textViewNoEjercicios.visibility = View.VISIBLE
                            recyclerViewEjercicios.visibility = View.GONE
                        } else {
                            textViewNoEjercicios.visibility = View.GONE
                            recyclerViewEjercicios.visibility = View.VISIBLE
                            recyclerViewEjercicios.layoutManager = LinearLayoutManager(this@ShowRutinaActivity)
                            recyclerViewEjercicios.adapter = EjerciciosRutinaAdapter(ejercicios) { ejercicio ->
                                val intent = Intent(this@ShowRutinaActivity, ShowEjercicioActivity::class.java)
                                intent.putExtra("idEjercicio", ejercicio.idEjercicio)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}