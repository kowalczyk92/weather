package pl.kowalczyk92.weather.ui.search.cityList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import io.reactivex.subjects.Subject
import pl.kowalczyk92.weather.R
import pl.kowalczyk92.weather.data.database.City

class CityListAdapter(
    private val searchEvents: Subject<String>,
    private val deleteCityEvents: Subject<City>,
    private val layoutInflater: LayoutInflater
) : ListAdapter<City, CityViewHolder>(CityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityViewHolder(
            layoutInflater.inflate(R.layout.row_city, parent, false),
            searchEvents,
            deleteCityEvents
        )

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}