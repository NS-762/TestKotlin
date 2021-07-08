package com.example.testkotlin.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {


    @Provides
    @Singleton
    fun provideApp() : Context {
        return context
    }
}