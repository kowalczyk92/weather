package pl.kowalczyk92.weather.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.kowalczyk92.weather.ui.main.MainActivity
import pl.kowalczyk92.weather.ui.main.di.MainActivityModule
import pl.kowalczyk92.weather.ui.main.di.MainFragmentBuilder

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MainFragmentBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}