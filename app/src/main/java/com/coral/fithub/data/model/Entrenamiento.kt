package com.coral.fithub.data.model

import androidx.room.*
import org.w3c.dom.Text
import java.time.LocalDate

@Entity(
    tableName = "Entrenamiento",
    foreignKeys = [
        ForeignKey(
            entity = Rutina::class,
            parentColumns = ["idRutina"],
            childColumns = ["idRutina"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Entrenamiento (
    @PrimaryKey(autoGenerate = true)
    val idEntrenamiento: Int,
    val fechaRealizacion: Long?,
    val pesoTotal: Float?,
    val idRutina: Int
)
