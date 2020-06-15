package com.example.dictionary.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DictionaryDAO {

    @Query("SELECT * FROM dictionary")
    fun getWeather(): LiveData<Base>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateCache(weather: Base)

}