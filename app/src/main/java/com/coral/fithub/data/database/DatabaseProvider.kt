package com.coral.fithub.data.database

import AppDatabase
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.coral.fithub.data.dao.EjercicioDao
import com.coral.fithub.data.dao.MusculoDao
import com.coral.fithub.data.dao.MusculoEjercicioDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "fithub_database"
            )
                .addCallback(DatabaseCallback())
                .build()
            INSTANCE = instance
            instance
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.ejercicioDao(), database.musculoDao(), database.musculoEjercicioDao())
                }
            }
        }

        suspend fun populateDatabase(ejercicioDao: EjercicioDao, musculoDao: MusculoDao, musculoEjercicioDao: MusculoEjercicioDao) {
            // Insertar datos iniciales
            PrepopulateData.ejercicios.forEach { ejercicio ->
                ejercicioDao.insert(ejercicio)
            }

            PrepopulateData.musculos.forEach { musculo ->
                musculoDao.insert(musculo)
            }

            PrepopulateData.musculoEjercicio.forEach { musculoEjercicio ->
                musculoEjercicioDao.insert(musculoEjercicio)
            }
        }
    }
}