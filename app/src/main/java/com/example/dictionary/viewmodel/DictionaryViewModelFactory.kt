package com.example.dictionary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.model.DictionaryRepositoryImp

class DictionaryViewModelFactory(private val dictionaryRepositoryImp: DictionaryRepositoryImp) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DictionaryViewModel(dictionaryRepositoryImp) as T
    }
}