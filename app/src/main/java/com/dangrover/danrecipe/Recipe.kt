package com.dangrover.danrecipe
import androidx.room.*

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey @ColumnInfo(name="id") val recipeId: Int,
    @ColumnInfo(name = "Title") val title: String?,
    @ColumnInfo(name = "Ingredients") val ingredients: String?,
    @ColumnInfo(name = "Instructions") val instructions: String?,
    @ColumnInfo(name = "IsFavorite") var isFavorite: Boolean = false

)