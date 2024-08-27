package com.chaeni__beam.ai_healing.UI

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chaeni__beam.ai_healing.CalendarActivity
import com.chaeni__beam.ai_healing.ContentSettingActivity
import com.chaeni__beam.ai_healing.ServiceActivity
import com.chaeni__beam.ai_healing.StatisticsActivity
import com.chaeni__beam.ai_healing.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    lateinit var binding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.calendarBtn.setOnClickListener{
            val intent = Intent(requireContext(), CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.statisticsBtn.setOnClickListener{
            val intent = Intent(requireContext(), StatisticsActivity::class.java)
            startActivity(intent)
        }

        binding.serviceStatisticsBtn.setOnClickListener{
            val intent = Intent(requireContext(), ServiceActivity::class.java)
            startActivity(intent)
        }





        return binding.root
    }


}