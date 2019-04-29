package pl.kowalczyk92.weather.ui.search

import androidx.navigation.fragment.NavHostFragment
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.utils.extensions.toBundle
import javax.inject.Inject

const val FORECAST_TAG = "forecast_tag"

class SearchRouting @Inject constructor(val fragment: SearchFragment) : SearchContract.Routing {

    override fun startDetailsFragment(forecast: WeatherForecast) {
        NavHostFragment.findNavController(fragment)
            .navigate(R.id.detailsFragment, forecast.toBundle())
    }
}