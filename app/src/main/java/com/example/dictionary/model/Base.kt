package com.example.dictionary.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type

@Parcelize
@Entity(tableName = "dictionary")
@TypeConverters
data class Base(

    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val list: ArrayList<List>
) : Parcelable

@Parcelize
data class List(

    val definition: String,
    val permalink: String,
    val thumbs_up: Int,
    val sound_urls: ArrayList<String>,
    val author: String,
    val word: String,
    val defid: Int,
    val current_vote: String,
    val written_on: String,
    val example: String,
    val thumbs_down: Int
) : Parcelable

class Converters {

    @TypeConverter
    fun dfromString(value: String): ArrayList<List> {
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun dfromArrayLisr(list: ArrayList<List>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
    fun fromString(value: String): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
