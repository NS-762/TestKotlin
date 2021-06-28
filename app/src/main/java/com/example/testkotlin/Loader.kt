package com.example.testkotlin

import com.example.testkotlin.Constants.Companion.APP_ID
import com.example.testkotlin.Constants.Companion.APP_KEY
import com.example.testkotlin.activity.MainActivity
import com.example.testkotlin.database.RecipeDatabase
import com.example.testkotlin.model.RecipeForDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Loader() {

    // TODO: 28.06.2021 DI
    private val mapRecipe: MapRecipe = MapRecipe()

    fun loadRecipesFromInternet(
        product: String,
        recipeDatabase: RecipeDatabase?
    ): Single<RecipeModel> {

        val response = RetrofitInstance.api.loadRecipes(product, APP_ID, APP_KEY)
            .map { recipeRequestApi ->
                mapRecipe.mapRecipeApiToRecipeModel(recipeRequestApi)
                //Преобразовать апи в модель без аннотаций
                //В след. звене цепочки - список моделей без аннотаций
            }
            .map { recipeModel ->
                mapRecipe.mapRecipeModelToRecipeForDB(recipeModel)
                //Преобразовать модели без аннотаций в объекты для БД
                //В след. звене - список объектов для БД
            }
            .map { listRecipesForDB ->
                recipeDatabase?.getRecipeDao()?.deleteAllRecipes()
                recipeDatabase?.getRecipeDao()?.insertListRecipes(listRecipesForDB)
                //Отчистить БД от предыдущих рецептов и поместить новые данные
                //В след звене - юнит
            }
            .map {
                recipeDatabase?.getRecipeDao()?.getAllRecipes()
                //Получение данных из БД
                //В след звене -ьсписок объектов из БД
            }
            .map { listRecipesForDB ->
                listRecipesForDB?.let {
                    mapRecipe.mapRecipeForDBToRecipeModel(it)
                }
                //Преобразовать в модель
                //В след звене - модель
            }
        return response
    }

    // TODO: 28.06.2021 БД DI
    fun loadRecipesFromDB(recipeDatabase: RecipeDatabase): Single<RecipeModel> {
        return recipeDatabase.getRecipeDao().getAllRecipesInSingle()
            .map { listRecipesForDB ->
                mapRecipe.mapRecipeForDBToRecipeModel(listRecipesForDB)
                //Преобразовать список рецептов из БД в модель
            }
    }
}