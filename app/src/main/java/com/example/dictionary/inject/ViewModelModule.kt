package com.example.dictionary.inject

import com.example.dictionary.model.DictionaryRepositoryImp
import com.example.dictionary.viewmodel.DictionaryViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule() {

    @Provides
    fun providesFactory(dictionaryRepositoryImp: DictionaryRepositoryImp):DictionaryViewModelFactory{
        return DictionaryViewModelFactory((dictionaryRepositoryImp))
    }
}