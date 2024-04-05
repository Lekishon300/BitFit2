package com.example.bitfit2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.app.Application
import android.util.Log
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

class UserInputFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_user__input, container, false)

        val buttonRecordItem=view.findViewById<Button>(R.id.record_button)
        val foodNameTV=view.findViewById<EditText>(R.id.food_nameET)
        val caloriesTV=view.findViewById<EditText>(R.id.calories_ET)
        buttonRecordItem.setOnClickListener{
            if(foodNameTV.text.isNotEmpty() && caloriesTV.text.isNotEmpty()){
                val foodName=foodNameTV.text.toString()
                val foodCalories=caloriesTV.text.toString().toInt()
                lifecycleScope.launch(Dispatchers.IO){
                    val foodEntity=FoodEntity(name=foodName,calories=foodCalories)
                    //(application as FoodApplication).db.FoodDao().deleteAll()
                    val foodApplication = requireActivity().application as FoodApplication
                    foodApplication.db.FoodDao().insert(foodEntity)

                }
            }else{
               // Toast.makeText(this,"Please enter valid food name and calories amount", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    companion object {
        fun newInstance():UserInputFragment{
            return UserInputFragment()
        }
    }
}