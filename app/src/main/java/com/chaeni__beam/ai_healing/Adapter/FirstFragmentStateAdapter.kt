package com.chaeni__beam.ai_healing.Adapter

import com.chaeni__beam.ai_healing.fragment.CalendarFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FirstFragmentStateAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    val firstFragmentPosition = Int.MAX_VALUE / 2

    override fun getItemCount(): Int = pageCount

    override fun createFragment(position: Int): Fragment {
        val calendarFragment = CalendarFragment()
        calendarFragment.pageIndex = position - firstFragmentPosition // center position adjustment
        return calendarFragment
    }
}
