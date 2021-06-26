package com.example.testkotlin

class MapRecipeRequest () {

    private lateinit var recipeRequest: RecipeRequest
    private lateinit var listHits: MutableList<Hits>


    fun mapRecipeRequest(recipeRequestApi: RecipeRequestApi) : RecipeRequest {
        listHits = mutableListOf()

        for (element in recipeRequestApi.hits) {
            var recipe = Recipe(element.recipe.label)
            var hits = Hits(recipe)
            listHits.add(hits)
        }
        recipeRequest = RecipeRequest(listHits)

        return recipeRequest
    }


}