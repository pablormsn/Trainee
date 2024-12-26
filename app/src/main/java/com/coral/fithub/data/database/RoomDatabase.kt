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

@Database(entities = [Ejercicio::class, Entrenamiento::class, Musculo::class, MusculoEjercicio::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ejercicioDao(): EjercicioDao
    abstract fun entrenamientoDao(): EntrenamientoDao
    abstract fun musculoDao(): MusculoDao
    abstract fun musculoEjercicioDao(): MusculoEjercicioDao
}