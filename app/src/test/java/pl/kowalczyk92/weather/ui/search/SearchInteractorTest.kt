package pl.kowalczyk92.weather.ui.search

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import pl.kowalczyk92.weather.data.database.AppDatabase
import pl.kowalczyk92.weather.data.database.CitiesDao
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.data.network.WeatherService

class SearchInteractorTest {

    val citiesDao: CitiesDao = mock()
    val appDatabase: AppDatabase = mock()
    val service: WeatherService = mock()
    val interactor = SearchInteractor(service, appDatabase)

    @Before
    fun setUp() {
        whenever(appDatabase.citiesDao()).thenReturn(citiesDao)
    }

    @Test
    fun `loadSearchedCities loads cities from database`() {
        interactor.loadSearchedCities()
        verify(citiesDao).getAll()
    }

    @Test
    fun `saveSearchedCity saves city in database`() {
        val city = City(name = "a")
        interactor.saveSearchedCity("a")
        verify(citiesDao).insert(city)
    }

    @Test
    fun `searchWeather loads weather forecast for proper city`() {
        val cityName = "a"
        interactor.searchWeather(cityName)
        verify(service).getWeatherForecast(cityName)
    }
}