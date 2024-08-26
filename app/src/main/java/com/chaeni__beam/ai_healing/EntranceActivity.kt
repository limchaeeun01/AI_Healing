package com.chaeni__beam.ai_healing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chaeni__beam.ai_healing.Data.Emotion
import com.chaeni__beam.ai_healing.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    private var currentEmotion: String = ""
    private var selectEmotion: String = ""

    val emotions = listOf(
        Emotion("sadness", R.drawable.sadness, "슬퍼요"),
        Emotion("happiness", R.drawable.happiness, "행복해요"),
        Emotion("anger", R.drawable.anger, "화나요"),
        Emotion("gentleness", R.drawable.gentleness, "평온해요")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentEmotion = intent.getStringExtra("emotion") ?: ""

        if (currentEmotion.isEmpty()) {
            setInitialEmotion()
        } else {
            setCurrentEmotion()
            setSelectEmotion()
        }

        val clickListener = { emotion: Emotion ->
            selectEmotion = emotion.id
            updateButtonBackgrounds()
            btnActivation()
        }

        val filteredEmotions = emotions.filter { it.id != currentEmotion }
        binding.emotion1.setOnClickListener { clickListener(filteredEmotions[0]) }
        binding.emotion2.setOnClickListener { clickListener(filteredEmotions[1]) }
        binding.emotion3.setOnClickListener { clickListener(filteredEmotions[2]) }

        binding.nextBtn.setOnClickListener {
            if (selectEmotion.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("emotion", selectEmotion)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setInitialEmotion() {
        binding.currentTextView.visibility = View.GONE
        binding.currentEmotionState.visibility = View.GONE

        binding.InitialtextView.visibility = View.VISIBLE
        binding.initialEmotionState.visibility = View.VISIBLE

        val initialEmotionButtons = listOf(
            Pair(binding.initSadness, "sadness"),
            Pair(binding.initAnger, "anger"),
            Pair(binding.initHappiness, "happiness"),
            Pair(binding.initGentleness, "gentleness")
        )

        initialEmotionButtons.forEach { (button, emotionId) ->
            button.setOnClickListener {
                selectEmotion = emotionId
                btnActivation()
                updateButtonBackgrounds()
            }
        }
    }

    private fun setCurrentEmotion() {
        binding.currentTextView.visibility = View.VISIBLE
        binding.currentEmotionState.visibility = View.VISIBLE

        binding.InitialtextView.visibility = View.GONE
        binding.initialEmotionState.visibility = View.GONE

        val currentEmotionClass = emotions.find { it.id == currentEmotion }
        if (currentEmotionClass != null) {
            binding.currentImage.setImageResource(currentEmotionClass.imageResId)
            binding.currentText.text = currentEmotionClass.text
        }
    }

    private fun setSelectEmotion() {
        val emotionImageList = listOf(
            binding.emotion1Image,
            binding.emotion2Image,
            binding.emotion3Image
        )
        val emotionTextList = listOf(
            binding.emotion1Text,
            binding.emotion2Text,
            binding.emotion3Text
        )
        val emotionLayouts = listOf(
            binding.emotion1,
            binding.emotion2,
            binding.emotion3
        )

        val filteredEmotions = emotions.filter { it.id != currentEmotion }

        for (i in filteredEmotions.indices) {
            emotionImageList[i].setImageResource(filteredEmotions[i].imageResId)
            emotionTextList[i].text = filteredEmotions[i].text
        }
    }

    private fun btnActivation() {
        binding.nextBtn.setBackgroundResource(
            if (selectEmotion.isNotEmpty()) R.drawable.button_rounded_corner_rectangle
            else R.drawable.grey_button_rounded_corner_rectangle
        )
    }

    private fun updateButtonBackgrounds() {
        val filteredEmotions = emotions.filter { it.id != currentEmotion }

        filteredEmotions.forEachIndexed { index, emotion ->
            val backgroundRes = if (selectEmotion == emotion.id) R.drawable.button_select_emotion else R.drawable.button_emotion
            when (index) {
                0 -> binding.emotion1.setBackgroundResource(backgroundRes)
                1 -> binding.emotion2.setBackgroundResource(backgroundRes)
                2 -> binding.emotion3.setBackgroundResource(backgroundRes)
            }
        }

        val initialEmotionButtons = listOf(
            Pair(binding.initSadness, "sadness"),
            Pair(binding.initAnger, "anger"),
            Pair(binding.initHappiness, "happiness"),
            Pair(binding.initGentleness, "gentleness")
        )

        initialEmotionButtons.forEach { (button, emotionId) ->
            val backgroundRes = if (selectEmotion == emotionId) R.drawable.button_select_emotion else R.drawable.button_emotion
            button.setBackgroundResource(backgroundRes)
        }
    }
}
