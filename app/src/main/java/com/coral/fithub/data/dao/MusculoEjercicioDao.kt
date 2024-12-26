package com.coral.fithub.data.dao

import androidx.room.*
import com.coral.fithub.data.model.MusculoEjercicio

@Dao
interface MusculoEjercicioDao {
    @Insert
    suspend fun insert(musculoEjercicio: MusculoEjercicio)

    @Update
    suspend fun update(musculoEjercicio: MusculoEjercicio)

    @Delete
    suspend fun delete(musculoEjercicio: MusculoEjercicio)

    @Query("SELECT * FROM musculoEjercicio WHERE idMusculo = :id")
    suspend fun getbyIdMusculo(id: Int): List<MusculoEjercicio>

    @Query("SELECT * FROM musculoEjercicio WHERE idEjercicio = :id")
    suspend fun getbyIdEjercicio(id: Int): List<MusculoEjercicio>
}