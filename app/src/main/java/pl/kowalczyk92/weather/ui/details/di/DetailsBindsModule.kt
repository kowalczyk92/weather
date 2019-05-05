package pl.kowalczyk92.weather.ui.details.di

import dagger.Binds
import dagger.Module
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.ui.details.DetailsContract
import pl.kowalczyk92.weather.ui.details.DetailsPresenter

@Module
abstract class DetailsBindsModule {

    @Binds
    abstract fun bindDetailsPresenter(detailsPresenter: DetailsPresenter): BasePresenter<DetailsContract.View>
}