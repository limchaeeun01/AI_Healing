package com.chaeni__beam.ai_healing.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chaeni__beam.ai_healing.fragment.CalendarFragment

class FirstFragmentStateAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    private val pageCount = Int.MAX_VALUE
    val firstFragmentPosition = Int.MAX_VALUE / 2

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        val calendarFragment = CalendarFragment()
        calendarFragment.pageIndex = position
        return calendarFragment
    }

}