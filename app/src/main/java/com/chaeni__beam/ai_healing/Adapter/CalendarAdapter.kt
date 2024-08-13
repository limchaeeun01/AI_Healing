package com.chaeni__beam.ai_healing.Adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaeni__beam.ai_healing.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarAdapter(
    val context: Context,
    val calendarLayout: LinearLayout,
    val date: Date
) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()
    var selectedPosition: Int = -1 // 선택된 위치를 저장하는 변수

    var furangCalendar: FurangCalendar = FurangCalendar(date)

    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int, date: Int)
    }

    var itemClick: ItemClick? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        val h = calendarLayout.height / 6
        holder.itemView.layoutParams.height = h

        holder.bind(dataList[position], position, context)

        holder.itemView.setOnClickListener { v ->
            selectedPosition = position // 클릭된 위치 저장
            notifyDataSetChanged() // 모든 아이템을 다시 그리게 함
            itemClick?.onClick(v, position, dataList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_calendar, parent, false)
        return CalendarItemHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    inner class CalendarItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var itemCalendarDateText: TextView = itemView!!.findViewById(R.id.item_calendar_date_text)
        var itemCalendarEmotionImg: ImageView = itemView!!.findViewById(R.id.item_calendar_emotion_img)

        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(data: Int, position: Int, context: Context) {
            val firstDateIndex = furangCalendar.prevTail
            val lastDateIndex = dataList.size - furangCalendar.nextHead - 1

            itemCalendarDateText.text = data.toString()

            val currentMonth = furangCalendar.getCurrentMonth()
            val today = Calendar.getInstance()
            val todayDay = today.get(Calendar.DAY_OF_MONTH)
            val todayMonth = today.get(Calendar.MONTH) + 1 // 월은 0부터 시작하므로 +1

            // 선택된 날짜 처리
            if (position == selectedPosition) {
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                itemCalendarDateText.background = context.getDrawable(R.drawable.circle_background)
            } else if (data == todayDay && todayMonth == currentMonth && selectedPosition == -1) {
                // 오늘 날짜 처리 (현재 월에서만 적용)
                itemCalendarDateText.setTypeface(itemCalendarDateText.typeface, Typeface.BOLD)
                itemCalendarDateText.background = context.getDrawable(R.drawable.circle_background)
            } else {
                // 기본 상태
                itemCalendarDateText.setTypeface(null, Typeface.NORMAL)
                itemCalendarDateText.background = null
            }

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색 처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextColor(context.getColor(R.color.gray))  // 회색 처리
            } else {
                itemCalendarDateText.setTextColor(context.getColor(R.color.black))  // 기본 색상
                Glide.with(itemView).load(R.drawable.happiness).into(itemCalendarEmotionImg)
            }
        }
    }
}
