package com.example.dictionary.inject

import androidx.room.Room
import com.example.dictionary.model.DictionaryDAO
import com.example.dictionary.model.DictionaryRoomDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule(application: DictionaryApplication) {

    private val database:DictionaryRoomDB = Room.databaseBuilder(application,
        DictionaryRoomDB::class.java, "dictionary_db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase():DictionaryRoomDB{
        return database
    }
    @Singleton
    @Provides
    fun providesProductDao(database: DictionaryRoomDB):DictionaryDAO{
        return database.WeatherDAO()
    }
}