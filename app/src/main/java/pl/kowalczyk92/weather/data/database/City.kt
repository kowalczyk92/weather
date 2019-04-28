package pl.kowalczyk92.weather.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "cities", indices = [Index(value = ["name"], unique = true)])
data class City(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String
)