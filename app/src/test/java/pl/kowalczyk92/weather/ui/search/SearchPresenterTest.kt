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
import pl.kowalczyk92.weather.data.entity.WeatherForecast
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
    fun `search event invokes weather search for proper city`() {
        val city = "a"
        searchEventSubject.onNext(city)
        verify(interactor).searchWeather(city)
    }

    @Test
    fun `search event shows default error message when error occurs`() {
        searchEventSubject.onError(Throwable())
        verify(view).showMessage(R.string.error_default)
    }

    @Test
    fun `search event shows loading view before weather search`() {
        val city = "a"
        searchEventSubject.onNext(city)
        inOrder(view, interactor) {
            verify(view).showLoading()
            verify(interactor).searchWeather(city)
        }
    }

    @Test
    fun `search event hides loading view after weather search`() {
        val city = "a"
        searchEventSubject.onNext(city)
        inOrder(view, interactor) {
            verify(interactor).searchWeather(city)
            verify(view).hideLoading()
        }
    }

    @Test
    fun `search event runs details fragment when weather search was succeed`() {
        verify(routing).startDetailsFragment()
    }

    @Test
    fun `attachView loads recently searched cities`() {
        verify(interactor).loadSearchedCities()
    }

    @Test
    fun `attachView shows recently searched cities`() {
        val cities = listOf("a", "b", "c")
        whenever(interactor.loadSearchedCities()).thenReturn(Single.just(cities))
        verify(view).showSearchedCities(cities)
    }

    @Test
    fun `attachView shows default error message when error occurs during loading searched cities`() {
        whenever(interactor.loadSearchedCities()).thenReturn(Single.error(Throwable()))
        presenter.attachView(view)
        verify(view).showMessage(R.string.error_default)
    }

    private fun createWeatherForecast() = WeatherForecast("Warszawa")

    private fun createSearchedCities() = listOf("a", "b", "c")
}