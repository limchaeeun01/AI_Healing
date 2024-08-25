package com.chaeni__beam.ai_healing.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chaeni__beam.ai_healing.fragment.CalendarFragment
import java.util.Date

class FirstFragmentStateAdapter(
    fragmentActivity: FragmentActivity,
    private val dates: List<Date>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment.newInstance(dates[position])
    }

    override fun getItemCount(): Int = dates.size
}
