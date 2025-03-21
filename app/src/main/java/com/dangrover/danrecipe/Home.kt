package com.dangrover.danrecipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dangrover.danrecipe.databinding.FragmentHomeBinding
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up tabs
        val tabHost = binding.tabhost
        tabHost.setup()

        val tab1 = tabHost.newTabSpec("Featured")
        tab1.setIndicator(getString(R.string.tab_Featured))
        tab1.setContent(R.id.featured)
        tabHost.addTab(tab1)

        val tab2 = tabHost.newTabSpec("Scheduled")
        tab2.setIndicator(getString(R.string.tab_Scheduled))
        tab2.setContent(R.id.scheduled)
        tabHost.addTab(tab2)

        val tab3 = tabHost.newTabSpec("Past")
        tab3.setIndicator(getString(R.string.tab_Past))
        tab3.setContent(R.id.past)
        tabHost.addTab(tab3)


        // Featured recycler view
        val featuredRecycler : RecyclerView = binding.featuredRecycler



        // Get DB
        val db = RecipeAppDatabase.getDatabase(requireContext())
        val recipeDao = db.recipeDao()

        // load 10 random recipes
        val recipes = recipeDao.getRandomRecipes(30)

        // set it up
        featuredRecycler.layoutManager = LinearLayoutManager(requireContext())
        featuredRecycler.adapter = FeaturedRecipesAdapter(recipes, this)

        // print it out in logcat
        Log.d("MainActivity", "Random Recipes: ${recipes.size}")
        for (recipe in recipes) {
            Log.d("MainActivity", "Recipe: ${recipe.title}")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    class FeaturedRecipesAdapter(private val recipes: List<Recipe>,
                                 private val parentFragment: Fragment) : RecyclerView.Adapter<FeaturedRecipesAdapter.FeaturedRecipeViewHolder>() {

        class FeaturedRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedRecipeViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_row, parent, false)
            return FeaturedRecipeViewHolder(view)
        }

        override fun getItemCount(): Int {
            return recipes.size
        }

        override fun onBindViewHolder(holder: FeaturedRecipeViewHolder, position: Int) {
            // set title
            val titleTextView = holder.itemView.findViewById<TextView>(R.id.recipe_title)
            titleTextView.text = recipes[position].title

            // make card view clickable
            val cardView = holder.itemView.findViewById<CardView>(R.id.card)
            cardView.setOnClickListener {
                Log.d("MainActivity", "Recipe clicked: ${recipes[position].title}")
                val recipeId = recipes[position].recipeId

                findNavController(parentFragment).navigate(R.id.action_FirstFragment_to_recipeDetail, Bundle().apply {
                    putInt("recipeId", recipeId)
                })

            }

        }
    }


}
