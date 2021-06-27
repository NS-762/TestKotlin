package com.example.testkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.Hits
import com.example.testkotlin.R
import com.example.testkotlin.database.RecipeDao
import com.example.testkotlin.database.RecipeDatabase
import com.example.testkotlin.model.RecipeForDatabase
import com.example.testkotlin.viewModel.MainActivityViewModel
import com.example.testkotlin.viewModel.MainActivityViewModelFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var etInputProduct: EditText
    private lateinit var btnLoadRecipes: Button
    private lateinit var tvRecipes: TextView
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private var recipeDatabase: RecipeDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
    }

    private fun init() {
        recipeDatabase = RecipeDatabase.getRecipeDatabase(this);

        mainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(recipeDatabase)
        )
            .get(MainActivityViewModel::class.java)
        mainActivityViewModel.loadRecipesFromDB()

        etInputProduct = findViewById(R.id.ET_input_product)
        tvRecipes = findViewById(R.id.TV_recipes)
        btnLoadRecipes = findViewById(R.id.BTN_load_recipes)

        btnLoadRecipes.setOnClickListener {

            val str = etInputProduct.text.toString()
            if (!str.isNullOrEmpty() and !str.isNullOrBlank()) {
                tvRecipes.text = ""
                mainActivityViewModel.loadRecipesFromInternet((etInputProduct.text).toString())
            } else tvRecipes.text = "Введите название продукта"

        }

        mainActivityViewModel.apply {
            mainActivityViewModel.getHitsRecipesLiveData().observeForever { addRecipesInTV(it) }
        }
    }

    private fun addRecipesInTV(listHitsRecipes: MutableList<Hits>) {
        var recipes = ""

        for (element in listHitsRecipes) {
            recipes += "\n${element.recipe.recipeName}"
        }
        tvRecipes.text = recipes
    }
}