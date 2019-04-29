package pl.kowalczyk92.weather.utils.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.kowalczyk92.weather.data.entity.Clouds
import pl.kowalczyk92.weather.data.entity.Coord
import pl.kowalczyk92.weather.data.entity.Main
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.data.entity.Wind
import pl.kowalczyk92.weather.ui.search.FORECAST_TAG

class DataExtensionsKtTest {

    @Test
    fun toBundle_creates_bundle_with_weather_forescast_object_using_FORECAST_TAG() {
        val forecast = createWeatherForecast()
        val bundle = forecast.toBundle()
        assertEquals(forecast, bundle.getParcelable<WeatherForecast>(FORECAST_TAG))
    }

    private fun createWeatherForecast() = WeatherForecast(
        base = "a",
        clouds = Clouds(1),
        cod = 2,
        coord = Coord(lat = 1.1, lon = 2.2),
        dt = 3,
        id = 4,
        main = createMain(),
        name = "city",
        visibility = 5,
        wind = Wind(deg = 111, speed = 222.222)
    )

    private fun createMain() = Main(
        humidity = 10,
        pressure = 11,
        temp = 30.5,
        temp_max = 31.0,
        temp_min = 29.0
    )
}