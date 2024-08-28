package com.chaeni__beam.ai_healing.content

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.R

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        window.statusBarColor = Color.parseColor("#FFF9E4")  // 상태바 색상 변경
        window.navigationBarColor = Color.parseColor("#FFF9E4")  // 네비게이션바 색상 변경
    }
}