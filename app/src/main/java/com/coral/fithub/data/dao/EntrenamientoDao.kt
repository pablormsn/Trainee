package com.coral.fithub.data.dao

import androidx.room.Dao
import androidx.room.*
import com.coral.fithub.data.model.Entrenamiento

@Dao
interface EntrenamientoDao {
    @Insert
    suspend fun insert(entrenamiento: Entrenamiento)

    @Update
    suspend fun update(entrenamiento: Entrenamiento)

    @Delete
    suspend fun delete(entrenamiento: Entrenamiento)

    @Query("SELECT * FROM entrenamiento WHERE id = :id")
    suspend fun get(id: Int): Entrenamiento

    @Query("SELECT * FROM entrenamiento")
    suspend fun getAll(): List<Entrenamiento>

}