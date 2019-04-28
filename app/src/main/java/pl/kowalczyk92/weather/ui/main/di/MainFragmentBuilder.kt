package pl.kowalczyk92.weather.ui.main.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kowalczyk92.weather.ui.details.DetailsFragment
import pl.kowalczyk92.weather.ui.search.SearchFragment
import pl.kowalczyk92.weather.ui.search.di.SearchModule

@Module
abstract class MainFragmentBuilder {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [])
    abstract fun bindDetailsFragment(): DetailsFragment
}