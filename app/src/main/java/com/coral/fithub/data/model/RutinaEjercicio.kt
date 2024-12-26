package com.coral.fithub.data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "RutinaEjercicio",
    primaryKeys = ["idRutina", "idEjercicio"],
    foreignKeys = [
        ForeignKey(
            entity = Rutina::class,
            parentColumns = ["idRutina"],
            childColumns = ["idRutina"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ejercicio::class,
            parentColumns = ["idEjercicio"],
            childColumns = ["idEjercicio"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class RutinaEjercicio (
    val idRutina: Int,
    val idEjercicio: Int,
)