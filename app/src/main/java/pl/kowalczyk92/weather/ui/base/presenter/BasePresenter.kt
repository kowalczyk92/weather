package pl.kowalczyk92.weather.ui.base.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import pl.kowalczyk92.weather.ui.base.BaseView

abstract class BasePresenter<V : BaseView> : Presenter<V> {

    protected var view: V? = null
    protected var compositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
        compositeDisposable.clear()
    }

    protected fun addSubscription(disposable: Disposable) = compositeDisposable.add(disposable)
}