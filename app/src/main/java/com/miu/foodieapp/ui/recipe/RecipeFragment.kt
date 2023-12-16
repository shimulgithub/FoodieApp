package com.miu.foodieapp.ui.recipe

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.foodieapp.R
import com.miu.foodieapp.adapter.RecipeAdapter
import com.miu.foodieapp.databinding.FragmentRecipeBinding
import com.miu.foodieapp.model.Recipe

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding

    private val recipeList = ArrayList<Recipe>(listOf(
        Recipe(
            "Cheese Pizza",
            "Simple Classic Cheese Pizza recipe perfect for every occasion.",
            """A single ball of artisan pizza dough * All-purpose flour for dusting * Semolina flour for dusting
                | ¼ cup Basic Pizza Sauce (recipe below) 
                | 3 ounces of low-moisture mozzarella that has been shredded 
                | Fresh basil or oregano leaves for decoration
                | """.trimMargin(),
            """ Step 1: Take the artisan pizza dough out of the fridge and let it stand for one to two hours at room temperature, or until it's chilly but not frigid. Press the dough onto a surface dusted with flour, allowing a 1/2-inch margin.
                | Step 2: Set a baking steel or big round cast-iron pizza pan on the center rack of your preheated home oven and preheat it to 500°F. Give the pan around thirty minutes to warm in the oven. For an outdoor pizza oven, preheat the oven and pizza stone on high for 20 minutes, as directed by the manufacturer.
                | Step 3: Using the outside edge of your hand, push hard to create a 1/2-inch wide ring around the perimeter of the dough. The dough should be gently stretched into a 10-inch circular, keeping a!""".trimMargin(),
            R.drawable.pizza,
            "30 min"
        ),
        Recipe(
            "Pasta",
            "Indulge in the rich and satisfying flavors of creamy Alfredo pasta.",
            """1 tbsp olive oil 2 cloves garlic minced NESTLÉ® MEDIUM CREMA - 7.6 fluid ounces 1/2 
                |Cup Whole Milk 3/4 Cup Grated Parmesan Cheese 1/2 Pound Fettuccini Pasta or
                | Pasta of your choice Freshly Ground Black Pepper Chopped Parsley for garnish""".trimMargin(),
            """Step 1: Prepare the pasta as directed on the packet. Step 2: In a medium saucepan, 
                heat the olive oil over medium-low heat. Drain and set aside. Add the minced garlic and cook, stirring, 
                for approximately one minute, or until fragrant.
                Step 3: Turn down the heat. Mix in milk and NESTLÉ Media Crema.
                 Add a dash of salt and freshly ground black pepper for seasoning.
                Step 4: Add the grated Parmesan cheese to the sauce and bring it to a mild boil. Step 5: Serve the hot, cooked pasta with the creamy Alfredo sauce, stirring regularly, for two minutes or until the sauce thickens. Add some chopped parsley as a garnish. Step 6: Savor your creamy and delectable Alfredo pasta!""".trimMargin(),
            R.drawable.pasta,
        "25 min"
        ),
        Recipe(
            "Beef Burger",
            "A delicious and juicy classic beef burger recipe that everyone will love.",
            """One pound of ground beef
                Half a teaspoon of black pepper and one teaspoon of salt
                With four hamburger buns, four pieces of cheddar cheese, 
                lettuce, tomato, onion, and pickles as garnishes, and mustard and ketchup as condiments""".trimMargin(),
            """Step1: Pre-heat your stovetop griddle or grill to medium-high temperature.
                |Step2: Partition the ground meat into 4 equivalent parcels and shape them into burger patties. Season every patty with salt and pepper.
                |Step3: Put the burger patties on the hot barbecue or frying pan. For medium-rare, cook each side for 4-5 minutes, adjusting according to preference.
                |Step4: Add a slice of cheddar cheese to each patty in the final minute of cooking and cover to melt the cheese.
                |Step5: Toast the cheeseburger buns on the barbecue briefly or until they are brilliant brown.
                |Step6: Collect the burgers by putting every patty on a bun. Pickles, onion, lettuce, and tomato can be added as desired. Spread ketchup and mustard on the top bun.
                |Step7: Serve the burgers hot and appreciate!""".trimMargin(),
            R.drawable.burger,
            "20 min"
        ),
        Recipe(
            "French Fries",
        "Make perfectly crispy and golden homemade French fries with this easy recipe.",
                """4 large Potatoes, peeled & cut into Matchsticks Fried 
                    |Vegetable Oil Salt To Taste Optional Ketchup or Mayonnaise Dip""".trimMargin(),
                """To fry potatoes, start by rinsing them in cold water and patting them dry with a kitchen 
                    |towel or paper towel. Heat vegetable oil in a large deep fryer or deep fryer to 350°F(175°C).
                    | Add the potato matchsticks in small batches to the hot oil, making sure not to overfill the pan. 
                    | Fry for 3-4 minutes, or until golden brown and crispy. Remove the fries with a slotted spoon,
                    |  tongs, or paper towels to absorb any excess oil. Immediately after removing the potatoes from
                    |   the oil, season them with salt and toss to coat evenly. Repeat with the remaining potatoes.""".trimMargin(),
                R.drawable.french_fries,
                "25 min"
        )
    ))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)

        val recipeRecyclerView = binding.recipeRecyclerView
        recipeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val recipeAdapter = RecipeAdapter(requireContext(), recipeList)
        recipeRecyclerView.adapter = recipeAdapter

        binding.btnAddNewRecipe.setOnClickListener { addNewRecipeClicked(recipeAdapter) }

        return binding.root
    }

    private fun addNewRecipeClicked(recipeAdapter: RecipeAdapter) {
        val addRecipeFormBuilder = AlertDialog.Builder(requireContext())
        addRecipeFormBuilder.setTitle("Add new recipe")
        val addRecipeFormView = layoutInflater.inflate(R.layout.add_recipe_form, null)
        addRecipeFormBuilder.setView(addRecipeFormView)

        addRecipeFormBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        addRecipeFormBuilder.setPositiveButton("Save") { dialog, _ ->
            val newRecipeName = addRecipeFormView.findViewById<EditText>(R.id.newRecipeName).text.toString()
            val newRecipeDescription = addRecipeFormView.findViewById<EditText>(R.id.newRecipeDescription).text.toString()
            val newRecipeIngredients = addRecipeFormView.findViewById<EditText>(R.id.newRecipeIngredients).text.toString()
            val newRecipeInstructions = addRecipeFormView.findViewById<EditText>(R.id.newRecipeInstructions).text.toString()
            val newRecipeCookTime = addRecipeFormView.findViewById<EditText>(R.id.newRecipeCookTime).text.toString()
            recipeList.add(
                Recipe(
                    newRecipeName,
                    newRecipeDescription,
                    newRecipeIngredients,
                    newRecipeInstructions,
                    R.drawable.no_image,
                    newRecipeCookTime
                )
            )
            dialog.dismiss()
            recipeAdapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "Recipe for $newRecipeName added successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        val recipeDialog = addRecipeFormBuilder.create()
        recipeDialog.show()
    }
}