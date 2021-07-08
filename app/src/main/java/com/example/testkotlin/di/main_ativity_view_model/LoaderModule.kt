package com.example.testkotlin.di.main_ativity_view_model

import com.example.testkotlin.IOpenRecipe
import com.example.testkotlin.Loader
import com.example.testkotlin.MapRecipe
import com.example.testkotlin.database.RecipeDatabase
import dagger.Module
import dagger.Provides

@Module
class LoaderModule {

    @Provides
    fun provideLoader(mapRecipe: MapRecipe, api: IOpenRecipe,
                      recipeDatabase: RecipeDatabase): Loader {
        return Loader(mapRecipe, api, recipeDatabase)
    }
}