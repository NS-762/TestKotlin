package com.example.testkotlin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecipeRequestApi(

    @SerializedName("hits")
    @Expose
    val hits: MutableList<HitsApi>
)

data class HitsApi(
    @SerializedName("recipe")
    @Expose
    val recipe: RecipeApi
)

data class RecipeApi(
    @SerializedName("label")
    @Expose
    val label: String
)
