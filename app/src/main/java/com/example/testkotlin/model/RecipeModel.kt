package com.example.testkotlin


data class RecipeModel(
    val hits: MutableList<Hits>
)

data class Hits(
    val recipe: Recipe
)

data class Recipe(
    val recipeName: String
)
