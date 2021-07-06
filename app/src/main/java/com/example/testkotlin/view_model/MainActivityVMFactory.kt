package com.example.testkotlin.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.database.RecipeDatabase

class MainActivityVMFactory(val recipeDatabase: RecipeDatabase?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RecipeDatabase::class.java)
            .newInstance(recipeDatabase)
    }
}