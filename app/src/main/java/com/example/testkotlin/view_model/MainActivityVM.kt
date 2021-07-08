package com.example.testkotlin.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.*
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityVM @Inject constructor(val loader: Loader) : BaseViewModel() {

    private val recipesLiveData = MutableLiveData<String>()


    fun loadRecipesFromInternet(product: String) {

        doWork {
            val recipeModel = loader.loadRecipesFromInternet(product)
            withContext(Dispatchers.Main) {
                recipesLiveData.value = convertRecipeModelToString(recipeModel)
            }
        }


//        loader.loadRecipesFromInternet(product)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ recipeModel ->
//                recipesLiveData.value = convertRecipeModelToString(recipeModel)
//            }, { recipesLiveData.value = "Данные не получены" })
    }

    fun loadRecipesFromDB() {

        doWork {
            val recipeModel = loader.loadRecipesFromDB()
            recipeModel?.let {
                withContext(Dispatchers.Main) {
                    recipesLiveData.value = convertRecipeModelToString(recipeModel)
                }
            }
        }

//        loader.loadRecipesFromDB()?.let {
//            it.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ recipeModel ->
//                    recipesLiveData.value = convertRecipeModelToString(recipeModel)
//                }, { recipesLiveData.value = "Данные не получены" })
//        }
    }

    private fun convertRecipeModelToString(recipeModel: RecipeModel): String {
        var recipes = ""
        for (element in recipeModel.hits) {
            recipes += "${element.recipe.recipeName}\n"
        }
        return recipes
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    fun getHitsRecipesLiveData(): MutableLiveData<String> = recipesLiveData
}







