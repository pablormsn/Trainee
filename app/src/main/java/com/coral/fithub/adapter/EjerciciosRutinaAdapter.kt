// filepath: /c:/Users/probl/Desktop/FitHub/app/src/main/java/com/coral/fithub/adapter/EjercicioDetalleAdapter.kt
package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Ejercicio

class EjerciciosRutinaAdapter(
    private val ejercicios: List<Ejercicio>
) : RecyclerView.Adapter<EjerciciosRutinaAdapter.EjercicioViewHolder>() {

    inner class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.textEjercicioNombre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ejercicio_rutina, parent, false)
        return EjercicioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]
        holder.nombre.text = ejercicio.nombre
    }

    override fun getItemCount() = ejercicios.size
}