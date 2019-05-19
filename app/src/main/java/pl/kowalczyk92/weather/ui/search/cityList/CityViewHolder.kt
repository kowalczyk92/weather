package pl.kowalczyk92.weather.ui.search.cityList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.row_city.view.city_name
import kotlinx.android.synthetic.main.row_city.view.delete_city_button
import pl.kowalczyk92.weather.data.database.City

class CityViewHolder(
    itemView: View,
    private val searchEvents: Subject<String>,
    private val deleteCityEvents: Subject<City>
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: City) = with(itemView) {
        city_name.text = item.name
        clicks().map { item.name }.subscribe(searchEvents)
        delete_city_button.clicks().map { item }.subscribe(deleteCityEvents)
    }
}