package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Entrenamiento

class EntrenamientoListaAdapter(
    private val entrenamientos: List<Entrenamiento>,
    private val onDeleteClickListener: (Entrenamiento) -> Unit
) : RecyclerView.Adapter<EntrenamientoListaAdapter.EntrenamientoViewHolder>() {

    inner class EntrenamientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entrenamientoNombre: TextView = itemView.findViewById(R.id.textEntrenamientoNombre)
        val deleteButton: ImageButton = itemView.findViewById(R.id.buttonDeleteEntrenamiento)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener(entrenamientos[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntrenamientoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entrenamiento, parent, false)
        return EntrenamientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntrenamientoViewHolder, position: Int) {
        val entrenamiento = entrenamientos[position]
        holder.entrenamientoNombre.text = "Entrenamiento ${entrenamiento.idEntrenamiento}"
    }

    override fun getItemCount(): Int {
        return entrenamientos.size
    }
}