package com.example.dictionary.inject

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context
    }
}