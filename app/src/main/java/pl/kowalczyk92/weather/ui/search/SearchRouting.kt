package pl.kowalczyk92.weather.ui.search

import pl.kowalczyk92.weather.data.entity.WeatherForecast
import javax.inject.Inject

class SearchRouting @Inject constructor() : SearchContract.Routing {

    override fun startDetailsFragment(forecast: WeatherForecast) {
        Unit
    }
}