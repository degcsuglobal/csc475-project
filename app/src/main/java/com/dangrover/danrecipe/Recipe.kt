package com.dangrover.danrecipe
import androidx.room.*

@Entity
data class Recipe(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "Title") val title: String?,
    @ColumnInfo(name = "Ingredients") val ingredients: String?,
    @ColumnInfo(name = "Instructions") val instructions: String?
)