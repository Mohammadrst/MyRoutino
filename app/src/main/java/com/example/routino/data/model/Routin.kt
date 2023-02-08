package com.example.routino.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "Routin")
data class Routin(
    var title: String,
    var doneDaysList: ArrayList<String> = ArrayList<String>(),
    var timesInWeek: Int,
    var color: String,
    var remmeberTime: String = "",
    var remmeberDay: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

class MyConverter(){
    val gson = Gson()

    @TypeConverter
    fun arrayListToJson(list: ArrayList<String>?): String? {
        return if(list == null) null else gson.toJson(list)
    }

    @TypeConverter
    fun jsonToArrayList(jsonData: String?): ArrayList<String>? {
        return if (jsonData == null) null else gson.fromJson(jsonData, object : TypeToken<ArrayList<String>?>() {}.type)
    }

    /*@TypeConverter
    fun fromString(value: String?): ArrayList<String> {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }*/
}


