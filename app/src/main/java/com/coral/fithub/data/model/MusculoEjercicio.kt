package com.coral.fithub.data.model

import androidx.room.*

@Entity(
    tableName = "MusculoEjercicio",
    primaryKeys = ["idMusculo", "idEjercicio"],
    foreignKeys = [
        ForeignKey(
            entity = Musculo::class,
            parentColumns = ["idMusculo"],
            childColumns = ["idMusculo"],
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
class MusculoEjercicio (

    val idMusculo: Int,


    val idEjercicio: Int
)