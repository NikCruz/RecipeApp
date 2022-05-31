package com.example.recipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.RecyclerviewItemBinding
import com.example.recipeapp.model.FoodData

class FoodAdapters(val context: Context,val foodList:ArrayList<FoodData>): RecyclerView.Adapter<FoodAdapters.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food=foodList[position]
        Glide
            .with(context)
            .load(food.image)
            .centerCrop()
            .into(holder.ivFood)
        holder.tvName.text=food.name
        holder.tvIngredients.text=food.ingredients
        holder.tvRecipe.text=food.recipe


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    inner class ViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivFood=binding.ivFood
        val tvName=binding.tvName
        val tvIngredients=binding.tvIngredients
        val tvRecipe=binding.tvRecipe



    }



}