package com.chaeni__beam.ai_healing.content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityDiaryBinding
import com.chaeni__beam.ai_healing.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {

    lateinit var binding : ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}