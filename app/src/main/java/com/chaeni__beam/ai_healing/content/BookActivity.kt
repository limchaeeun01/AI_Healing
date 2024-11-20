package com.chaeni__beam.ai_healing.content

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityBookBinding
import com.chaeni__beam.ai_healing.databinding.ActivityLoginBinding

class BookActivity : AppCompatActivity() {

    lateinit var binding : ActivityBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FFF9E4")  // 상태바 색상 변경
        window.navigationBarColor = Color.parseColor("#FFF9E4")  // 네비게이션바 색상 변경

        binding.book1.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://search.shopping.naver.com/book/catalog/32465494978?query=%EB%82%98%EB%8A%94%20%EB%82%98%EB%A1%9C%20%EC%82%B4%EA%B8%B0%EB%A1%9C%20%ED%96%88%EB%8B%A4&NaPm=ct%3Dm0fborkg%7Cci%3D60fa6bd87cd19bb5caaf1cef284eab86e85d7f15%7Ctr%3Dboksl%7Csn%3D95694%7Chk%3D1910c7d1cdc0015155115f694d330766541388cd")
            }
            startActivity(intent)
        }
    }
}