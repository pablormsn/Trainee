package com.coral.fithub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowRutinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_rutina)

        val idRutina = intent.getIntExtra("idRutina", -1)

        if (idRutina != -1) {
            val db = DatabaseProvider.getDatabase(this)
            val rutinaDao = db.rutinaDao()

            CoroutineScope(Dispatchers.IO).launch {
                val rutina = rutinaDao.get(idRutina)
                withContext(Dispatchers.Main) {
                    val textViewNombre = findViewById<TextView>(R.id.textRutinaNombre)
                    val textViewDescripcion = findViewById<TextView>(R.id.textRutinaDescripcion)

                    textViewNombre.text = rutina.nombre
                    textViewDescripcion.text = rutina?.descripcion
                }
            }
        }
    }
}