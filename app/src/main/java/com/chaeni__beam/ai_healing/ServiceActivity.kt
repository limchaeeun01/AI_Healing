package com.chaeni__beam.ai_healing

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.chaeni__beam.ai_healing.databinding.ActivityFoodRankBinding
import com.chaeni__beam.ai_healing.databinding.ActivityServiceBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding

    val entries1 = ArrayList<PieEntry>()
    val entries2 = ArrayList<PieEntry>()

    private lateinit var buttons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttons = listOf(
            binding.movieBtn,
            binding.musicBtn,
            binding.bookBtn,
            binding.gameBtn,
            binding.foodBtn
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                // 클릭된 버튼은 선택 상태로 설정
                button.isSelected = true

                // 다른 버튼들은 선택 상태 해제
                buttons.filter { it != button }.forEach {
                    it.isSelected = false
                }

                val tag = button.tag as? String
                tag?.let {
                    setBarChart2(it) // 매개변수로 tag를 전달하여 함수 호출
                }
            }
        }

        setBarChart1()
    }

    private fun setBarChart1() {
        binding.pieChart1.setUsePercentValues(true)

        entries1.add(PieEntry(21f, "음식"))
        entries1.add(PieEntry(32f, "영화"))
        entries1.add(PieEntry(7f, "음악"))
        entries1.add(PieEntry(20f, "책"))
        entries1.add(PieEntry(20f, "게임"))

        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        colorsItems.add(Color.parseColor("#A0DDE0"))
        colorsItems.add(Color.parseColor("#FE8D6F"))
        colorsItems.add(Color.parseColor("#FDC453"))
        colorsItems.add(Color.parseColor("#F886A8"))

        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries1, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 18f
        }

        val pieData = PieData(pieDataSet)
        binding.pieChart1.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            setEntryLabelColor(Color.BLACK)
            setCenterTextSize(20f)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }

    private fun setBarChart2(tag : String) {
        binding.pieChart2.setUsePercentValues(true)

        when (tag) {
            "movie" -> {
                entries2.clear() // 기존 항목을 삭제
                entries2.add(PieEntry(21f, "로맨스"))
                entries2.add(PieEntry(10f, "스릴러"))
                entries2.add(PieEntry(30f, "애니메이션"))
                entries2.add(PieEntry(21f, "판타지"))
                entries2.add(PieEntry(6f, "코미디"))
                entries2.add(PieEntry(12f, "SF"))
            }
            "music" -> {
                entries2.clear() // 기존 항목을 삭제
                entries2.add(PieEntry(21f, "K-POP"))
                entries2.add(PieEntry(10f, "힙합"))
                entries2.add(PieEntry(30f, "발라드"))
                entries2.add(PieEntry(21f, "J-POP"))
                entries2.add(PieEntry(18f, "OST"))
            }
            "book" -> {
                entries2.clear() // 기존 항목을 삭제
                entries2.add(PieEntry(21f, "소설"))
                entries2.add(PieEntry(28f, "시/에세이"))
                entries2.add(PieEntry(30f, "인문"))
                entries2.add(PieEntry(21f, "자기계발"))
            }
            "game" -> {
                entries2.clear() // 기존 항목을 삭제
                entries2.add(PieEntry(21f, "시뮬레이션"))
                entries2.add(PieEntry(28f, "어드벤처"))
                entries2.add(PieEntry(30f, "롤플레잉"))
                entries2.add(PieEntry(21f, "전략"))
            }
            "food" -> {
                entries2.clear() // 기존 항목을 삭제
                entries2.add(PieEntry(21f, "한식"))
                entries2.add(PieEntry(10f, "양식"))
                entries2.add(PieEntry(30f, "중식"))
                entries2.add(PieEntry(21f, "버거"))
                entries2.add(PieEntry(6f, "디저트"))
                entries2.add(PieEntry(12f, "커피"))
            }
        }

        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        colorsItems.add(Color.parseColor("#A0DDE0"))
        colorsItems.add(Color.parseColor("#FE8D6F"))
        colorsItems.add(Color.parseColor("#FDC453"))
        colorsItems.add(Color.parseColor("#F886A8"))
        colorsItems.add(Color.parseColor("#BA9AB9"))

        colorsItems.add(ColorTemplate.getHoloBlue())

        val pieDataSet = PieDataSet(entries2, "")
        pieDataSet.apply {
            colors = colorsItems
            valueTextColor = Color.BLACK
            valueTextSize = 18f
        }

        val pieData = PieData(pieDataSet)
        binding.pieChart2.apply {
            data = pieData
            description.isEnabled = false
            isRotationEnabled = false
            centerText = when(tag) {
                "movie" -> "영화"
                "music" -> "음악"
                "book" -> "책"
                "game" -> "게임"
                "food" -> "음식"
                else -> "기타"
            }
            setEntryLabelColor(Color.BLACK)
            setCenterTextSize(20f)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }
}
