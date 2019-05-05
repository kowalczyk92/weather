package pl.kowalczyk92.weather.ui.search.di

import dagger.Binds
import dagger.Module
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.ui.search.SearchContract
import pl.kowalczyk92.weather.ui.search.SearchInteractor
import pl.kowalczyk92.weather.ui.search.SearchPresenter
import pl.kowalczyk92.weather.ui.search.SearchRouting

@Module
abstract class SearchBindsModule {

    @Binds
    abstract fun bindSearchPresenter(searchPresenter: SearchPresenter): BasePresenter<SearchContract.View>

    @Binds
    abstract fun bindSearchInteractor(searchInteractor: SearchInteractor): SearchContract.Interactor

    @Binds
    abstract fun bindSesrchRouting(searchRouting: SearchRouting): SearchContract.Routing
}