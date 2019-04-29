package pl.kowalczyk92.weather.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
    val all: Int
) : Parcelable