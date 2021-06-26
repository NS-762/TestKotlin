package com.example.testkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "recipes_table")
data class RecipeForDatabase(
    var label: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}