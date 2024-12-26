package com.coral.fithub.data.model

import androidx.room.*
import java.time.LocalDate

@Entity
data class Entrenamiento (
    val idEntrenamiento: Int,

    val fechaRealizacion: LocalDate,

    val pesoTotal: Float,

    val idRutina: Int
)
