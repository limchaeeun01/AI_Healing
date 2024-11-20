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
import com.chaeni__beam.ai_healing.Data.DiaryData
import com.chaeni__beam.ai_healing.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class CalendarAdapter(
    val context: Context,
    val recyclerView: RecyclerView,
    val date: Date
) : RecyclerView.Adapter<CalendarAdapter.CalendarItemHolder>() {

    private val TAG = javaClass.simpleName
    var dataList: ArrayList<Int> = arrayListOf()
    var selectedPosition: Int = -1 // 선택된 위치를 저장하는 변수

    var furangCalendar: FurangCalendar = FurangCalendar(date)

    val diaryEntries = listOf(
        DiaryData("2024-07-01", 3, "기분 좋은 아침이었다."),           // 행복
        DiaryData("2024-07-02", 4, "차분한 하루를 보냈다."),            // 평온함
        DiaryData("2024-07-03", 2, "화나는 일이 많았다."),             // 화남
        DiaryData("2024-07-05", 1, "슬픈 소식을 듣고 마음이 무거웠다."), // 슬픔
        DiaryData("2024-07-06", 4, "평온하게 지냈다."),                 // 평온함
        DiaryData("2024-07-07", 4, "느긋하게 주말을 보냈다."),          // 평온함
        DiaryData("2024-07-08", 3, "일이 잘 풀려서 기분이 좋았다."),     // 행복
        DiaryData("2024-07-10", 1, "외로운 하루였다."),                 // 슬픔
        DiaryData("2024-07-11", 2, "상사와의 갈등으로 화가 났다."),     // 화남
        DiaryData("2024-07-13", 3, "친구와 즐거운 시간을 보냈다."),     // 행복
        DiaryData("2024-07-14", 4, "평온하게 하루를 마무리했다."),      // 평온함
        DiaryData("2024-07-15", 1, "갑자기 슬픈 감정이 밀려왔다."),     // 슬픔
        DiaryData("2024-07-17", 2, "교통사정으로 스트레스를 받았다."),  // 화남
        DiaryData("2024-07-19", 4, "하루 종일 평온했다."),              // 평온함
        DiaryData("2024-07-20", 3, "작은 성취를 이루었다."),            // 행복
        DiaryData("2024-07-22", 2, "짜증나는 일들이 많았다."),          // 화남
        DiaryData("2024-07-24", 3, "오랜만에 편안한 하루였다."),        // 행복
        DiaryData("2024-07-26", 1, "마음이 울적했다."),                 // 슬픔
        DiaryData("2024-07-28", 4, "조용히 시간을 보냈다."),            // 평온함
        DiaryData("2024-07-30", 3, "좋은 소식을 들었다."),             // 행복
        DiaryData("2024-07-31", 2, "더운 날씨에 화가 났다.") ,         // 화남

        DiaryData("2024-08-01", 3, "행복한 하루였다."),           // 행복
        DiaryData("2024-08-02", 1, "슬픈 일이 있었다."),           // 슬픔
        DiaryData("2024-08-04", 4, "평온한 하루였다."),           // 평온함
        DiaryData("2024-08-05", 3, "친구들과 즐거운 시간을 보냈다."), // 행복
        DiaryData("2024-08-06", 3, "좋은 날씨에 산책을 즐겼다."), // 행복
        DiaryData("2024-08-10", 3, "맛있는 음식을 먹고 행복했다."), // 행복
        DiaryData("2024-08-11", 2, "예상치 못한 일이 생겨 화가 났다."), // 화남
        DiaryData("2024-08-12", 4, "차분하게 하루를 보냈다."),   // 평온함
        DiaryData("2024-08-13", 3, "오래된 친구와의 재회로 행복했다."), // 행복
        DiaryData("2024-08-14", 1, "그리운 사람을 생각하며 슬펐다."), // 슬픔
        DiaryData("2024-08-16", 2, "짜증나는 일들이 많았다."),   // 화남
        DiaryData("2024-08-17", 4, "휴식을 취하며 평온함을 느꼈다."), // 평온함
        DiaryData("2024-08-18", 3, "작은 성취가 있어 기뻤다."),   // 행복
        DiaryData("2024-08-20", 3, "좋은 소식을 들어 행복했다."), // 행복
        DiaryData("2024-08-21", 2, "업무로 스트레스를 받았다."), // 화남
        DiaryData("2024-08-24", 1, "슬픈 영화를 보고 눈물이 났다."), // 슬픔
        DiaryData("2024-08-25", 2, "사소한 일에 화가 났다."),   // 화남
        DiaryData("2024-08-29", 1, "요즘 너무 피곤하고 힘들다."),   // 슬픔

        // 10월 일기
        DiaryData("2024-10-01", 4, "조용히 시간을 보내며 평온함을 느꼈다."),     // 평온함
        DiaryData("2024-10-02", 2, "회의 중 발생한 의견 충돌로 스트레스를 받았다."), // 화남
        DiaryData("2024-10-04", 3, "좋은 소식을 들으며 하루가 즐거웠다."),        // 행복
        DiaryData("2024-10-05", 4, "느긋하게 독서를 하며 평온한 시간을 보냈다."),  // 평온함
        DiaryData("2024-10-07", 1, "슬픈 영화를 보고 울적한 기분이 들었다."),      // 슬픔
        DiaryData("2024-10-09", 2, "혼잡한 교통 상황에 짜증이 났다."),            // 화남
        DiaryData("2024-10-10", 3, "좋은 날씨 덕분에 기분 좋은 산책을 했다."),      // 행복
        DiaryData("2024-10-12", 1, "친구와 다툰 후 마음이 무거웠다."),             // 슬픔
        DiaryData("2024-10-14", 4, "오랜만에 정리하며 마음이 차분해졌다."),        // 평온함
        DiaryData("2024-10-15", 3, "작은 성공을 이루고 뿌듯한 하루를 보냈다."),    // 행복
        DiaryData("2024-10-18", 2, "예상치 못한 상황에 화가 났다."),               // 화남
        DiaryData("2024-10-20", 4, "느긋하게 하루를 보내며 평화를 느꼈다."),       // 평온함
        DiaryData("2024-10-22", 1, "슬픈 소식을 듣고 하루 종일 마음이 무거웠다."), // 슬픔
        DiaryData("2024-10-25", 3, "좋은 친구와 시간을 보내며 웃을 수 있었다."),   // 행복
        DiaryData("2024-10-28", 2, "사소한 일에 스트레스를 받았다."),              // 화남

    // 11월 일기
        DiaryData("2024-11-01", 3, "기분 좋은 시작으로 하루가 행복했다."),        // 행복
        DiaryData("2024-11-03", 4, "차분하게 시간을 보내며 마음이 평온했다."),     // 평온함
        DiaryData("2024-11-05", 1, "갑작스러운 슬픈 일로 힘든 하루였다."),         // 슬픔
        DiaryData("2024-11-07", 2, "업무에서 실수를 해서 스트레스를 받았다."),     // 화남
        DiaryData("2024-11-08", 4, "느긋하게 음악을 들으며 평온함을 느꼈다."),     // 평온함
        DiaryData("2024-11-10", 3, "좋은 날씨에 산책하며 행복감을 느꼈다."),        // 행복
        DiaryData("2024-11-12", 1, "외로움이 밀려와 우울한 하루를 보냈다."),       // 슬픔
        DiaryData("2024-11-14", 2, "계획대로 일이 풀리지 않아 짜증이 났다."),      // 화남
        DiaryData("2024-11-16", 3, "작은 성취를 이루며 행복한 하루를 보냈다."),    // 행복
        DiaryData("2024-11-18", 4, "조용히 독서를 하며 마음이 평화로웠다."),       // 평온함
        DiaryData("2024-11-20", 1, "오랜만에 울적한 기분이 밀려왔다."),            // 슬픔





    )


    init {
        furangCalendar.initBaseCalendar()
        dataList = furangCalendar.dateList
    }

    interface ItemClick {
        fun onClick(view: View, position: Int, date: Int)
    }

    var itemClick: ItemClick? = null

    fun getEmotionIcon(emotionId: Int): Int? {
        return when (emotionId) {
            1 -> R.drawable.sadness    // 슬픔
            2 -> R.drawable.anger       // 화남
            3 -> R.drawable.happiness   // 행복
            4 -> R.drawable.gentleness    // 평온함
            else -> null                // 기본 아이콘 설정 안함
        }
    }

    fun getEmotionForDate(date: String): Int {
        val diaryEntry = diaryEntries.find { it.date == date }
        return diaryEntry?.emotionId ?: -1 // 해당 날짜의 감정 ID가 없으면 -1 반환
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: CalendarItemHolder, position: Int) {
        val itemHeight = context.resources.getDimensionPixelSize(R.dimen.item_height) // 고정된 높이값 사용
        holder.itemView.layoutParams.height = itemHeight

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
            val currentYear = furangCalendar.calendar.get(Calendar.YEAR)
            val today = Calendar.getInstance()
            val todayDay = today.get(Calendar.DAY_OF_MONTH)
            val todayMonth = today.get(Calendar.MONTH) + 1 // 월은 0부터 시작하므로 +1

            // 현재 월의 1일 이전, 현재 월의 마지막일 이후 값의 텍스트를 회색 처리
            if (position < firstDateIndex || position > lastDateIndex) {
                itemCalendarDateText.setTextColor(context.getColor(R.color.gray))  // 회색 처리
                itemCalendarEmotionImg.visibility = View.GONE // 회색 글씨일 경우 아이콘을 숨김
            } else {
                itemCalendarDateText.setTextColor(context.getColor(R.color.black))  // 기본 색상

                // 날짜를 yyyy-MM-dd 형식으로 포맷
                val date = String.format("%04d-%02d-%02d", currentYear, currentMonth, data)

                // 날짜에 해당하는 감정 ID를 가져옴 (임시로 userId는 "userId"로 설정)
                val emotionId = getEmotionForDate(date)
                val emotionIcon = getEmotionIcon(emotionId)

                // 아이콘 설정
                if (emotionIcon != null) {
                    itemCalendarEmotionImg.setImageResource(emotionIcon)
                    itemCalendarEmotionImg.visibility = View.VISIBLE
                } else {
                    itemCalendarEmotionImg.visibility = View.GONE // 아이콘을 설정하지 않고 뷰를 숨김
                }
            }

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
        }


    }
}