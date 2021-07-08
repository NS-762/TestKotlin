package com.example.testkotlin.database

import androidx.room.*
import com.example.testkotlin.model.RecipeForDatabase
import io.reactivex.rxjava3.core.Single

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipeForDatabase: RecipeForDatabase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListRecipes(listRecipesForDB: MutableList<RecipeForDatabase>)

    @Update
    fun updateRecipe(recipeForDatabase: RecipeForDatabase)

    @Delete
    fun deleteRecipe(recipeForDatabase: RecipeForDatabase)

    @Query("SELECT * FROM recipes_table")
    fun getAllRecipes() : MutableList<RecipeForDatabase>

//    @Query("SELECT * FROM recipes_table")
//    fun getAllRecipesInSingle() : Single<MutableList<RecipeForDatabase>>

    @Query("SELECT * FROM recipes_table WHERE recipeName=:recipeName")
    fun getRecipeOnRecipeName(recipeName: String) : RecipeForDatabase

    @Query("DELETE FROM recipes_table")
    fun deleteAllRecipes()

}