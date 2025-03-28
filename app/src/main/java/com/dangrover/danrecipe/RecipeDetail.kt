package com.dangrover.danrecipe
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dangrover.danrecipe.databinding.RecipeDetailBinding


class RecipeDetail  : Fragment() {
    private var _binding: RecipeDetailBinding? = null
    private val binding get() = _binding!!
    private var recipe : Recipe? = null;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the recipe
        val recipeId = arguments?.getInt("recipeId") ?: 0
        Log.d("RecipeDetail", "RecipeId: $recipeId")
        this.recipe = RecipeAppDatabase.getDatabase(requireContext()).recipeDao().getRecipeById(recipeId)

        // Set text
        binding.recipeText.text = this.formatRecipeForDisplay(this.recipe!!)

        // Set title by setting the label for this fragment
        // because we are in a nav controller
        activity?.title = this.recipe!!.title

        // Bind action listeners for the favorite button and schedule buttons
        binding.favoriteButton.setOnClickListener{
            favoritePressed()
        }

        binding.scheduleButton.setOnClickListener {
            Log.d("RecipeDetail", "Schedule button clicked")
        }

        Log.d("RecipeDetail", "Activity: ${this.activity}")
    }

    private fun favoritePressed(){
        Log.d("RecipeDetail", "Favorite button clicked")

        // update button state, toggle favorite flag in database
        val r = this.recipe!!
        val isFavorite = r.isFavorite
        r.isFavorite = !isFavorite
        RecipeAppDatabase.getDatabase(requireContext()).recipeDao().updateRecipe(r)

        // update button text
        binding.favoriteButton.text = if (isFavorite) "Favorite" else "Unfavorite"
    }

    private fun formatRecipeForDisplay(recipe: Recipe): String {
        var formatted = ""

        // ingredients is: ['ingredient1", 'ingredient2' ...]
        // Remove brackets, delimit with commas, then remove quotes
        if (recipe.ingredients != null) {
            Log.d("RecipeDetail", "Ingredients: ${recipe.ingredients}")
            val parsedIngredientsArray = recipe.ingredients.removeSurrounding("[", "]").split(",").map { it.removeSurrounding("'", "'") }
            Log.d("RecipeDetail", "Parsed Ingredients: $parsedIngredientsArray")
            val prefixed = parsedIngredientsArray?.mapIndexed { index, item -> "- $item" }
            formatted += "INGREDIENTS:\n"+prefixed?.joinToString("\n")+"\n"
        }

        if (recipe.instructions != null) {
            formatted += "\nINSTRUCTIONS:\n" + recipe.instructions
        }

        return formatted
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        @JvmStatic
        fun newInstance(recipeId: Int): RecipeDetail {
            val fragment = RecipeDetail()
            val args = Bundle()
            args.putInt("recipeId", recipeId)
            fragment.arguments = args
            return fragment
        }
    }

}