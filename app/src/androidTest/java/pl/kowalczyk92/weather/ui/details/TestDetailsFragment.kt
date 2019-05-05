package pl.kowalczyk92.weather.ui.details

import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter

class TestDetailsFragment : DetailsFragment() {

    override fun inject() {
        presenter = object : BasePresenter<DetailsContract.View>() {}
    }
}