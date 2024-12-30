package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Serie

class SeriesAdapter(
    private val seriesList: List<Serie>, // Lista de datos
    private val onDelete: (Serie) -> Unit, // Acción para eliminar una serie
    private val onSerieUpdated: (Serie) -> Unit // Acción para actualizar una serie
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    // 1. Crear el ViewHolder (inicializa las vistas)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false)
        return SeriesViewHolder(view)
    }

    // 2. Enlazar los datos con el ViewHolder
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val serie = seriesList[position]

        // Actualiza las vistas con los datos
        holder.pesoEditText.setText(serie.peso.toString())
        holder.repeticionesEditText.setText(serie.repeticiones.toString())
        holder.completadoCheckBox.isChecked = serie.completado == 1

        // Acciones para cambiar datos
        holder.pesoEditText.addTextChangedListener {
            serie.peso = it.toString().toFloatOrNull() ?: 0f
            onSerieUpdated(serie)
        }

        holder.repeticionesEditText.addTextChangedListener {
            serie.repeticiones = it.toString().toIntOrNull() ?: 0
            onSerieUpdated(serie)
        }

        holder.completadoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            serie.completado = 1
            onSerieUpdated(serie)
        }

        // Botón de eliminar
        holder.deleteButton.setOnClickListener {
            onDelete(serie)
        }
    }

    // 3. Retornar el tamaño de la lista de datos
    override fun getItemCount(): Int {
        return seriesList.size
    }

    // ViewHolder: mantiene las referencias a las vistas de cada item
    class SeriesViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView) {
        val pesoEditText: EditText = itemView.findViewById(R.id.editTextWeight)
        val repeticionesEditText: EditText = itemView.findViewById(R.id.editTextReps)
        val completadoCheckBox: CheckBox = itemView.findViewById(R.id.checkBoxCompleted)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

}
