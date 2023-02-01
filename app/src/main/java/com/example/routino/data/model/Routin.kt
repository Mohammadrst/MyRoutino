package com.example.routino.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


@Entity(tableName = "Routin")
data class Routin(
    val title: String,
    val list: ArrayList<String>,
    val timesInWeek: Int,
    val color: String,
    val remmeberTime: String,
    val remmeberDay: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}


class MyConverter {

    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}
