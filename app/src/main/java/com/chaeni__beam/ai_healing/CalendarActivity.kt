package com.chaeni__beam.ai_healing

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.chaeni__beam.ai_healing.Adapter.FirstFragmentStateAdapter
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

        binding.diaryBtn.setOnClickListener{
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }

        initView()

    }

    fun initView() {
        val firstFragmentStateAdapter
                = FirstFragmentStateAdapter(this)
        binding.calendarViewPager.adapter = firstFragmentStateAdapter
        binding.calendarViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.calendarViewPager.setCurrentItem(firstFragmentStateAdapter.firstFragmentPosition, false)
    }

}