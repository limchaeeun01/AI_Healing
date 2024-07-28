package com.chaeni__beam.ai_healing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityContentSettingBinding
import com.chaeni__beam.ai_healing.databinding.ActivityMainBinding

class ContentSettingActivity : AppCompatActivity() {

    lateinit var binding : ActivityContentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }
    }
}