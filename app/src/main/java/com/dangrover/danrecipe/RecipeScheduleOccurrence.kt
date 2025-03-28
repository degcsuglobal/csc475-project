package com.dangrover.danrecipe
import androidx.room.*
import java.util.Date


// takes a recipe ID (referencing the recipe table)
// and a date.
@Entity(tableName = "recipe_scheduling")
class RecipeScheduleOccurrence {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    @ColumnInfo(name = "recipe_id") var recipeId: Int = 0
    @ColumnInfo(name = "date") var date: Date = Date()
}