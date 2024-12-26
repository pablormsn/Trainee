package com.coral.fithub.data.model
import androidx.room.*
import androidx.room.Entity

@Entity(tableName = "Musculo")
data class Musculo (
    @PrimaryKey(autoGenerate = true)
    val idMusculo: Int,

    val nombre: String,

    val grupoMuscular:  GrupoMuscular
)
