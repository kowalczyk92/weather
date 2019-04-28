package pl.kowalczyk92.weather.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CitiesDao {

    @Query("SELECT * from cities")
    fun getAll(): Single<List<City>>

    @Insert(onConflict = REPLACE)
    fun insert(city: City)

    @Delete
    fun delete(city: City): Completable
}