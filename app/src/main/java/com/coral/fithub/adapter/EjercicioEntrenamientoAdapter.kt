package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Ejercicio

class EjercicioEntrenamientoAdapter(
    private val listaDeEntrenamientos: List<Ejercicio>,
    private val nombreRutina: String // Añadir el nombre de la rutina
) : RecyclerView.Adapter<EjercicioEntrenamientoAdapter.EjercicioEntrenamientoViewHolder>() {

    class EjercicioEntrenamientoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewNombre: TextView = view.findViewById(R.id.textEjercicioNombre)
        val textViewRutina: TextView = view.findViewById(R.id.textRutinaNombre) // Añadir TextView para el nombre de la rutina
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioEntrenamientoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ejercicio_entrenamiento, parent, false)
        return EjercicioEntrenamientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EjercicioEntrenamientoViewHolder, position: Int) {
        val ejercicio = listaDeEntrenamientos[position]
        holder.textViewNombre.text = ejercicio.nombre
        holder.textViewRutina.text = nombreRutina // Asignar el nombre de la rutina al TextView
    }

    override fun getItemCount(): Int {
        return listaDeEntrenamientos.size
    }
}