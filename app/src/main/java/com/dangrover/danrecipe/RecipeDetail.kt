package com.dangrover.danrecipe
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dangrover.danrecipe.databinding.RecipeDetailBinding

class RecipeDetail  : Fragment() {
    private var _binding: RecipeDetailBinding? = null
    private var recipeId: Int = 0

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
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