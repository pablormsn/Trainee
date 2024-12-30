package com.coral.fithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.R
import com.coral.fithub.data.model.Rutina

class RutinaAdapter(
    private val rutinas: List<Rutina>,
    private val onItemClickListener: (Rutina) -> Unit,
    private val onDeleteClickListener: (Rutina) -> Unit,
    private val onStartTrainingClickListener: (Rutina) -> Unit
) :
    RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder>() {

    inner class RutinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rutinaName: TextView = itemView.findViewById(R.id.textRutNombre)
        val deleteButton: ImageButton = itemView.findViewById<ImageButton>(R.id.buttonDeleteRutina)
        val startTrainingButton: ImageButton = itemView.findViewById<ImageButton>(R.id.buttonStartTraining)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener(rutinas[position])
                }
            }
            startTrainingButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onStartTrainingClickListener(rutinas[position])
                }
            }
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDeleteClickListener(rutinas[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutinaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rutina, parent, false)
        return RutinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutinaViewHolder, pos: Int) {
        val rutina = rutinas[pos]
        holder.rutinaName.text = rutina.nombre
    }

    override fun getItemCount() = rutinas.size
}
