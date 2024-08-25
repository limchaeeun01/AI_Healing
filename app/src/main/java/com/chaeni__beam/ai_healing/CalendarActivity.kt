package com.chaeni__beam.ai_healing

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.chaeni__beam.ai_healing.Adapter.FirstFragmentStateAdapter
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.Calendar

class CalendarActivity : FragmentActivity() {

    private lateinit var binding: ActivityCalendarBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        initView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initView() {
        // 현재 날짜를 기준으로 한 연도의 시작과 끝 날짜를 설정
        val startDate = LocalDate.now().minusMonths(6).withDayOfMonth(1).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }
        val endDate = LocalDate.now().plusMonths(6).withDayOfMonth(
            LocalDate.now().plusMonths(6).withDayOfMonth(1).plusMonths(1).minusDays(1).dayOfMonth
        ).atStartOfDay(ZoneId.systemDefault()).toInstant().let { Date.from(it) }

        val dates = generateDateRange(startDate, endDate)

        val firstFragmentStateAdapter = FirstFragmentStateAdapter(this, dates)
        binding.calendarViewPager.adapter = firstFragmentStateAdapter
        binding.calendarViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.calendarViewPager.setCurrentItem(dates.size / 2, false)
    }

    // 날짜 범위를 생성하는 유틸 함수
    private fun generateDateRange(startDate: Date, endDate: Date): List<Date> {
        val dates = mutableListOf<Date>()
        val startCalendar = Calendar.getInstance().apply { time = startDate }
        val endCalendar = Calendar.getInstance().apply { time = endDate }

        while (startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
            dates.add(startCalendar.time)
            startCalendar.add(Calendar.MONTH, 1) // 월 단위로 증가
        }

        return dates
    }
}
