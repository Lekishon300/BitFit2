package com.example.bitfit2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FoodsListFragment : Fragment() {
    private val foods = mutableListOf<FoodEntity>()
    private lateinit var adapter:FoodAdapter
    private lateinit var foodsRv:RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_foods_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        foodsRv = view.findViewById(R.id.foodsRV)
        foodsRv.layoutManager = layoutManager
        foodsRv.setHasFixedSize(true)
        adapter = FoodAdapter(foods)
        lifecycleScope.launch{
            val foodApplication = requireActivity().application as FoodApplication
            //foodApplication.db.FoodDao().deleteAll()
            foodApplication.db.FoodDao().getAll().collect(){databaseList->
                foods.clear()
                foods.addAll(databaseList)
                adapter.notifyDataSetChanged()

            }
        }
        foodsRv.adapter = adapter
        return view
    }

    companion object {
        fun newInstance():FoodsListFragment{
            return FoodsListFragment()
        }
    }
}