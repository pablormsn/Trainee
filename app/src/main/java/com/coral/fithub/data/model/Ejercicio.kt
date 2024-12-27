package com.coral.fithub.data.model

import androidx.annotation.*
import androidx.room.*
import java.util.Date

@Entity(
    tableName = "Ejercicio"
)
data class Ejercicio (
    @PrimaryKey(autoGenerate = true)
    val idEjercicio: Int,

    val mejorMarca: Float?,

    val nombre: String
)