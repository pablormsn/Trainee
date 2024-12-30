package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Entrenamiento
import com.coral.fithub.data.model.Ejercicio

class EntrenamientoAdapter(
    private val entrenamientos: List<Entrenamiento>
) : RecyclerView.Adapter<EntrenamientoAdapter.EntrenamientoViewHolder>() {

    inner class EntrenamientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entrenamientoNombre: TextView = itemView.findViewById(R.id.textRutinaNombre)
        val recyclerViewEjercicios: RecyclerView = itemView.findViewById(R.id.recyclerViewEjercicios)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntrenamientoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_entrenamiento, parent, false)
        return EntrenamientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntrenamientoViewHolder, position: Int) {
        val entrenamiento = entrenamientos[position]

        // Configurar el RecyclerView para la lista de ejercicios
        holder.recyclerViewEjercicios.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.recyclerViewEjercicios.adapter = EjercicioEntrenamientoAdapter(rutina.ejercicios)
    }

    override fun getItemCount(): Int {
        return entrenamientos.size
    }
}