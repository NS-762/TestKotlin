package com.example.testkotlin

import com.example.testkotlin.Constants.Companion.APP_ID
import com.example.testkotlin.Constants.Companion.APP_KEY
import com.example.testkotlin.database.RecipeDatabase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Loader() {

    @Inject
    lateinit var mapRecipe: MapRecipe

    @Inject
    lateinit var api: IOpenRecipe

    @Inject
    lateinit var recipeDatabase: RecipeDatabase

    init {
        App.instance!!.createLoaderComponent()!!.inject(this)
    }

    fun loadRecipesFromInternet(product: String): Single<RecipeModel> {
        return api.loadRecipes(product, APP_ID, APP_KEY)
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
                recipeDatabase.getRecipeDao().deleteAllRecipes()
                recipeDatabase.getRecipeDao().insertListRecipes(listRecipesForDB)
                //Отчистить БД от предыдущих рецептов и поместить новые данные
                //В след звене - юнит
            }
            .map {
                recipeDatabase.getRecipeDao().getAllRecipes()
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
    }

    fun loadRecipesFromDB(): Single<RecipeModel>? {
        return recipeDatabase.getRecipeDao().getAllRecipesInSingle()
            .map { listRecipesForDB ->
                mapRecipe.mapRecipeForDBToRecipeModel(listRecipesForDB)
                //Преобразовать список рецептов из БД в модель
            }
    }
}