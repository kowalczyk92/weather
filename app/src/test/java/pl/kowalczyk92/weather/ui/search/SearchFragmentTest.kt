package pl.kowalczyk92.weather.ui.search

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.kowalczyk92.weather.R

class SearchFragmentTest {

    val fragment = SearchFragment()

    @Test
    fun `layout id is equals fragment_search`() {
        assertEquals(R.layout.fragment_search, fragment.layout)
    }
}
