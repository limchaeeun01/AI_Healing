package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.Data.Emotion
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import com.chaeni__beam.ai_healing.databinding.ActivityDiaryBinding
import com.chaeni__beam.ai_healing.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryActivity : AppCompatActivity() {

    lateinit var binding : ActivityDiaryBinding

    private var emotion: String = ""

    val emotions = listOf(
        Emotion("sadness", R.drawable.sadness, "슬퍼요"),
        Emotion("happiness", R.drawable.happiness, "행복해요"),
        Emotion("anger", R.drawable.anger, "화나요"),
        Emotion("gentleness", R.drawable.gentleness, "평온해요")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emotion = intent.getStringExtra("emotion").toString()

        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        binding.dateText.text = currentDate

        setEmotion()

        binding.okBtn.setOnClickListener{
            finish()
        }

    }

    private fun setEmotion() {
        // 감정에 맞는 Emotion 객체 찾기
        val emotionData = emotions.find { it.id == emotion }

        if (emotionData != null) {
            // emotionData가 null이 아닐 경우에만 이미지와 문구 설정
            binding.emotionImage.setImageResource(emotionData.imageResId)
            binding.emotionText.text = emotionData.text
        } else {
            // 감정 데이터가 없는 경우 기본 처리
            binding.emotionImage.setImageResource(R.drawable.grey_background) // 기본 이미지 설정
            binding.emotionText.text = "감정 정보가 없습니다."
        }
    }
}