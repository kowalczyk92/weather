package pl.kowalczyk92.weather.ui.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_details.details_name
import kotlinx.android.synthetic.main.fragment_details.details_pressure
import kotlinx.android.synthetic.main.fragment_details.details_temperature
import kotlinx.android.synthetic.main.fragment_details.details_wind_speed
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.BaseFragment
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.ui.search.FORECAST_TAG
import javax.inject.Inject

@SuppressLint("SetTextI18n")
open class DetailsFragment : BaseFragment(), DetailsContract.View {

    @Inject
    lateinit var presenter: BasePresenter<DetailsContract.View>

    private val forecastEventsSubject = PublishSubject.create<WeatherForecast>()
    override val forecastEvent: Observable<WeatherForecast>
        get() = forecastEventsSubject

    override val layout = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        getWeatherForecast()?.let {
            forecastEventsSubject.onNext(it)
        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showCityName(name: String) {
        details_name.text = name
    }

    override fun showTemperature(degree: Int, color: Int) {
        details_temperature.text = "${getString(R.string.details_teperature)} $degree"
        details_temperature.setTextColor(color)
    }

    override fun showPressure(pressure: Int) {
        details_pressure.text = "${getString(R.string.details_pressure)} $pressure"
    }

    override fun showWindSpeed(speed: Int) {
        details_wind_speed.text = "${getString(R.string.details_wind_speed)} $speed"
    }

    private fun getWeatherForecast() = arguments?.getParcelable<WeatherForecast>(FORECAST_TAG)
}