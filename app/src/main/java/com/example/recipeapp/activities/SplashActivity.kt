package com.example.recipeapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.recipeapp.databinding.ActivitySplashBinding
import com.example.recipeapp.firebase.FirestoreClass

class SplashActivity : AppCompatActivity() {
    private var binding: ActivitySplashBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        Handler().postDelayed({

            var currentUserID = FirestoreClass().getCurrentUserID()
            if (currentUserID.isNotEmpty()){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }else {
                startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            }
            finish()
        },2500)
    }
}