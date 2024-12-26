package com.coral.fithub.data;

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.coral.fithub.R
import com.coral.fithub.data.model.Rutina
import java.io.BufferedReader
import java.io.InputStreamReader

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Trainee.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {
            // Lee el archivo schema.sql desde res/raw
            val inputStream = context.resources.openRawResource(R.raw.schema)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            reader.close()

            // Divide y ejecuta cada sentencia SQL separada por ;
            val sqlStatements = stringBuilder.toString().split(";")
            for (sql in sqlStatements) {
                if (sql.trim().isNotEmpty()) {
                    db.execSQL(sql.trim())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Puedes definir el comportamiento de actualización aquí
        db.execSQL("DROP TABLE IF EXISTS Musculo")
        db.execSQL("DROP TABLE IF EXISTS Ejercicio")
        db.execSQL("DROP TABLE IF EXISTS Rutina")
        onCreate(db)
    }

    fun getAllRutinas(): List<Rutina>{
        val rutinas = mutableListOf<Rutina>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Rutina", null)

        if(cursor.moveToFirst()){
            do{
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("idRutina"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("Nombre"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Descripcion"))
                rutinas.add(Rutina(id, nombre, descripcion))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return rutinas
    }
}