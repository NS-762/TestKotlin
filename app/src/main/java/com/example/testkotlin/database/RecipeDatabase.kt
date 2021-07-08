package com.example.testkotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testkotlin.model.RecipeForDatabase

@Database(entities = [RecipeForDatabase::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}