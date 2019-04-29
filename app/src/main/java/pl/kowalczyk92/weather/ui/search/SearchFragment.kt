package pl.kowalczyk92.weather.ui.search

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_search.*
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City
import pl.kowalczyk92.weather.ui.base.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchContract.View {

    @Inject
    lateinit var presenter: SearchPresenter

    override val layout = R.layout.fragment_search
    override val searchEvents: Observable<String>
        get() = confirm_button
            .clicks()
            .map { search_edit_text.text.toString() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun showSearchedCities(cities: List<City>) {
        cities.forEach {
            print(it.toString())
        }
    }

    override fun showLoading() {
        loading_view?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading_view?.visibility = View.GONE
    }
}
