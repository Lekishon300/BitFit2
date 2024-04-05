package com.example.bitfit2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private val foods = mutableListOf<FoodEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_dashboard, container, false)
        val average_calET= view.findViewById<EditText>(R.id.average_calET)
        val min_CalET= view.findViewById<EditText>(R.id.min_CalET)
        val max_CalET = view.findViewById<EditText>(R.id.max_CalET)
        val foods = mutableListOf<FoodEntity>()
        var maxCalories = Int.MIN_VALUE // Initialize maxCalories with the smallest possible integer value
        var minCalories = Int.MAX_VALUE //
        lifecycleScope.launch{
            Log.e("FOODS Debugging", "launching begins")
            val foodApplication = requireActivity().application as FoodApplication
            var  sum=0
            //var average_calET = view.findViewById<EditText>(R.id.average_calET)
            foodApplication.db.FoodDao().getAll().collect(){databaseList->
                foods.clear()
                foods.addAll(databaseList)
                for (food in foods) {
                    food.calories?.toInt()?.let {
                        sum += it
                        maxCalories = maxOf(maxCalories, it)
                        minCalories = minOf(minCalories, it)
                    }
                    Log.e("FOODS Debugging", "${food.name} this is the name" )
                }
                val averageCalories =  sum / foods.size
                Log.e("FOODS Debugging", "Total calories: $sum")
                Log.e("FOODS Debugging", "max calories: $maxCalories")
                Log.e("FOODS Debugging", "min calories: $minCalories")
                Log.e("FOODS Debugging", "average calories: $averageCalories")
                max_CalET.setText(maxCalories.toString())
                min_CalET.setText(minCalories.toString())
                average_calET.setText(averageCalories.toString())

            }
        }
        return view
    }
    companion object {
        fun newInstance():DashboardFragment{
            return DashboardFragment()
        }
    }
}