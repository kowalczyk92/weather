package pl.kowalczyk92.weather.ui.details.detailsUtils

class FarenheitConverter {

    fun getCelsius(tempInFarenheit: Double) = (tempInFarenheit - 32) / 1.8
}