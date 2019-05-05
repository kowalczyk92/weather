package pl.kowalczyk92.weather.ui.details

import io.reactivex.Observable
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.BaseView

interface DetailsContract {
    interface View : BaseView {
        val forecastEvent: Observable<WeatherForecast>
        fun showCityName(name: String)
        fun showTemperature(degree: Int, color: Int)
        fun showPressure(pressure: Int)
        fun showWindSpeed(speed: Int)
    }
}