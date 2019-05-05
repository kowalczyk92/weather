package pl.kowalczyk92.weather.ui.details.detailsUtils

import org.junit.Assert.assertEquals
import org.junit.Test

class FarenheitConverterTest {

    val converter = FarenheitConverter()

    @Test
    fun `getCelsius returns temperature in celsius degree`() {
        assertEquals(-40.0, converter.getCelsius(tempInFarenheit = -40.0), 0.01)
        assertEquals(37.0, converter.getCelsius(tempInFarenheit = 98.6), 0.01)
        assertEquals(0.0, converter.getCelsius(tempInFarenheit = 32.0), 0.01)
    }
}