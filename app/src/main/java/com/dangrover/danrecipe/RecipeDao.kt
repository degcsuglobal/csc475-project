package com.dangrover.danrecipe;
import androidx.room.*;

@Dao
interface RecipeDao {


    // get random recipes with count
    @Query("SELECT * FROM recipes ORDER BY RANDOM() LIMIT :count")
    fun getRandomRecipes(count: Int): List<Recipe>

}
