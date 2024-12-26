package com.coral.fithub.data

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.*
import androidx.room.TypeConverters
import com.coral.fithub.data.*
import com.coral.fithub.data.model.Musculo

@Database(entities = [Musculo::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musculoDao(): MusculoDao
}
