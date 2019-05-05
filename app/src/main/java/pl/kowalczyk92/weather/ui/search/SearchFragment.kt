package pl.kowalczyk92.weather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search.*
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.ui.base.BaseFragment
import pl.kowalczyk92.weather.ui.base.presenter.BasePresenter
import pl.kowalczyk92.weather.ui.search.cityList.CityListAdapter
import javax.inject.Inject
import javax.inject.Provider

open class SearchFragment : BaseFragment(), SearchContract.View {

    @Inject
    lateinit var presenter: BasePresenter<SearchContract.View>
    @Inject
    lateinit var linearLayoutManager: Provider<LinearLayoutManager>
    lateinit var adapter: CityListAdapter
    private val searchEventsSubject = PublishSubject.create<String>()
    private val deleteCityEventsSubject = PublishSubject.create<City>()

    override val layout = R.layout.fragment_search
    override val searchEvents: Observable<String>
        get() = Observable.merge(searchEventsSubject,
            confirm_button.clicks().map { search_edit_text.text.toString() }
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showSearchedCities(cities: List<City>) {
        adapter.submitList(cities)
    }

    override fun showLoading() {
        loading_view?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view?.visibility = View.GONE
    }

    private fun setRecyclerView() {
        adapter = CityListAdapter(
            searchEventsSubject,
            deleteCityEventsSubject,
            LayoutInflater.from(context)
        )
        recycler_view.layoutManager = linearLayoutManager.get()
        recycler_view.adapter = adapter
    }
}
