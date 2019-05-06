package pl.kowalczyk92.weather.ui.details

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.Clouds
import pl.kowalczyk92.weather.data.entity.Coord
import pl.kowalczyk92.weather.data.entity.Main
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.data.entity.Wind
import pl.kowalczyk92.weather.ui.details.detailsUtils.TemperatureColorCalculator
import pl.kowalczyk92.weather.utils.schedulers.TestSchedulersProvider

class DetailsPresenterTest {

    val view: DetailsContract.View = mock()
    val colorCalculator: TemperatureColorCalculator = mock()
    val presenter = DetailsPresenter(colorCalculator, TestSchedulersProvider())
    val forecastEventSubject = PublishSubject.create<WeatherForecast>()

    @Before
    fun setUp() {
        whenever(view.forecastEvent).thenReturn(forecastEventSubject)
        presenter.attachView(view)
    }

    @Test
    fun `forecast event shows city name`() {
        forecastEventSubject.onNext(createWeatherForecast())
        verify(view).showCityName("city")
    }

    @Test
    fun `forecast event shows temperature`() {
        forecastEventSubject.onNext(createWeatherForecast())
        verify(view).showTemperature(30.5, colorCalculator.getColor(30.5))
    }

    @Test
    fun `forecast event shows pressure`() {
        forecastEventSubject.onNext(createWeatherForecast())
        verify(view).showPressure(11.0)
    }

    @Test
    fun `forecast event shows wind speed`() {
        forecastEventSubject.onNext(createWeatherForecast())
        verify(view).showWindSpeed(222.222)
    }

    @Test
    fun `forecast event shows default error message when error occurs`() {
        forecastEventSubject.onError(Throwable())
        verify(view).showMessage(R.string.error_default)
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
        pressure = 11.0,
        temp = 30.5,
        temp_max = 31.0,
        temp_min = 29.0
    )
}