package com.coral.fithub.data.dao

import androidx.room.*
import com.coral.fithub.data.model.Musculo
@Dao
interface MusculoDao {
    @Insert
    suspend fun insert(musculo: Musculo)

    @Update
    suspend fun update(musculo: Musculo)

    @Delete
    suspend fun delete(musculo: Musculo)

    @Query("SELECT * FROM musculo WHERE id = :id")
    suspend fun get(id: Int): Musculo

    @Query("SELECT * FROM musculo")
    suspend fun getAll(): List<Musculo>
}