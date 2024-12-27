package com.coral.fithub.data.model

import androidx.room.*

@Entity(
    tableName = "Rutina"
)
data class Rutina (
    @PrimaryKey(autoGenerate = true)
    val idRutina: Int,
    val nombre: String,
    val descripcion: String
)