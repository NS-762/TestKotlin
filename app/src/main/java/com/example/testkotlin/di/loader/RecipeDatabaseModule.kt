package com.example.testkotlin.di.loader

import android.content.Context
import androidx.room.Room
import com.example.testkotlin.database.RecipeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RecipeDatabaseModule {

    @Provides
    fun provideRecipeDatabase(context: Context): RecipeDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                RecipeDatabase::class.java, "recipeDatabase"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}