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

class MainActivity : AppCompatActivity() {

    private lateinit var etInputProduct: EditText
    private lateinit var btnLoadRecipes: Button
    private lateinit var tvRecipes: TextView
    private lateinit var mainActivityViewModel: MainActivityViewModel


//    private var recipeDatabase: RecipeDatabase? = null
    private var recipeDao: RecipeDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        recipeDatabase = RecipeDatabase.getRecipeDatabase(this);
        val recipeDatabase = RecipeDatabase.getRecipeDatabase(this);
        recipeDao = recipeDatabase?.getRecipeDao()
        val recipeForDatabase = RecipeForDatabase( "hahaaa")
        recipeDao?.insertRecipe(recipeForDatabase)


        var testRecipe: RecipeForDatabase? = recipeDao?.getRecipeOnLabel(recipeForDatabase.label)
        testRecipe?.let { Log.e("Recipe", it.label) }


        init()
    }

    private fun init() {
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        etInputProduct = findViewById(R.id.ET_input_product)
        tvRecipes = findViewById(R.id.TV_recipes)
        btnLoadRecipes = findViewById(R.id.BTN_load_recipes)

        btnLoadRecipes.setOnClickListener {

            val str = etInputProduct.text.toString()
            if (!str.isNullOrEmpty() and !str.isNullOrBlank()) {
                tvRecipes.text = ""
                mainActivityViewModel.loadRecipes((etInputProduct.text).toString())
            } else tvRecipes.text = "Введите название продукта"

        }

        mainActivityViewModel.apply {
            mainActivityViewModel.getRecipesLiveData().observeForever { addRecipeInTV(it) }
        }
    }

    private fun addRecipeInTV(listHits: MutableList<Hits>) {
        var recipes: String = ""

        for (element in listHits) {
            recipes += "\n${element.recipe.label}"
        }
        tvRecipes.text = recipes
    }
}