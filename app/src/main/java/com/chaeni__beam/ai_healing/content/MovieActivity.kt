package com.chaeni__beam.ai_healing.content

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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

        window.statusBarColor = Color.parseColor("#2E1818")  // 상태바 색상 변경
        window.navigationBarColor = Color.parseColor("#2E1818")  // 네비게이션바 색상 변경

        binding.movie1.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://pedia.watcha.com/ko-KR/contents/mOgBBKY")
            }
            startActivity(intent)
        }
    }
}