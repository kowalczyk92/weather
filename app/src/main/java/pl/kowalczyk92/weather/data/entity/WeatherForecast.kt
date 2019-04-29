package pl.kowalczyk92.weather.data.entity

data class WeatherForecast(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val visibility: Int,
    val wind: Wind
)