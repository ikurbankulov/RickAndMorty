package com.example.data.local.database

import androidx.room.TypeConverter
import com.example.data.local.models.LocationDbModel
import com.google.gson.Gson

class LocationTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun locationToString(location: LocationDbModel): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun stringToLocation(locationString: String): LocationDbModel {
        return gson.fromJson(locationString, LocationDbModel::class.java)
    }
}
