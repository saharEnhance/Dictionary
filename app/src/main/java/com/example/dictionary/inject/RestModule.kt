package com.example.dictionary.inject

import com.example.dictionary.network.DictionaryRestService
import dagger.Module
import dagger.Provides

@Module
class RestModule {
    @Provides
    fun restService(): DictionaryRestService {
        return DictionaryRestService.instance
    }
}