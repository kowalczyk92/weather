package pl.kowalczyk92.weather.ui.search.cityList

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import pl.kowalczyk92.weather.data.database.City

class CityDiffCallbackTest {

    val diffCallback = CityDiffCallback()

    @Test
    fun `areItemsTheSame returns true when items have the same id`() {
        val city = City(1, "a")
        assertTrue(diffCallback.areItemsTheSame(city, city))
    }

    @Test
    fun `areItemsTheSame returns false when items have not the same id`() {
        val city = City(1, "a")
        assertFalse(diffCallback.areItemsTheSame(city.copy(2), city))
    }

    @Test
    fun `areContentsTheSame returns true when data objects are the same`() {
        val city = City(1, "a")
        assertTrue(diffCallback.areContentsTheSame(city, city))
    }

    @Test
    fun `areContentsTheSame returns false when data objects are not the same`() {
        val city = City(1, "a")
        assertFalse(diffCallback.areContentsTheSame(city.copy(name = "b"), city))
    }
}