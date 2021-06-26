package com.example.testkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testkotlin.model.RecipeForDatabase

@Database(entities = [RecipeForDatabase::class], version = 2)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    companion object {
        private var instance: RecipeDatabase? = null

        fun getRecipeDatabase(context: Context): RecipeDatabase? {
            if (instance == null) {

                instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java, "recipeDatabase"
                    )
                    .fallbackToDestructiveMigration()
                    .build()

            }
            return instance
        }

        fun destroyDataBase() {
            instance = null
        }
    }
}