package pl.kowalczyk92.weather.ui.search

import io.reactivex.Single
import pl.kowalczyk92.weather.data.database.AppDatabase
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.data.network.WeatherService
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val weatherService: WeatherService,
    private val appDatabase: AppDatabase
) :
    SearchContract.Interactor {

    override fun searchWeather(cityName: String): Single<WeatherForecast> =
        weatherService.getWeatherForecast(cityName)

    override fun saveSearchedCity(cityName: String) {
        appDatabase.citiesDao().insert(City(name = cityName))
    }

    override fun loadSearchedCities(): Single<List<City>> = appDatabase.citiesDao().getAll()
}