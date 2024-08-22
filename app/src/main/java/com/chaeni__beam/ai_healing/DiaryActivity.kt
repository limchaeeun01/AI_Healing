package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import com.chaeni__beam.ai_healing.databinding.ActivityDiaryBinding
import com.chaeni__beam.ai_healing.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiaryActivity : AppCompatActivity() {

    lateinit var binding : ActivityDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        binding.dateText.text = currentDate

    }
}