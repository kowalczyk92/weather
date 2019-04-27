package pl.kowalczyk92.weather.ui.details

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.kowalczyk92.weather.R

class DetailsFragmentTest {

    val fragment = DetailsFragment()

    @Test
    fun `layout id is equals fragment_details`() {
        assertEquals(R.layout.fragment_details, fragment.layout)
    }
}