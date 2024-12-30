package com.coral.fithub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coral.fithub.EntrenamientoActivity
import com.coral.fithub.R
import com.coral.fithub.data.database.DatabaseProvider
import com.coral.fithub.data.model.Entrenamiento
import com.coral.fithub.data.model.Rutina
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                    val rutina = rutinas[position]
                    val context = itemView.context
                    createEntrenamiento(context, rutina.idRutina) { idEntrenamiento ->
                        val intent = Intent(context, EntrenamientoActivity::class.java).apply {
                            putExtra("idEntrenamiento", idEntrenamiento)
                        }
                        context.startActivity(intent)
                    }
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

    private fun createEntrenamiento(context: Context, idRutina: Int, onComplete: (Int) -> Unit) {
        val db = DatabaseProvider.getDatabase(context)
        val entrenamientoDao = db.entrenamientoDao()
        CoroutineScope(Dispatchers.IO).launch {
            val entrenamiento = Entrenamiento(0, null, null, idRutina)
            entrenamientoDao.insert(entrenamiento)
            val entrenamientoCreado = entrenamientoDao.getAll().last()
            val idEntrenamiento = entrenamientoCreado.idEntrenamiento
            withContext(Dispatchers.Main) {
                onComplete(idEntrenamiento)
            }
        }
    }
}
