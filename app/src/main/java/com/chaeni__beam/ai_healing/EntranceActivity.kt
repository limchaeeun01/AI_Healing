package com.chaeni__beam.ai_healing

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chaeni__beam.ai_healing.Adapter.Emotion
import com.chaeni__beam.ai_healing.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    private var currentEmotion: String = ""

    private var selectEmotion: String = ""

    val emotions = listOf(
        Emotion("sadness", R.drawable.sadness, "슬퍼요"),
        Emotion("apathy", R.drawable.apathy, "무기력해요"),
        Emotion("happiness", R.drawable.happiness, "행복해요"),
        Emotion("anger", R.drawable.anger, "화나요"),
        Emotion("gentleness", R.drawable.gentleness, "평온해요")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentEmotion = intent.getStringExtra("emotion").toString()

        setCurrentEmotion()
        setSelectEmotion()


        // 버튼 클릭 리스너 설정
        val clickListener = { emotion: Emotion ->
            selectEmotion = emotion.id
            updateButtonBackgrounds()
            btnActivation()
        }

        // 버튼에 클릭 리스너 할당
        val filteredEmotions = emotions.filter { it.id != currentEmotion }
        binding.emotion1.setOnClickListener { clickListener(filteredEmotions[0]) }
        binding.emotion2.setOnClickListener { clickListener(filteredEmotions[1]) }
        binding.emotion3.setOnClickListener { clickListener(filteredEmotions[2]) }
        binding.emotion4.setOnClickListener { clickListener(filteredEmotions[3]) }

        binding.nextBtn.setOnClickListener {
            if (selectEmotion != "") {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("emotion", selectEmotion)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
                finish() // 현재 액티비티를 종료
            }
        }

    }

    fun setCurrentEmotion(){
        val currentEmotionClass = emotions.find { it.id == currentEmotion }
        binding.currentImage.setImageResource(currentEmotionClass!!.imageResId);
        binding.currentText.setText(currentEmotionClass.text);
    }

    fun setSelectEmotion(){
        val emotionImageList = listOf<ImageView>(
            binding.emotion1Image,
            binding.emotion2Image,
            binding.emotion3Image,
            binding.emotion4Image
        )
        val emotionTextList = listOf<TextView>(
            binding.emotion1Text,
            binding.emotion2Text,
            binding.emotion3Text,
            binding.emotion4Text
        )

        val emotionLayouts = listOf(
            binding.emotion1,
            binding.emotion2,
            binding.emotion3,
            binding.emotion4
        )

        val filteredEmotions = emotions.filter { it.id != currentEmotion }

        for(i in 0..3){
            emotionImageList[i].setImageResource(filteredEmotions[i].imageResId)
            emotionTextList[i].setText(filteredEmotions[i].text)
        }
    }

    private fun btnActivation(){
        if(selectEmotion.isNotEmpty()){
            binding.nextBtn.setBackgroundResource(R.drawable.button_rounded_corner_rectangle)
        }else{
            binding.nextBtn.setBackgroundResource(R.drawable.grey_button_rounded_corner_rectangle)
        }
    }

    private fun updateButtonBackgrounds() {
        // 버튼 배경 업데이트
        val filteredEmotions = emotions.filter { it.id != currentEmotion }
        binding.emotion1.setBackgroundResource(
            if (selectEmotion == filteredEmotions[0].id) R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.emotion2.setBackgroundResource(
            if (selectEmotion == filteredEmotions[1].id) R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.emotion3.setBackgroundResource(
            if (selectEmotion == filteredEmotions[2].id) R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.emotion4.setBackgroundResource(
            if (selectEmotion == filteredEmotions[3].id) R.drawable.button_select_emotion else R.drawable.button_emotion
        )
    }
}
