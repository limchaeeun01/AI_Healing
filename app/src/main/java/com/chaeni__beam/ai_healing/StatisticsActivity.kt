package com.chaeni__beam.ai_healing

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.chaeni__beam.ai_healing.databinding.ActivityCalendarBinding
import com.chaeni__beam.ai_healing.databinding.ActivityStatisticsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS

class StatisticsActivity : AppCompatActivity() {

    lateinit var binding : ActivityStatisticsBinding

    val entries1 = ArrayList<PieEntry>()
    val entries2 = ArrayList<PieEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBarChart1()
        setBarChart2()

        setStatisticsText()

    }

    fun setStatisticsText(){
        binding.sadnessText.text = entries1[0].value.toString() + "% -> " + entries2[0].value.toString()

        val textViews = mutableListOf<TextView>(
            binding.sadnessText,
            binding.angerText,
            binding.gentlenessText,
            binding.happinessText
        )

        for(i in 0..3){
            var text = ""
            when(i){
                0 -> text = "슬픔 : "
                1 -> text = "화남 : "
                2 -> text = "평온함 : "
                3 -> text = "행복 : "
            }
            text += entries1[i].value.toString() + "% -> " + entries2[i].value.toString()
            if(entries1[i].value > entries2[i].value){
                text += " (" + (entries1[i].value-entries2[i].value).toString() + "% 감소)"
            }else if(entries1[i].value < entries2[i].value){
                text += " (" + (entries2[i].value-entries1[i].value).toString() + "% 증가)"
            }else{
                text += "(유지)"
            }

            textViews[i].setText(text)
        }

    }

    private fun setBarChart1() {
        binding.pieChart1.setUsePercentValues(true)

        // data set

        entries1.add(PieEntry(41f, "슬픔"))
        entries1.add(PieEntry(32f, "화남"))
        entries1.add(PieEntry(7f, "평온함"))
        entries1.add(PieEntry(20f, "행복"))


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
            centerText = "7월"
            setEntryLabelColor(Color.BLACK)
            setCenterTextSize(20f)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }

    private fun setBarChart2() {
        binding.pieChart2.setUsePercentValues(true)

        // data set
        entries2.add(PieEntry(21f, "슬픔"))
        entries2.add(PieEntry(30f, "화남"))
        entries2.add(PieEntry(19f, "평온함"))
        entries2.add(PieEntry(30f, "행복"))


        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        colorsItems.add(Color.parseColor("#A0DDE0"))
        colorsItems.add(Color.parseColor("#FE8D6F"))
        colorsItems.add(Color.parseColor("#FDC453"))
        colorsItems.add(Color.parseColor("#F886A8"))

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
            centerText = "8월"
            setEntryLabelColor(Color.BLACK)
            setCenterTextSize(20f)
            animateY(1400, Easing.EaseInOutQuad)
            animate()
        }
    }
}