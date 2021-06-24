package com.example.testkotlin

import com.google.gson.annotations.SerializedName

data class RecipeRequest(
    val hits: List<Hits>? = null
)

data class Hits(
   val recipe: Recipe? = null
)

data class Recipe(
    val label: String? = null
)
