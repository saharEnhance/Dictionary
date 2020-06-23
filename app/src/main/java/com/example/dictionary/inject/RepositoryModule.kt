package com.example.dictionary.inject

import com.example.dictionary.model.DictionaryRepositoryImp
import com.example.dictionary.network.DictionaryRestService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepositoryModule(dictionaryRestService: DictionaryRestService):DictionaryRepositoryImp{
        return DictionaryRepositoryImp(dictionaryRestService)
    }
}