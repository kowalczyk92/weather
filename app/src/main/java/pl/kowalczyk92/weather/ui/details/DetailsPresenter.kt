package pl.kowalczyk92.weather.ui.details

import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.utils.schedulers.SchedulersProvider
import javax.inject.Inject

open class DetailsPresenter @Inject constructor(private val schedulersProvider: SchedulersProvider) :
    BasePresenter<DetailsContract.View>() {

    override fun attachView(view: DetailsContract.View) {
        super.attachView(view)
    }
}