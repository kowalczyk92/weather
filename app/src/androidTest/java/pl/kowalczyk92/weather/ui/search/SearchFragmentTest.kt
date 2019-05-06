package pl.kowalczyk92.weather.ui.search

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.fragment_search.confirm_button
import kotlinx.android.synthetic.main.fragment_search.loading_view
import kotlinx.android.synthetic.main.fragment_search.recycler_view
import kotlinx.android.synthetic.main.fragment_search.search_edit_text
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.ui.search.cityList.CityListAdapter

class SearchFragmentTest {

    lateinit var scenario: FragmentScenario<TestSearchFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer<TestSearchFragment>()
    }

    @Test
    fun layout_id_is_equals_fragment_search() {
        scenario.onFragment {
            assertEquals(pl.kowalczyk92.weather.R.layout.fragment_search, it.layout)
        }
    }

    @Test
    fun showLoading_shows_loading_view() {
        scenario.onFragment {
            assertFalse(it.loading_view.isVisible)
            it.showLoading()
            assertTrue(it.loading_view.isVisible)
        }
    }

    @Test
    fun hideLoading_hides_loading_view() {
        scenario.onFragment {
            it.loading_view.visibility = View.VISIBLE
            assertTrue(it.loading_view.isVisible)
            it.hideLoading()
            assertFalse(it.loading_view.isVisible)
        }
    }

    @Test
    fun onViewCreated_sets_recyclerView_adapter() {
        scenario.onFragment {
            assertEquals(it.adapter, it.recycler_view.adapter)
        }
    }

    @Test
    fun onViewCreated_sets_recyclerView_linearLayoutManager() {
        scenario.onFragment {
            assertTrue(it.recycler_view.layoutManager is LinearLayoutManager)
        }
    }

    @Test
    fun onViewCreated_attaches_presenter() {
        val presenter: SearchPresenter = mock()
        scenario.onFragment {
            it.presenter = presenter
            it.onViewCreated(View(it.context), Bundle())
            verify(presenter).attachView(it)
        }
    }

    @Test
    fun onDestroyView_detaches_presenter() {
        val presenter: SearchPresenter = mock()
        scenario.onFragment {
            it.presenter = presenter
        }
        scenario.moveToState(Lifecycle.State.DESTROYED)
        verify(presenter).detachView()
    }

    @Test
    fun showSearchedCities_sets_adapter_list() {
        val adapter: CityListAdapter = mock()
        val cities = listOf(City(1, "a"))
        scenario.onFragment {
            it.adapter = adapter
            it.showSearchedCities(cities)
        }
        verify(adapter).submitList(cities)
    }

    @Test
    fun confirmButton_click_emits_event_with_editText_value() {
        val cityName = "a"
        scenario.onFragment {
            it.search_edit_text.text = Editable.Factory.getInstance().newEditable(cityName)
            val testObserver = it.searchEvents.test()
            it.confirm_button.performClick()
            testObserver.assertNoErrors()
            testObserver.assertValue(cityName)
        }
    }

    @Test
    fun confirmButton_click_does_not_emit_value_when_editText_is_empty() {
        scenario.onFragment {
            val testObserver = it.searchEvents.test()
            it.confirm_button.performClick()
            testObserver.assertNoErrors()
            testObserver.assertNoValues()
        }
    }
}
