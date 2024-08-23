package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import com.chaeni__beam.ai_healing.databinding.ActivityStatisticsBinding

class StatisticsActivity : AppCompatActivity() {

    lateinit var binding : ActivityStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}