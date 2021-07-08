package com.example.testkotlin

import com.example.testkotlin.Constants.Companion.APP_ID
import com.example.testkotlin.Constants.Companion.APP_KEY
import com.example.testkotlin.database.RecipeDatabase
import com.example.testkotlin.view_model.BaseViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Loader @Inject constructor(
    val mapRecipe: MapRecipe, var api: IOpenRecipe,
    var recipeDatabase: RecipeDatabase
) {

    suspend fun loadRecipesFromInternet(product: String): RecipeModel {


        val recipeRequestApi = api.loadRecipes(product, APP_ID, APP_KEY)
        var recipeModel = mapRecipe.mapRecipeApiToRecipeModel(recipeRequestApi)
        var listRecipesForDB = mapRecipe.mapRecipeModelToRecipeForDB(recipeModel)
        recipeDatabase.getRecipeDao().deleteAllRecipes()
        recipeDatabase.getRecipeDao().insertListRecipes(listRecipesForDB)

        //Нужен ли этот шаг, если уже есть список рецетов из БД
        listRecipesForDB = recipeDatabase.getRecipeDao().getAllRecipes()

        //Нужен ли этот шаг, если уже есть модель без аннотаций с рецептами
        listRecipesForDB?.let {
            recipeModel = mapRecipe.mapRecipeForDBToRecipeModel(it)

        }
        return recipeModel
    }

//
//            .map { recipeRequestApi ->
//                mapRecipe.mapRecipeApiToRecipeModel(recipeRequestApi)
//                //Преобразовать апи в модель без аннотаций
//                //В след. звене цепочки - список моделей без аннотаций
//            }
//            .map { recipeModel ->
//                mapRecipe.mapRecipeModelToRecipeForDB(recipeModel)
//                //Преобразовать модели без аннотаций в объекты для БД
//                //В след. звене - список объектов для БД
//            }
//            .map { listRecipesForDB ->
//                recipeDatabase.getRecipeDao().deleteAllRecipes()
//                recipeDatabase.getRecipeDao().insertListRecipes(listRecipesForDB)
//                //Отчистить БД от предыдущих рецептов и поместить новые данные
//                //В след звене - юнит
//            }
//            .map {
//                recipeDatabase.getRecipeDao().getAllRecipes()
//                //Получение данных из БД
//                //В след звене -ьсписок объектов из БД
//            }
//            .map { listRecipesForDB ->
//                listRecipesForDB?.let {
//                    mapRecipe.mapRecipeForDBToRecipeModel(it)
//                }
//                //Преобразовать в модель
//                //В след звене - модель
//            }
//    }

    suspend fun loadRecipesFromDB(): RecipeModel? {
        val listRecipesForDB = recipeDatabase.getRecipeDao().getAllRecipes()
        val recipeModel = mapRecipe.mapRecipeForDBToRecipeModel(listRecipesForDB)
        return recipeModel
    }
}