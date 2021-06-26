package com.example.testkotlin

import com.example.testkotlin.Constants.Companion.APP_ID
import com.example.testkotlin.Constants.Companion.APP_KEY
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Loader {

    private val mapRecipeRequest: MapRecipeRequest = MapRecipeRequest()

    fun loadRecipes(product: String) : Single<RecipeRequest> {

        // TODO: 25.06.2021 убрать потоки 
        val response = RetrofitInstance.api.loadRecipes(product, APP_ID, APP_KEY)
            .subscribeOn(Schedulers.io())
            .map { recipeRequestApi ->
                mapRecipeRequest.mapRecipeRequest(recipeRequestApi)
            }
            .observeOn(AndroidSchedulers.mainThread())

        return response
    }
}