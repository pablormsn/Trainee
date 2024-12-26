package com.coral.fithub.data.model

import androidx.annotation.*
import androidx.room.*
import java.util.Date

@Entity(
    tableName = "Ejercicio",
    foreignKeys = [
        ForeignKey(
            entity = Rutina::class,
            parentColumns = ["idRutina"],
            childColumns = ["idRutina"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ejercicio (
    @PrimaryKey(autoGenerate = true)
    val idEjercicio: Int,

    val mejorMarca: Float?
)