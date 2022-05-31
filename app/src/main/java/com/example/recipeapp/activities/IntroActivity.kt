package com.example.recipeapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.databinding.ActivityIntroBinding


class IntroActivity : AppCompatActivity() {

    private var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.btnSignUpIntro?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

            binding?.btnSignInIntro?.setOnClickListener {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
        }

    }
}
