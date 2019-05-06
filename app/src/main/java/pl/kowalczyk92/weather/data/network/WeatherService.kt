package pl.kowalczyk92.weather.data.network

import io.reactivex.Single
import pl.kowalczyk92.weather.BuildConfig
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("data/2.5/weather")
    fun getWeatherForecast(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String = BuildConfig.APPID
    ): Single<WeatherForecast>
}