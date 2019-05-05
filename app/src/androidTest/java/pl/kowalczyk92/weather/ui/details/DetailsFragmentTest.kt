package pl.kowalczyk92.weather.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.fragment_details.details_name
import kotlinx.android.synthetic.main.fragment_details.details_pressure
import kotlinx.android.synthetic.main.fragment_details.details_temperature
import kotlinx.android.synthetic.main.fragment_details.details_wind_speed
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.Clouds
import pl.kowalczyk92.weather.data.entity.Coord
import pl.kowalczyk92.weather.data.entity.Main
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.data.entity.Wind
import pl.kowalczyk92.weather.utils.extensions.toBundle

class DetailsFragmentTest {

    lateinit var scenario: FragmentScenario<TestDetailsFragment>

    @Before
    fun setUp() {
        scenario =
            launchFragmentInContainer<TestDetailsFragment>(fragmentArgs = createWeatherForecast().toBundle())
    }

    @Test
    fun layout_id_is_equals_fragment_details() {
        scenario.onFragment {
            assertEquals(pl.kowalczyk92.weather.R.layout.fragment_details, it.layout)
        }
    }

    @Test
    fun onViewCreated_attaches_presenter() {
        val presenter: DetailsPresenter = mock()
        scenario.onFragment {
            it.presenter = presenter
            it.onViewCreated(View(it.context), Bundle())
            verify(presenter).attachView(it)
        }
    }

    @Test
    fun onDestroyView_detaches_presenter() {
        val presenter: DetailsPresenter = mock()
        scenario.onFragment {
            it.presenter = presenter
        }
        scenario.moveToState(Lifecycle.State.DESTROYED)
        verify(presenter).detachView()
    }

    @Test
    fun showCityName_sets_city_name() {
        val cityName = "a"
        scenario.onFragment {
            it.showCityName(cityName)
            assertEquals(cityName, it.details_name.text)
        }
    }

    @Test
    fun showTemperature_sets_temperature() {
        val temperature = 123
        scenario.onFragment {
            it.showTemperature(temperature, R.color.colorPrimary)
            assertEquals("Temperatura: 123", it.details_temperature.text)
        }
    }

    @Test
    fun showTemperature_sets_temperature_text_color() {
        scenario.onFragment {
            it.showTemperature(1, R.color.colorPrimary)
            assertEquals(R.color.colorPrimary, it.details_temperature.currentTextColor)
        }
    }

    @Test
    fun showPressure_sets_pressure() {
        val pressure = 123
        scenario.onFragment {
            it.showPressure(pressure)
            assertEquals("Ciśnienie: 123", it.details_pressure.text)
        }
    }

    @Test
    fun showWindSpeed_sets_wind_speed() {
        val wind = 123
        scenario.onFragment {
            it.showWindSpeed(wind)
            assertEquals("Prędkość wiatru: 123", it.details_wind_speed.text)
        }
    }

    @Test
    fun onViewCreated_emits_event_with_weatherForecast() {
        val forecast = createWeatherForecast()
        scenario.onFragment {
            val testObserver = it.forecastEvent.test()
            it.onViewCreated(View(it.activity), Bundle())
            testObserver.assertNoErrors()
            testObserver.assertValue(forecast)
        }
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
