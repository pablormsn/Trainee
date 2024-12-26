package com.coral.fithub.data.model

import androidx.room.*
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
    val idEntrenamiento: Int,
    val fechaRealizacion: LocalDate,
    val pesoTotal: Float,
    val idRutina: Int
)
