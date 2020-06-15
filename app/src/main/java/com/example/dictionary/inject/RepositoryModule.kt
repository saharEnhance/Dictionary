package com.example.dictionary.inject

import com.example.dictionary.model.DictionaryRepositoryImp
import com.example.dictionary.model.DictionaryRoomDB
import com.example.dictionary.network.DictionaryRestService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepositoryModule(dictionaryRestService: DictionaryRestService, database: DictionaryRoomDB):DictionaryRepositoryImp{
        return DictionaryRepositoryImp(dictionaryRestService,database)
    }
}