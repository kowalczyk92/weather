package pl.kowalczyk92.weather.ui.search.cityList

import android.view.LayoutInflater
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.row_city.view.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.ui.main.MainActivity

@RunWith(RobolectricTestRunner::class)
class CityViewHolderTest {

    lateinit var viewHolder: CityViewHolder
    val searchEvents = PublishSubject.create<String>()
    val deleteCityEvents = PublishSubject.create<City>()
    val city = City(id = 1, name = "a")

    @Before
    fun setup() {
        val activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        val view = LayoutInflater.from(activity).inflate(R.layout.row_city, null)
        viewHolder = CityViewHolder(view, searchEvents, deleteCityEvents)
    }

    @Test
    fun `bind sets city name`() {
        assertTrue(viewHolder.itemView.city_name.text.isEmpty())
        viewHolder.bind(city)
        assertEquals("a", viewHolder.itemView.city_name.text)
    }

    @Test
    fun `view click emits event with city name`() {
        val testObserver = searchEvents.test()
        viewHolder.bind(city)
        viewHolder.itemView.performClick()
        testObserver.assertNoErrors()
        testObserver.assertValue("a")
    }

    @Test
    fun `delete button click emits event with city`() {
        val testObserver = deleteCityEvents.test()
        viewHolder.bind(city)
        viewHolder.itemView.delete_city_button.performClick()
        testObserver.assertNoErrors()
        testObserver.assertValue(city)
    }
}