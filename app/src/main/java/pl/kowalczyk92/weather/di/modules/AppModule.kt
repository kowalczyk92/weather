package pl.kowalczyk92.weather.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import pl.kowalczyk92.weather.WeatherApp
import pl.kowalczyk92.weather.data.database.AppDatabase
import pl.kowalczyk92.weather.di.AppScope

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideAppDatabase(application: WeatherApp) =
        Room.databaseBuilder(application, AppDatabase::class.java, "appDatabase.db")
            .build()
}