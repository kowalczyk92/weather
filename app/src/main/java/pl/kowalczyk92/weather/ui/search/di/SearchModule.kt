package pl.kowalczyk92.weather.ui.search.di

import dagger.Module
import dagger.Binds
import pl.kowalczyk92.weather.ui.search.SearchContract
import pl.kowalczyk92.weather.ui.search.SearchInteractor
import pl.kowalczyk92.weather.ui.search.SearchRouting

@Module
abstract class SearchModule {

    @Binds
    abstract fun bindSearchInteractor(searchInteractor: SearchInteractor): SearchContract.Interactor

    @Binds
    abstract fun bindSesrchRouting(searchRouting: SearchRouting): SearchContract.Routing
}