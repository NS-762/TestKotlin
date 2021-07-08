package com.example.testkotlin.di.loader

import com.example.testkotlin.MapRecipe
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    fun provideMap(): MapRecipe {
        return MapRecipe()
    }
}