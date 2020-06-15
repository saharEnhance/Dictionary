package com.example.dictionary.inject

import com.example.dictionary.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class,RestModule::class,RepositoryModule::class,ApplicationModule::class,DaoModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}