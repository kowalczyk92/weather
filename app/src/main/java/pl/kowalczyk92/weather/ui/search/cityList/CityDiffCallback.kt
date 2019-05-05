package pl.kowalczyk92.weather.ui.search.cityList

import androidx.recyclerview.widget.DiffUtil
import pl.kowalczyk92.weather.data.database.City

class CityDiffCallback : DiffUtil.ItemCallback<City>() {

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }
}