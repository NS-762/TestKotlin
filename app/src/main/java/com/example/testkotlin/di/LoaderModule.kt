package com.example.testkotlin.di

import com.example.testkotlin.Loader
import dagger.Module
import dagger.Provides

@Module
class LoaderModule {

    @Provides
    fun provideLoader() : Loader {
        return Loader()
    }

}