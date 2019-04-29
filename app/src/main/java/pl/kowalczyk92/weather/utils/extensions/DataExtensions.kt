package pl.kowalczyk92.weather.utils.extensions

import android.os.Bundle
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.search.FORECAST_TAG

fun WeatherForecast.toBundle() =
    Bundle().apply { putParcelable(FORECAST_TAG, this@toBundle) }