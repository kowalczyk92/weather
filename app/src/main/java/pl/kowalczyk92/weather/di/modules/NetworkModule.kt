package pl.kowalczyk92.weather.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import pl.kowalczyk92.weather.BuildConfig
import pl.kowalczyk92.weather.data.network.WeatherService
import pl.kowalczyk92.weather.di.AppScope
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @AppScope
    @Provides
    fun provideService(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)

    @AppScope
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .baseUrl(BuildConfig.URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @AppScope
    @Provides
    fun provideGson() = GsonBuilder()
        .setLenient()
        .create()

    @AppScope
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()
}