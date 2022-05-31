package com.example.recipeapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapters.FoodAdapters
import com.example.recipeapp.databinding.ActivityMainBinding
import com.example.recipeapp.firebase.FirestoreClass
import com.example.recipeapp.model.FoodData

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        FirestoreClass().getFoodList(this)

    }

    fun onGettingFoodList(foodList: ArrayList<FoodData>) {
        binding?.rvRecipe?.layoutManager=LinearLayoutManager(this)
        val adapter=FoodAdapters(this,foodList)
        binding?.rvRecipe?.adapter=adapter



    }
}