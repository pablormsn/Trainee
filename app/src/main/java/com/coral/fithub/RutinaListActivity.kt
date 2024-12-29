package com.coral.fithub

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.RutinaAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.coral.fithub.data.model.Rutina

class RutinaListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewNoRutinas: TextView
    private lateinit var addRutinaLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutina_list)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewRutinas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        textViewNoRutinas = findViewById(R.id.textViewNoRutinas)

        addRutinaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Actualizar la lista de rutinas
                loadRutinas()
            }
        }


        // Configurar FloatingActionButton
        val fabAddRutina = findViewById<FloatingActionButton>(R.id.fabAddRutina)
        fabAddRutina.setOnClickListener {
            val intent = Intent(this, AddRutinaActivity::class.java)
            addRutinaLauncher.launch(intent)
        }

        // Cargar rutinas
        loadRutinas()
    }


    private fun loadRutinas() {
        val db = DatabaseProvider.getDatabase(this)
        val rutinaDao = db.rutinaDao()

        CoroutineScope(Dispatchers.IO).launch {
            val rutinas = rutinaDao.getAll()
            withContext(Dispatchers.Main) {
                if (rutinas.isEmpty()) {
                    textViewNoRutinas.visibility = TextView.VISIBLE
                    recyclerView.visibility = RecyclerView.GONE
                } else {
                    textViewNoRutinas.visibility = TextView.GONE
                    recyclerView.visibility = RecyclerView.VISIBLE
                    recyclerView.adapter = RutinaAdapter(rutinas, { rutina ->
                        val intent = Intent(this@RutinaListActivity, ShowRutinaActivity::class.java)
                        intent.putExtra("idRutina", rutina.idRutina)
                        startActivity(intent)
                    }, { rutina ->
                        deleteRutina(rutina)
                    })
                }
            }
        }
    }

    private fun deleteRutina(rutina: Rutina) {
        val db = DatabaseProvider.getDatabase(this)
        val rutinaDao = db.rutinaDao()

        CoroutineScope(Dispatchers.IO).launch {
            rutinaDao.delete(rutina)
            withContext(Dispatchers.Main) {
                loadRutinas()
            }
        }
    }
}
