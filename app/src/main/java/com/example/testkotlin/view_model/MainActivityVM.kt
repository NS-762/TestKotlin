package com.example.testkotlin.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivityVM : ViewModel() {

    @Inject
    lateinit var loader: Loader
    private val recipesLiveData = MutableLiveData<String>()

    init {
        App.instance!!.createMainActivityVMComponent()!!.inject(this)
    }

    fun loadRecipesFromInternet(product: String) {
        loader.loadRecipesFromInternet(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ recipeModel ->
                recipesLiveData.value = convertRecipeModelToString(recipeModel)
            }, { recipesLiveData.value = "Данные не получены" })
    }

    fun loadRecipesFromDB() {
        loader.loadRecipesFromDB()?.let {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ recipeModel ->
                    recipesLiveData.value = convertRecipeModelToString(recipeModel)
                }, { recipesLiveData.value = "Данные не получены" })
        }

//        val list = mutableListOf<Int>(1, 2, 3, 4, 5)
//
//        list.filter { it > 3 }.forEach {
//            Log.e("aaa", it.toString())
//        }
//        for (i in 0..10) {
//            Log.e("cc", i.toString())
//        }
//
//        for (i in 10 downTo 0 step 3) {
//            Log.e("bb", i.toString())
//        }
//        for (i in 1 until 10) {
//            Log.e("ff", i.toString())
//        }

    }

    private fun convertRecipeModelToString(recipeModel: RecipeModel): String {
        var recipes = ""
        for (element in recipeModel.hits) {
            recipes += "${element.recipe.recipeName}\n"
        }
        return recipes
    }

    fun getHitsRecipesLiveData(): MutableLiveData<String> = recipesLiveData
}







