package pl.kowalczyk92.weather.ui.details.detailsUtils

import android.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureColorCalculatorTest {

    val temperatureColorCalculator = TemperatureColorCalculator()

    @Test
    fun `getColor returns red when temperature is higher than 20`() {
        assertEquals(Color.RED, temperatureColorCalculator.getColor(20.1))
    }

    @Test
    fun `getColor returns black when temperature is lower or equals 20 and higher or equals 10`() {
        assertEquals(Color.BLACK, temperatureColorCalculator.getColor(20.0))
        assertEquals(Color.BLACK, temperatureColorCalculator.getColor(15.0))
        assertEquals(Color.BLACK, temperatureColorCalculator.getColor(10.0))
    }

    @Test
    fun `getColor returns blue when temperature is lower than 10`() {
        assertEquals(Color.BLUE, temperatureColorCalculator.getColor(9.9))
    }
}