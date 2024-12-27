import androidx.room.Database
import androidx.room.RoomDatabase
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.model.Entrenamiento
import com.coral.fithub.data.model.Musculo
import com.coral.fithub.data.model.MusculoEjercicio
import com.coral.fithub.data.dao.EjercicioDao
import com.coral.fithub.data.dao.EntrenamientoDao
import com.coral.fithub.data.dao.MusculoDao
import com.coral.fithub.data.dao.MusculoEjercicioDao
import com.coral.fithub.data.dao.RutinaDao
import com.coral.fithub.data.dao.RutinaEjercicioDao
import com.coral.fithub.data.dao.SerieDao
import com.coral.fithub.data.model.Rutina
import com.coral.fithub.data.model.RutinaEjercicio
import com.coral.fithub.data.model.Serie

@Database(entities = [Ejercicio::class, Entrenamiento::class, Musculo::class, MusculoEjercicio::class, Rutina::class, RutinaEjercicio::class, Serie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun entrenamientoDao(): EntrenamientoDao
    abstract fun musculoDao(): MusculoDao
    abstract fun musculoEjercicioDao(): MusculoEjercicioDao
    abstract fun rutinaDao(): RutinaDao
    abstract fun rutinaEjercicioDao(): RutinaEjercicioDao
    abstract fun serieDao(): SerieDao

}