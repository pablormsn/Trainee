// filepath: /c:/Users/probl/Desktop/FitHub/app/src/main/java/com/coral/fithub/EjerciciosListActivity.kt
package com.coral.fithub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.adapter.EjercicioAdapter
import com.coral.fithub.adapter.EjercicioListAdapter
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.Musculo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EjerciciosListActivity : AppCompatActivity() {

    private lateinit var ejercicioListAdapter: EjercicioListAdapter
    private lateinit var ejercicios: List<Ejercicio>
    private lateinit var musculos: List<Musculo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerViewEjercicios = findViewById<RecyclerView>(R.id.recyclerViewEjerciciosList)
        recyclerViewEjercicios.layoutManager = LinearLayoutManager(this)

        val searchViewEjercicios = findViewById<SearchView>(R.id.searchViewEjercicios)
        val spinnerMusculos = findViewById<Spinner>(R.id.spinnerMusculos)

        val db = DatabaseProvider.getDatabase(this)
        val ejercicioDao = db.ejercicioDao()
        val musculoDao = db.musculoDao()

        CoroutineScope(Dispatchers.IO).launch {
            ejercicios = ejercicioDao.getAll()
            musculos = musculoDao.getAll().sortedBy { it.nombre }
            withContext(Dispatchers.Main) {
                ejercicioListAdapter = EjercicioListAdapter(ejercicios) { ejercicio ->
                    val intent =
                        Intent(this@EjerciciosListActivity, ShowEjercicioActivity::class.java)
                    intent.putExtra("idEjercicio", ejercicio.idEjercicio)
                    startActivity(intent)
                }
                recyclerViewEjercicios.adapter = ejercicioListAdapter

                // Set up the Spinner adapter
                val musculoNames = listOf("Todos") + musculos.map { it.nombre }
                val spinnerAdapter = ArrayAdapter(this@EjerciciosListActivity, android.R.layout.simple_spinner_item, musculoNames)
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerMusculos.adapter = spinnerAdapter

                // Handle Spinner selection
                spinnerMusculos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        val selectedMusculo = if (position == 0) null else musculos[position - 1]
                        filterEjercicios(searchViewEjercicios.query.toString(), selectedMusculo)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        filterEjercicios(searchViewEjercicios.query.toString(), null)
                    }
                }

                // Handle SearchView text change
                searchViewEjercicios.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val selectedMusculo = if (spinnerMusculos.selectedItemPosition == 0) null else musculos[spinnerMusculos.selectedItemPosition - 1]
                        filterEjercicios(newText ?: "", selectedMusculo)
                        return true
                    }
                })
            }
        }
    }

    private fun filterEjercicios(query: String, musculo: Musculo?) {
        CoroutineScope(Dispatchers.IO).launch {
            val filteredEjercicios = if (musculo == null) {
                ejercicios.filter { it.nombre.contains(query, ignoreCase = true) }
            } else {
                val musculoEjercicioDao = DatabaseProvider.getDatabase(this@EjerciciosListActivity).musculoEjercicioDao()
                val musculoEjercicios = musculoEjercicioDao.getbyIdMusculo(musculo.idMusculo)
                val ejercicioIds = musculoEjercicios.map { it.idEjercicio }
                ejercicios.filter { it.idEjercicio in ejercicioIds && it.nombre.contains(query, ignoreCase = true) }
            }
            withContext(Dispatchers.Main) {
                ejercicioListAdapter.updateList(filteredEjercicios)
            }
        }
    }
}