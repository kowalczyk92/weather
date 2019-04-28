package pl.kowalczyk92.weather.ui.search

import io.reactivex.Observable
import io.reactivex.Single
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.BaseView

interface SearchContract {
    interface View : BaseView {
        val searchEvents: Observable<String>
        fun showSearchedCities(cities: List<String>)
        fun showLoading()
        fun hideLoading()
    }

    interface Interactor {
        fun searchWeather(city: String): Single<WeatherForecast>
        fun saveSearchedCity(city: String)
        fun loadSearchedCities(): Single<List<String>>
    }

    interface Routing {
        fun startDetailsFragment()
    }
}