package pl.kowalczyk92.weather.ui.search

import io.reactivex.Observable
import io.reactivex.Single
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.BaseView

interface SearchContract {
    interface View : BaseView {
        val searchEvents: Observable<String>
        fun showSearchedCities(cities: List<City>)
        fun showLoading()
        fun hideLoading()
    }

    interface Interactor {
        fun searchWeather(cityName: String): Single<WeatherForecast>
        fun saveSearchedCity(cityName: String)
        fun loadSearchedCities(): Single<List<City>>
    }

    interface Routing {
        fun startDetailsFragment(forecast: WeatherForecast)
    }
}