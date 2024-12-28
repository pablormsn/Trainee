package com.coral.fithub;

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.data.dao.RutinaDao;
import com.coral.fithub.data.database.DatabaseProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowRutinaActivity: AppCompatActivity() {

    private lateinit var textViewName: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewExercises: RecyclerView
    private lateinit var textViewNoExercises: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_rutina)

        textViewName = findViewById<TextView>(R.id.RutinaName)
        textViewDescription = findViewById<TextView>(R.id.RutinaDescription)
        textViewExercises = findViewById<RecyclerView>(R.id.ExercisesList)
        textViewNoExercises = findViewById<TextView>(R.id.textViewNoExercises)

        val rutinaId = intent.getIntExtra("rutinaId", 0)
        val rutinaDao = DatabaseProvider.getDatabase(this).rutinaDao()
        CoroutineScope(Dispatchers.Main).launch {
            val rutina = withContext(Dispatchers.IO) {
                rutinaDao.get(rutinaId)
            }
            textViewName.text = rutina.nombre
            textViewDescription.text = rutina.descripcion

            loadExercises(rutinaId)
        }
    }
        private fun loadExercises(rutinaId: Int) {
            val ejercicioDao = DatabaseProvider.getDatabase(this).ejercicioDao()
            CoroutineScope(Dispatchers.Main).launch {
                val ejercicios = withContext(Dispatchers.IO) {
                    ejercicioDao.getEjerciciosPorRutina(rutinaId)
                }
                if (ejercicios.isEmpty()) {
                    textViewNoExercises.visibility = TextView.VISIBLE
                    textViewExercises.visibility = RecyclerView.GONE
                } else {
                    textViewNoExercises.visibility = TextView.GONE
                    textViewExercises.adapter = EjercicioAdapter(ejercicios)
                }
            }
        }
}
