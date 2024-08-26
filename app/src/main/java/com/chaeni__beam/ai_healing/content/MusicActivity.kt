package com.chaeni__beam.ai_healing.content

import android.graphics.Color
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

        window.statusBarColor = Color.parseColor("#18212E")  // 상태바 색상 변경
        window.navigationBarColor = Color.parseColor("#18212E")  // 네비게이션바 색상 변경
    }
}