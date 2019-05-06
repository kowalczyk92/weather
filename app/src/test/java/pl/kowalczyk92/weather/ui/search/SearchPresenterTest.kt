package pl.kowalczyk92.weather.ui.search

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.data.entity.Clouds
import pl.kowalczyk92.weather.data.entity.Coord
import pl.kowalczyk92.weather.data.entity.Main
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.data.entity.Wind
import pl.kowalczyk92.weather.utils.schedulers.TestSchedulersProvider

class SearchPresenterTest {

    val view: SearchContract.View = mock()
    val interactor: SearchContract.Interactor = mock()
    val routing: SearchContract.Routing = mock()
    val presenter = SearchPresenter(interactor, routing, TestSchedulersProvider())

    val searchEventSubject = PublishSubject.create<String>()

    @Before
    fun setUp() {
        whenever(view.searchEvents).thenReturn(searchEventSubject)
        whenever(interactor.searchWeather(any())).thenReturn(Single.just(createWeatherForecast()))
        whenever(interactor.loadSearchedCities()).thenReturn(Single.just(createSearchedCities()))
        presenter.attachView(view)
    }

    @Test
    fun `search event invokes weather search for proper city name`() {
        val cityName = "a"
        searchEventSubject.onNext(cityName)
        verify(interactor).searchWeather(cityName)
    }

    @Test
    fun `search event shows default error message when error occurs`() {
        searchEventSubject.onError(Throwable())
        verify(view).showMessage(R.string.error_default)
    }

    @Test
    fun `search event shows loading view before weather search`() {
        val cityName = "a"
        searchEventSubject.onNext(cityName)
        inOrder(view, interactor) {
            verify(view).showLoading()
            verify(interactor).searchWeather(cityName)
        }
    }

    @Test
    fun `search event hides loading view after weather search`() {
        val cityName = "a"
        searchEventSubject.onNext(cityName)
        inOrder(view, interactor) {
            verify(interactor).searchWeather(cityName)
            verify(view).hideLoading()
        }
    }

    @Test
    fun `search event runs details fragment when weather search was succeed`() {
        searchEventSubject.onNext("a")
        verify(routing).startDetailsFragment(createWeatherForecast())
    }

    @Test
    fun `attachView loads recently searched cities`() {
        verify(interactor).loadSearchedCities()
    }

    @Test
    fun `attachView shows recently searched cities`() {
        val cities = createSearchedCities()
        whenever(interactor.loadSearchedCities()).thenReturn(Single.just(cities))
        verify(view).showSearchedCities(cities)
    }

    @Test
    fun `attachView shows default error message when error occurs during loading searched cities`() {
        whenever(interactor.loadSearchedCities()).thenReturn(Single.error(Throwable()))
        presenter.attachView(view)
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

    private fun createSearchedCities() =
        listOf(City(name = "a"), City(name = "b"), City(name = "c"))
}