// filepath: /c:/Users/probl/Desktop/FitHub/app/src/main/java/com/coral/fithub/adapter/EjercicioAdapter.kt
package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Ejercicio

class EjercicioAdapter(
    private val ejercicios: List<Ejercicio>,
    private val onItemCheckedChangeListener: (Ejercicio, Boolean) -> Unit
) : RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    val selectedEjercicios = mutableSetOf<Ejercicio>()

    inner class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textEjercicioNombre)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBoxEjercicio)

        init {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val ejercicio = ejercicios[position]
                    onItemCheckedChangeListener(ejercicio, isChecked)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ejercicio, parent, false)
        return EjercicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]
        holder.nombre.text = ejercicio.nombre
        holder.checkBox.isChecked = selectedEjercicios.contains(ejercicio)
    }

    override fun getItemCount() = ejercicios.size

    fun getSelectedEjercicios(): List<Ejercicio> {
        return selectedEjercicios.toList()
    }
}