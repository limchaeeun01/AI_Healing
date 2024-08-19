package com.chaeni__beam.ai_healing.content

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityContentBinding
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.food.FoodActivity

class ContentActivity : AppCompatActivity() {

    lateinit var binding : ActivityContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }

        binding.musicRcm.setOnClickListener{
            val intent = Intent(this, MusicActivity::class.java)
            startActivity(intent)
        }
    }
}