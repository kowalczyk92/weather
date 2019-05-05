package pl.kowalczyk92.weather.ui.search

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import javax.inject.Provider

class TestSearchFragment : SearchFragment() {

    override fun inject() {
        linearLayoutManager =
            Provider { LinearLayoutManager(activity, RecyclerView.VERTICAL, false) }
        presenter = object : BasePresenter<SearchContract.View>() {}
    }
}