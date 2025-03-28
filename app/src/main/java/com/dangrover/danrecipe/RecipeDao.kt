package com.dangrover.danrecipe;
import androidx.room.*;

@Dao
interface RecipeDao {

    // get random recipes with count
    @Query("SELECT * FROM recipes ORDER BY RANDOM() LIMIT :count")
    fun getRandomRecipes(count: Int): List<Recipe>

    // get recipe by id
    @Query("SELECT * FROM recipes WHERE id = :id")
    fun getRecipeById(id: Int): Recipe

    // update recipe
    @Update
    fun updateRecipe(recipe: Recipe)

    // schedule recipe for date
    @Insert
    fun scheduleRecipe(recipeScheduleOccurrence: RecipeScheduleOccurrence)


}
