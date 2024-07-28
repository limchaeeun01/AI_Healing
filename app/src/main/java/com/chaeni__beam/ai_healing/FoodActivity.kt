package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.databinding.ActivityLoginBinding

class FoodActivity : AppCompatActivity() {

    lateinit var binding : ActivityFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }
    }
}