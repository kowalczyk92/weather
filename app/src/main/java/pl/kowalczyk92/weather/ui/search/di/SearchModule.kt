package pl.kowalczyk92.weather.ui.search.di

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import pl.kowalczyk92.weather.ui.main.MainActivity

@Module
class SearchModule {

    @Provides
    fun providesLinearLayoutManager(activity: MainActivity) =
        LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
}