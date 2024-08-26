package com.chaeni__beam.ai_healing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaeni__beam.ai_healing.Adapter.CalendarAdapter
import com.chaeni__beam.ai_healing.databinding.FragmentCalendarBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_DATE = "date"

        fun newInstance(date: Date): CalendarFragment {
            val fragment = CalendarFragment()
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        val date = arguments?.getSerializable(ARG_DATE) as? Date
        date?.let {
            initView(it)
        }
        return binding.root
    }

    private fun initView(date: Date) {
        // 날짜를 텍스트로 표시
        binding.calendarYearMonthText.text = SimpleDateFormat("yyyy년 MM월", Locale.getDefault()).format(date)

        // RecyclerView 초기화
        val recyclerView: RecyclerView = binding.calendarView
        recyclerView.layoutManager = GridLayoutManager(context, 7)

        context?.let { ctx ->
            val calendarAdapter = CalendarAdapter(ctx, recyclerView, date)
            recyclerView.adapter = calendarAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}