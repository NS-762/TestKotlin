package com.example.testkotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.*
import com.example.testkotlin.database.RecipeDatabase
import com.example.testkotlin.model.RecipeForDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel(val recipeDatabase: RecipeDatabase?) : ViewModel() {

    private val hitsRecipesLiveData = MutableLiveData<MutableList<Hits>>()

    @Inject
    lateinit var loader: Loader

    init {
        App.appComponent.injectToMainActivityViewModel(this)
    }


/*    private lateinit var loader: Loader
    init {
        loader = App.appComponent.getLoader()
    }*/

    fun loadRecipesFromInternet(product: String) {

        loader.loadRecipesFromInternet(product, recipeDatabase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { recipeModel ->
                hitsRecipesLiveData.value = recipeModel.hits
            }
    }

    fun loadRecipesFromDB() {
        loader.loadRecipesFromDB(recipeDatabase)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { recipeModel ->
                hitsRecipesLiveData.value = recipeModel.hits
            }
    }

    fun getHitsRecipesLiveData(): LiveData<MutableList<Hits>> = hitsRecipesLiveData
}







