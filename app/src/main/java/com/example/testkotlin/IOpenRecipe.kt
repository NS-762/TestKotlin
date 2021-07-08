package com.example.testkotlin

import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenRecipe {
    @GET("/search")
    suspend fun loadRecipes(
        @Query("q") product: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String): RecipeRequestApi

//    https://api.edamam.com/search?q=chicken&app_id=a7f34838&app_key=3fd573f25cb1b98bd8cb8df5c682dd2e&from=0&to=3&calories=591-722&health=alcohol-free
}