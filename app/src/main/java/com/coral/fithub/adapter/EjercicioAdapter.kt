package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Ejercicio

class EjercicioAdapter(private val ejercicios: List<Ejercicio>) :
    RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    // ViewHolder que representa cada item de la lista
    class EjercicioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ejercicioName: TextView = itemView.findViewById(R.id.textEjercicioName)
        val mejorMarca: TextView = itemView.findViewById(R.id.textMejorMarca)
    }

    // Crear un nuevo ViewHolder y inflar el layout de cada item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ejercicio, parent, false)
        return EjercicioViewHolder(view)
    }

    // Enlazar los datos del modelo Ejercicio con las vistas
    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        val ejercicio = ejercicios[position]
        holder.ejercicioName.text = ejercicio.nombre
        holder.mejorMarca.text = "Mejor Marca: ${ejercicio.mejorMarca ?: "N/A"}"
    }

    // Retorna el número de items en la lista
    override fun getItemCount() = ejercicios.size
}
