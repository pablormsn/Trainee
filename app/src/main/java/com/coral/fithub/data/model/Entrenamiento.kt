package com.coral.fithub.data.model

import java.time.LocalDate

data class Entrenamiento (
    val idEntrenamiento: Int,
    val fechaRealizacion: LocalDate,
    val pesoTotal: Float,
    val idRutina: Int,
    val idUsuario: Int
)
