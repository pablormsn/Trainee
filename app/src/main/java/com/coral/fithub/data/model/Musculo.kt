package com.coral.fithub.data.model

import androidx.annotation.NonNull
import androidx.room.*

@Entity(tableName = "Musculo")
data class Musculo (
    @PrimaryKey(autoGenerate = true)
    val idMusculo: Int,

    val nombre: String,

    val enum:  GrupoMuscular
)
