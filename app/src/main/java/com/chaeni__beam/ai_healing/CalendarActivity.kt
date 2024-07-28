package com.chaeni__beam.ai_healing

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import com.chaeni__beam.ai_healing.databinding.ActivityContentSettingBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity() {

    lateinit var binding : ActivityCalendarBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        binding.dateTextView.setText(current.format(formatter))

        binding.calendarView.setOnDateChangeListener{view, year, month, dayOfMonth ->
            binding.dateTextView.setText(String.format("%d/%02d/%02d", year, month + 1, dayOfMonth))

            if(dayOfMonth == 1) {
                binding.text.setText("행복 : 50%\n평온 : 50%")
            }else if(dayOfMonth == 5){
                binding.text.setText("슬픔 : 50%\n평온 : 25%\n무기력 : 25%")
            }else if(dayOfMonth == 7){
                binding.text.setText("무기력 : 100%")
            }else if(dayOfMonth == 10){
                binding.text.setText("슬픔 : 100%")
            }else{
                binding.text.setText("데이터 없음")
            }

        }

    }
}