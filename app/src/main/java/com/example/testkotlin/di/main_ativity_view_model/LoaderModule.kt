package com.example.testkotlin.di.main_ativity_view_model

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