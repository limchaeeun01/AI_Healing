package com.chaeni__beam.ai_healing.fragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.chaeni__beam.ai_healing.Adapter.CalendarAdapter
import com.chaeni__beam.ai_healing.CalendarActivity
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.FragmentCalendarBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!


    private val TAG = javaClass.simpleName
    lateinit var mContext: Context

    var pageIndex = 0
    lateinit var currentDate: Date

    lateinit var calendarAdapter: CalendarAdapter

    companion object {
        var instance: CalendarFragment? = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CalendarActivity) {
            mContext = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun initView() {
        pageIndex -= (Int.MAX_VALUE / 2)
        Log.e(TAG, "Calender Index: $pageIndex")
        // 날짜 적용
        val date = Calendar.getInstance().run {
            add(Calendar.MONTH, pageIndex)
            time
        }
        currentDate = date
        Log.e(TAG, "$date")

        // 포맷 적용
        val datetime: String = SimpleDateFormat(
            mContext.getString(R.string.calendar_year_month_format),
            Locale.KOREA
        ).format(date.time)

        binding.calendarYearMonthText.text = datetime

        // GridLayoutManager를 설정하여 RecyclerView가 격자로 표시되도록 합니다.
        val gridLayoutManager = GridLayoutManager(mContext, 7) // 7은 요일 개수
        binding.calendarView.layoutManager = gridLayoutManager

        //클릭 이벤트
        calendarAdapter = CalendarAdapter(mContext, binding.calendarLayout, currentDate).apply {
            itemClick = object : CalendarAdapter.ItemClick {
                override fun onClick(view: View, position: Int, date: Int) {
                    Toast.makeText(mContext, "Clicked date: $date", Toast.LENGTH_SHORT).show()

                    // 기존 스타일을 초기화 (필요한 경우)
                    calendarAdapter.notifyDataSetChanged()

                    // 클릭된 날짜의 텍스트를 굵게 설정
                    view.findViewById<TextView>(R.id.item_calendar_date_text)?.apply {
                        setTypeface(typeface, Typeface.BOLD)
                    }
                }
            }
        }

        // CalendarAdapter를 RecyclerView에 연결합니다.
        binding.calendarView.adapter = calendarAdapter
    }



    override fun onDestroy() {
        super.onDestroy()
        instance = null
        _binding = null
    }


}