package com.example.testkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.database.RecipeDatabase

class MainActivityViewModelFactory(val recipeDatabase: RecipeDatabase?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RecipeDatabase::class.java)
            .newInstance(recipeDatabase)
    }
}