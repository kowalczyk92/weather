package pl.kowalczyk92.weather.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Main(
    val humidity: Int,
    val pressure: Double,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
) : Parcelable