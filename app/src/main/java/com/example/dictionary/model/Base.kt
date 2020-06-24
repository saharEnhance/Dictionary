package com.example.dictionary.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Base(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val list: ArrayList<DictionaryList>
) : Parcelable

@Parcelize
data class DictionaryList(

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