package com.example.dictionary.inject

import android.app.Application

class DictionaryApplication:Application() {

    val appComponent by lazy{
        initializeComponent()
    }
    private fun initializeComponent():AppComponent {
        return DaggerAppComponent.builder()
            .viewModelModule(ViewModelModule())
            .restModule(RestModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}