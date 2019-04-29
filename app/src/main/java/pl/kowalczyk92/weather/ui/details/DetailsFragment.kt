package pl.kowalczyk92.weather.ui.details

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_details.*
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.BaseFragment
import pl.kowalczyk92.weather.ui.search.FORECAST_TAG

class DetailsFragment : BaseFragment() {

    override val layout = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        details_text_view.text = arguments?.getParcelable<WeatherForecast>(FORECAST_TAG).toString()
    }
}