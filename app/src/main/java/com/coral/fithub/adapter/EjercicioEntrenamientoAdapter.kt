package com.coral.fithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.AddSerieActivity
import com.coral.fithub.R
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Ejercicio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EjercicioEntrenamientoAdapter(
    private val listaDeEjercicios: List<Ejercicio>,
    private val idEntrenamiento: Int
) : RecyclerView.Adapter<EjercicioEntrenamientoAdapter.EjercicioEntrenamientoViewHolder>() {

    class EjercicioEntrenamientoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNombre: TextView = view.findViewById(R.id.textEjercicioNombreEntrenamiento)
        val buttonAddSerie: Button = view.findViewById(R.id.buttonAddSerie)
        val recyclerViewSeries: RecyclerView = view.findViewById(R.id.recyclerViewSeries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioEntrenamientoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ejercicio_entrenamiento, parent, false)
        return EjercicioEntrenamientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioEntrenamientoViewHolder, position: Int) {
        val ejercicio = listaDeEjercicios[position]
        holder.textViewNombre.text = ejercicio.nombre

        // Configurar el RecyclerView para las series
        holder.recyclerViewSeries.layoutManager = LinearLayoutManager(holder.itemView.context)
        loadSeries(holder, ejercicio.idEjercicio)

        holder.buttonAddSerie.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, AddSerieActivity::class.java).apply {
                putExtra("idEjercicio", ejercicio.idEjercicio)
                putExtra("idEntrenamiento", idEntrenamiento)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listaDeEjercicios.size
    }

    private fun loadSeries(holder: EjercicioEntrenamientoViewHolder, idEjercicio: Int) {
        val context = holder.itemView.context
        val db = DatabaseProvider.getDatabase(context)
        val serieDao = db.serieDao()

        CoroutineScope(Dispatchers.IO).launch {
            val series = serieDao.getSeriesByEjercicioAndEntrenamiento(idEjercicio, idEntrenamiento)
            withContext(Dispatchers.Main) {
                holder.recyclerViewSeries.adapter = SerieAdapter(series)
            }
        }
    }
}