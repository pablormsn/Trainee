// filepath: /c:/Users/probl/Desktop/FitHub/app/src/main/java/com/coral/fithub/adapter/EjercicioListAdapter.kt
package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Ejercicio

class EjercicioListAdapter(
    private var ejercicios: List<Ejercicio>,
    private val onItemClickListener: (Ejercicio) -> Unit
) : RecyclerView.Adapter<EjercicioListAdapter.EjercicioViewHolder>() {

    inner class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icono: ImageView = itemView.findViewById(R.id.iconEjercicio)
        val nombre: TextView = itemView.findViewById(R.id.textEjercicioNombreMain)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener(ejercicios[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ejercicio_main, parent, false)
        return EjercicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]
        holder.nombre.text = ejercicio.nombre
        holder.icono.setImageResource(R.drawable.exs) // Set the icon for each exercise
    }

    override fun getItemCount() = ejercicios.size

    fun updateList(newEjercicios: List<Ejercicio>) {
        ejercicios = newEjercicios
        notifyDataSetChanged()
    }
}