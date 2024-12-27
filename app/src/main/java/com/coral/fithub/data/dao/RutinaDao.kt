package com.coral.fithub.data.dao

import androidx.room.*
import com.coral.fithub.data.model.Rutina

@Dao
interface RutinaDao {
    @Insert
    suspend fun insert(rutina: Rutina)

    @Update
    suspend fun update(rutina: Rutina)

    @Delete
    suspend fun delete(rutina: Rutina)

    @Query("SELECT * FROM rutina WHERE idRutina = :id")
    suspend fun get(id: Int): Rutina

    @Query("SELECT * FROM rutina")
    suspend fun getAll(): List<Rutina>
}