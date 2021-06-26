package com.example.testkotlin.database

import androidx.room.*
import com.example.testkotlin.model.RecipeForDatabase

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipeForDatabase: RecipeForDatabase)

    @Update
    fun updateRecipe(recipeForDatabase: RecipeForDatabase)

    @Delete
    fun deleteRecipe(recipeForDatabase: RecipeForDatabase)

    @Query("SELECT * FROM recipes_table WHERE label=:label")
    fun getRecipeOnLabel(label: String) : RecipeForDatabase

    @Query("DELETE FROM recipes_table")
    fun clearDatabase()

}