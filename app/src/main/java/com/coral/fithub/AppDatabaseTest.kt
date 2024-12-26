import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.coral.fithub.data.model.Ejercicio
import com.coral.fithub.data.dao.EjercicioDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test



@SmallTest
class AppDatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var ejercicioDao: EjercicioDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        ejercicioDao = database.ejercicioDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetEjercicio() = runBlocking {
        val ejercicio = Ejercicio(1, 10302f, "Descripcion 1")
        ejercicioDao.insert(ejercicio)
        val allEjercicios = ejercicioDao.getAll()
        assertEquals(allEjercicios[0], ejercicio)
    }
}