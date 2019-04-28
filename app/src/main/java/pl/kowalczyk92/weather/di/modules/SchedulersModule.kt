package pl.kowalczyk92.weather.di.modules

import dagger.Binds
import dagger.Module
import pl.kowalczyk92.weather.utils.schedulers.AppSchedulersProvider
import pl.kowalczyk92.weather.utils.schedulers.SchedulersProvider

@Module
abstract class SchedulersModule {

    @Binds
    abstract fun bindSchedulersProvider(appSchedulersProvider: AppSchedulersProvider): SchedulersProvider
}