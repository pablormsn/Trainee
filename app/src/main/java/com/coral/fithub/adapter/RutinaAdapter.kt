package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Rutina

class RutinaAdapter(private val rutinas: List<Rutina>) :
    RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    class RutinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rutinaName: TextView = itemView.findViewById(R.id.textRutinaName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_show_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, pos: Int) {
        val rutina = rutinas[pos]
        holder.rutinaName.text = rutina.nombre
    }

    override fun getItemCount() = rutinas.size
}
