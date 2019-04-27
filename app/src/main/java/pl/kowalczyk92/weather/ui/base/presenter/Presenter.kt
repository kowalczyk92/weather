package pl.kowalczyk92.weather.ui.base.presenter

import pl.kowalczyk92.weather.ui.base.BaseView

interface Presenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}