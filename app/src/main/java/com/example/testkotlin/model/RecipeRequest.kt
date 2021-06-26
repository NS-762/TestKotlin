package com.example.testkotlin


data class RecipeRequest(
    val hits: MutableList<Hits>
)

data class Hits(
    val recipe: Recipe
)

data class Recipe(
    val label: String
)
