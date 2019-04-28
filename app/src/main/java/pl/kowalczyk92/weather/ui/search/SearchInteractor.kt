package pl.kowalczyk92.weather.ui.search

import io.reactivex.Single
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import javax.inject.Inject

class SearchInteractor @Inject constructor() : SearchContract.Interactor {

    override fun searchWeather(city: String): Single<WeatherForecast> =
        Single.just(WeatherForecast(city))

    override fun saveSearchedCity(city: String) {
        Unit
    }

    override fun loadSearchedCities(): Single<List<String>> =
        Single.just(listOf("Warszawa", "Poznań", "Kraków"))
}