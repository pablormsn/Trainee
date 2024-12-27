package com.coral.fithub.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.coral.fithub.data.model.Serie

@Dao
interface SerieDao {
    @Insert
    suspend fun insert(serie: Serie)

    @Update
    suspend fun update(serie: Serie)

    @Delete
    suspend fun delete(serie: Serie)

    @Query("SELECT * FROM serie WHERE idSerie = :id")
    suspend fun get(id: Int): Serie

    @Query("SELECT * FROM serie")
    suspend fun getAll(): List<Serie>
}