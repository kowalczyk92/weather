package pl.kowalczyk92.weather.ui.search

import io.reactivex.rxkotlin.subscribeBy
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.utils.schedulers.SchedulersProvider
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val interactor: SearchContract.Interactor,
    private val routing: SearchContract.Routing,
    private val schedulersProvider: SchedulersProvider
) :
    BasePresenter<SearchContract.View>() {

    override fun attachView(view: SearchContract.View) {
        super.attachView(view)

        addSubscription(
            view.searchEvents
                .doOnNext { view.showLoading() }
                .observeOn(schedulersProvider.io())
                .flatMapSingle { cityName -> interactor.searchWeather(cityName) }
                .doOnNext { forecast -> interactor.saveSearchedCity(forecast.name) }
                .observeOn(schedulersProvider.ui())
                .subscribeBy(
                    onNext = {
                        routing.startDetailsFragment(it)
                        view.hideLoading()
                    },
                    onError = {
                        view.hideLoading()
                        view.showMessage(R.string.error_default)
                        it.printStackTrace()
                    }
                )
        )

        addSubscription(
            interactor.loadSearchedCities()
                .subscribeOn(schedulersProvider.io())
                .observeOn(schedulersProvider.ui())
                .subscribeBy(
                    onSuccess = {
                        view.showSearchedCities(it)
                    },
                    onError = { view.showMessage(R.string.error_default) }
                )
        )
    }
}