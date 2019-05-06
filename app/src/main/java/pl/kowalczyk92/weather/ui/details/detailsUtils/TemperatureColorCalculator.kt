package pl.kowalczyk92.weather.ui.details.detailsUtils

import android.graphics.Color
import javax.inject.Inject

class TemperatureColorCalculator @Inject constructor() {

    fun getColor(temp: Double) =
        when {
            temp < 10 -> Color.BLUE
            temp > 20 -> Color.RED
            else -> Color.BLACK
        }
}