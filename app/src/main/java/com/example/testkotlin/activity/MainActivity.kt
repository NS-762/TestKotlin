package com.example.testkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.testkotlin.*
import com.example.testkotlin.view_model.MainActivityVM

class MainActivity : AppCompatActivity() {

    private lateinit var etInputProduct: EditText
    private lateinit var btnLoadRecipes: Button
    private lateinit var tvRecipes: TextView
    private lateinit var mainActivityVM: MainActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()

        var pizza1: Pizza = TomatoPizza(ItalianPizza(TomatoPizza(EmptyPizza())))
        Log.e("AGAGAG", pizza1.getCost().toString())
    }

    private fun init() {
        mainActivityVM =
            ViewModelProvider(this) //, MainActivityVMFactory(recipeDatabase) пример создания viewModel через фабрику
                .get(MainActivityVM::class.java)
        mainActivityVM.loadRecipesFromDB()

        etInputProduct = findViewById(R.id.ET_input_product)
        tvRecipes = findViewById(R.id.TV_recipes)
        btnLoadRecipes = findViewById(R.id.BTN_load_recipes)

        btnLoadRecipes.setOnClickListener {
            val str = etInputProduct.text.toString()
            if (!str.isNullOrEmpty() and !str.isNullOrBlank()) {
                tvRecipes.text = ""
                mainActivityVM.loadRecipesFromInternet((etInputProduct.text).toString())
            } else tvRecipes.text = "Введите название продукта"
        }

        mainActivityVM.apply {
            mainActivityVM.getHitsRecipesLiveData().observeForever { addRecipesInTV(it) }
        }
    }

    private fun addRecipesInTV(recipes: String) {
        tvRecipes.text = recipes
    }
}