package com.example.testkotlin

import com.example.testkotlin.model.RecipeForDatabase

class MapRecipe () {

    //Преобразование объекта с рецетами из интернета в список рецептов для ДБ
    fun mapRecipeApiToRecipeForDB(recipeApi: RecipeRequestApi) : MutableList<RecipeForDatabase> {
        var listRecipesForDB = mutableListOf<RecipeForDatabase>()
        for (element in recipeApi.hits) {
            var recipeName = element.recipe.recipeName
            var recipeForDB = RecipeForDatabase(recipeName)
            listRecipesForDB.add(recipeForDB)
        }
        return listRecipesForDB
    }

    //Преобразование списка рецептов из ДБ в модель рецепта
    fun mapRecipeForDBToRecipeModel(listRecipesForDB : MutableList<RecipeForDatabase>)
    : RecipeModel {
        var listHits = mutableListOf<Hits>()
        for (element in listRecipesForDB) {
            var label = element.recipeName
            var recipe = Recipe(label)
            var hits = Hits(recipe)
            listHits.add(hits)
        }
        val recipeModel = RecipeModel(listHits)
        return recipeModel
    }

    //Преобразование объекта с рецетами из интернета в модель рецепта
    fun mapRecipeApiToRecipeModel(recipeRequestApi: RecipeRequestApi) : RecipeModel {
        var listRecipeModels =  mutableListOf<RecipeModel>()
        var listHits = mutableListOf<Hits>()
        for (element in recipeRequestApi.hits) {
            var recipe = Recipe(element.recipe.recipeName)
            var hits = Hits(recipe)
            listHits.add(hits)
        }
        val recipeModel = RecipeModel(listHits)
        return recipeModel
    }

    //Преобразование модели рецепта из интернета в список рецептов для ДБ
    fun mapRecipeModelToRecipeForDB(recipeModel: RecipeModel) : MutableList<RecipeForDatabase> {
        var listRecipesForDB = mutableListOf<RecipeForDatabase>()
        for (element in recipeModel.hits) {
            var recipeName = element.recipe.recipeName
            var recipeForDB = RecipeForDatabase(recipeName)
            listRecipesForDB.add(recipeForDB)
        }
        return listRecipesForDB
    }
}