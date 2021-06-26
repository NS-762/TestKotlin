package com.example.testkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testkotlin.*
import io.reactivex.rxjava3.disposables.Disposable

class MainActivityViewModel : ViewModel() {

    private val recipesLiveData = MutableLiveData<MutableList<Hits>>()

    private val loader: Loader = Loader()
    val disposable: Disposable? = null

    fun loadRecipes(product: String) {

        // TODO: 25.06.2021 ???

        loader.loadRecipes(product)
            .subscribe { recipes ->
            recipesLiveData.value = recipes.hits
        }
    }

    fun getRecipesLiveData(): LiveData<MutableList<Hits>> = recipesLiveData
}







