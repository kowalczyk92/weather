package pl.kowalczyk92.weather.data.entity

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)