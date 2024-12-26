package com.coral.fithub.data.dao

import androidx.room.*
import com.coral.fithub.data.model.Ejercicio

@Dao
interface EjercicioDao {

    @Insert
    suspend fun insert(ejercicio: Ejercicio)

    @Update
    suspend fun update(ejercicio: Ejercicio)

    @Delete
    suspend fun delete(ejercicio: Ejercicio)

    @Query("SELECT * FROM ejercicio WHERE id = :id")
    suspend fun get(id: Int): Ejercicio

    @Query("SELECT * FROM ejercicio")
    suspend fun getAll(): List<Ejercicio>
}