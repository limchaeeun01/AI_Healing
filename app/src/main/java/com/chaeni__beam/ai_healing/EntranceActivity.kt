package com.chaeni__beam.ai_healing

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chaeni__beam.ai_healing.databinding.ActivityEntranceBinding

class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    private var selectEmotion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 버튼 클릭 리스너 설정
        val clickListener = { buttonId: Int ->
            when (buttonId) {
                binding.happiness.id -> selectEmotion = "happiness"
                binding.sadness.id -> selectEmotion = "sadness"
                binding.apathy.id -> selectEmotion = "apathy"
                binding.anger.id -> selectEmotion = "anger"
                binding.gentleness.id -> selectEmotion = "gentleness"
            }
            updateButtonBackgrounds()
            btnActivation()
        }

        // 버튼에 클릭 리스너 할당
        binding.happiness.setOnClickListener { clickListener(binding.happiness.id) }
        binding.sadness.setOnClickListener { clickListener(binding.sadness.id) }
        binding.apathy.setOnClickListener { clickListener(binding.apathy.id) }
        binding.anger.setOnClickListener { clickListener(binding.anger.id) }
        binding.gentleness.setOnClickListener { clickListener(binding.gentleness.id) }

        binding.nextBtn.setOnClickListener{
            if(selectEmotion != ""){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("emotion", selectEmotion)
                startActivity(intent)
            }
        }

    }

    fun btnActivation(){
        if(selectEmotion != ""){
            binding.nextBtn.setBackgroundResource(R.drawable.button_rounded_corner_rectangle)
        }else{
            binding.nextBtn.setBackgroundResource(R.drawable.grey_button_rounded_corner_rectangle)
        }
    }

    fun updateButtonBackgrounds() {
        // 버튼 배경 업데이트
        binding.happiness.setBackgroundResource(
            if (selectEmotion == "happiness") R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.sadness.setBackgroundResource(
            if (selectEmotion == "sadness") R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.apathy.setBackgroundResource(
            if (selectEmotion == "apathy") R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.anger.setBackgroundResource(
            if (selectEmotion == "anger") R.drawable.button_select_emotion else R.drawable.button_emotion
        )
        binding.gentleness.setBackgroundResource(
            if (selectEmotion == "gentleness") R.drawable.button_select_emotion else R.drawable.button_emotion
        )
    }
}
