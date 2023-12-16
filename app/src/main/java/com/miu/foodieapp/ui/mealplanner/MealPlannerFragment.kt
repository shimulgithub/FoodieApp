package com.miu.foodieapp.ui.mealplanner

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.miu.foodieapp.R
import com.miu.foodieapp.adapter.MealPlanAdapter
import com.miu.foodieapp.databinding.FragmentMealPlannerBinding
import com.miu.foodieapp.model.MealPlan
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

class MealPlannerFragment : Fragment() {

    private lateinit var binding: FragmentMealPlannerBinding

    //test data
    private val today: LocalDate = LocalDate.now()
    private val mealPlanList = ArrayList<MealPlan>(listOf(
        MealPlan(
            "Fruits",
            "Grilled Chicken",
            "Vegetarian",
            today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        ),
        MealPlan(
            "Greek Parfait",
            "Sandwich",
            "Chicken with Broccoli",
            today.with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY))
        ),
        MealPlan(
            "Poached Egg",
            "Spinach Wrap",
            "Scampi with Pasta",
            today.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
        ),
        MealPlan(
            "Pancakes with apple juice",
            "Quinoa Salad",
            "Grilled Steak",
            today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))
        )
    ))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealPlannerBinding.inflate(layoutInflater)

        val recyclerView = binding.mealPlannerRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val mealPlanAdapter = MealPlanAdapter(mealPlanList)
        recyclerView.adapter = mealPlanAdapter

        binding.btnAddNewMealPlan.setOnClickListener {
            addNewMealPlanClicked(mealPlanAdapter)
        }

        return binding.root
    }

    private fun addNewMealPlanClicked(mealPlanAdapter: MealPlanAdapter) {
        val addMealPlanFormBuilder = AlertDialog.Builder(requireContext())
        addMealPlanFormBuilder.setTitle("New meal plan")
        val addMealPlanFormView = layoutInflater.inflate(R.layout.add_meal_plan_form, null)
        addMealPlanFormBuilder.setView(addMealPlanFormView)

        addMealPlanFormBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        addMealPlanFormBuilder.setPositiveButton("Save") { dialog, _ ->
            val breakfast = addMealPlanFormView.findViewById<EditText>(R.id.addMealPlanBreakfast).text.toString()
            val lunch = addMealPlanFormView.findViewById<EditText>(R.id.addMealPlanLunch).text.toString()
            val dinner = addMealPlanFormView.findViewById<EditText>(R.id.addMealPlanDinner).text.toString()

            val selectedDateMillis = addMealPlanFormView.findViewById<CalendarView>(R.id.addMealPlanCalendarView).date
            val selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            mealPlanList.add(MealPlan(
                breakfast,
                lunch,
                dinner,
                selectedDate
            ))

            mealPlanAdapter.notifyDataSetChanged()
            dialog.dismiss()
            Toast.makeText(
                requireContext(),
                "Meal plan added successfully",
                Toast.LENGTH_SHORT
            ).show()
        }

        val mealPlanDialog = addMealPlanFormBuilder.create()
        mealPlanDialog.show()
    }
}