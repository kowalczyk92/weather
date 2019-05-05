package pl.kowalczyk92.weather.ui.search.cityList

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.robolectric.RobolectricTestRunner
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City

@RunWith(RobolectricTestRunner::class)
class CityListAdapterTest {

    val layoutInflater: LayoutInflater = mock()
    val listAdapter = CityListAdapter(mock(), mock(), layoutInflater)
    val viewHolder: CityViewHolder = mock()
    val viewGroup: ViewGroup = mock()

    @Before
    fun setUp() {
        whenever(layoutInflater.inflate(anyInt(), any(), any())).thenReturn(mock())
    }

    @Test
    fun `onCreateViewHolder creates view holder with city row layout`() {
        listAdapter.onCreateViewHolder(viewGroup, 0)
        verify(layoutInflater).inflate(R.layout.row_city, viewGroup, false)
    }

    @Test
    fun `onBindViewHolder binds view holder`() {
        val city = City(1, "a")
        listAdapter.submitList(listOf(city))
        listAdapter.onBindViewHolder(viewHolder, 0)
        verify(viewHolder).bind(city)
    }
}