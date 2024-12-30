package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Serie

class SerieAdapter(
    private val seriesList: List<Serie>
) : RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {

    class SerieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewPeso: TextView = view.findViewById(R.id.textPeso)
        val textViewRepeticiones: TextView = view.findViewById(R.id.textRepeticiones)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_serie, parent, false)
        return SerieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        val serie = seriesList[position]
        holder.textViewPeso.text = serie.peso.toString()
        holder.textViewRepeticiones.text = serie.repeticiones.toString()
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }
}