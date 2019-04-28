package pl.kowalczyk92.weather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(City::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
}