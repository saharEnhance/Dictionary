package com.example.dictionary.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Base::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class DictionaryRoomDB : RoomDatabase() {

    abstract fun WeatherDAO(): DictionaryDAO

    companion object {
        @Volatile
        private var INSTANCE: DictionaryRoomDB? = null

        fun getInstance(context: Context): DictionaryRoomDB {
            var tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    DictionaryRoomDB::class.java,
                    "dictionary_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
