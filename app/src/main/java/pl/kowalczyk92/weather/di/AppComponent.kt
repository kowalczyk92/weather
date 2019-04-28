package pl.kowalczyk92.weather.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.kowalczyk92.weather.WeatherApp
import pl.kowalczyk92.weather.di.modules.AppModule
import pl.kowalczyk92.weather.di.modules.NetworkModule
import pl.kowalczyk92.weather.di.modules.SchedulersModule

@AppScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        SchedulersModule::class,
        ActivityBuilder::class,
        AndroidSupportInjectionModule::class]
)
interface AppComponent : AndroidInjector<WeatherApp> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<WeatherApp>
}