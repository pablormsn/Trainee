package com.coral.fithub.data.dao

import androidx.annotation.DeprecatedSinceApi
import androidx.room.*
import com.coral.fithub.data.model.RutinaEjercicio

@Dao
interface RutinaEjercicioDao {
    @Insert
    suspend fun insert(rutinaEjercicio: RutinaEjercicio)

    @Update
    suspend fun update(rutinaEjercicio: RutinaEjercicio)

    @Delete
    suspend fun delete(rutinaEjercicio: RutinaEjercicio)

    @Query("SELECT * FROM rutinaEjercicio WHERE idRutina = :id")
    suspend fun getbyIdRutina(id: Int): List<RutinaEjercicio>

    @Query("SELECT * FROM rutinaEjercicio WHERE idEjercicio = :id")
    suspend fun getbyIdEjercicio(id: Int): List<RutinaEjercicio>

    @Query("DELETE FROM rutinaEjercicio WHERE idRutina = :id")
    suspend fun deleteByRutina(id: Int)
}