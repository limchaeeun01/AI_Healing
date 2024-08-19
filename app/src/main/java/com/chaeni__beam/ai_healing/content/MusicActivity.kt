package com.chaeni__beam.ai_healing.content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {

    lateinit var binding : ActivityMusicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}