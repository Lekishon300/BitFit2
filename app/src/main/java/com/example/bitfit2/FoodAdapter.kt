package com.example.bitfit2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val foods:MutableList<FoodEntity>): RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var nameTextView: TextView =itemView.findViewById(R.id.food_nameItem)
        var caloriesNumberTextView: TextView =itemView.findViewById(R.id.calories_numberItem)
        var caloriesWordTextView: TextView =itemView.findViewById(R.id.calories_wordItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.food_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        val food=foods[position]
        holder.nameTextView.text=food.name
        holder.caloriesNumberTextView.text=food.calories.toString()
    }

    override fun getItemCount(): Int {
        return foods.size
    }

}