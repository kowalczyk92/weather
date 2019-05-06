package pl.kowalczyk92.weather.ui.details

import io.reactivex.rxkotlin.subscribeBy
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.entity.WeatherForecast
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.ui.details.detailsUtils.TemperatureColorCalculator
import pl.kowalczyk92.weather.utils.schedulers.SchedulersProvider
import javax.inject.Inject

open class DetailsPresenter @Inject constructor(
    private val farenheitColorCalculator: TemperatureColorCalculator,
    private val schedulersProvider: SchedulersProvider
) :
    BasePresenter<DetailsContract.View>() {

    override fun attachView(view: DetailsContract.View) {
        super.attachView(view)

        addSubscription(
            view.forecastEvent
                .observeOn(schedulersProvider.ui())
                .subscribeBy(
                    onNext = { setForecastView(it) },
                    onError = { view.showMessage(R.string.error_default) }
                )
        )
    }

    private fun setForecastView(forecast: WeatherForecast) {
        view?.let {
            it.showCityName(forecast.name)
            it.showTemperature(
                forecast.main.temp,
                farenheitColorCalculator.getColor(forecast.main.temp)
            )
            it.showPressure(forecast.main.pressure)
            it.showWindSpeed(forecast.wind.speed)
        }
    }
}