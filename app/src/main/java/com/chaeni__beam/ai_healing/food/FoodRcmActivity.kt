package com.chaeni__beam.ai_healing.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaeni__beam.ai_healing.Adapter.FoodVerticalAdapter
import com.chaeni__beam.ai_healing.Data.foodData
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityFoodRankBinding


class FoodRcmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodRankBinding

    lateinit var foodAdapter: FoodVerticalAdapter
    val foodData = mutableListOf<foodData>()

    private var emotion: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emotion = intent.getStringExtra("emotion").toString()

        initFoodRecycler()

    }

    fun initFoodRecycler() {
        foodAdapter = FoodVerticalAdapter(this)

        // RecyclerView에 LinearLayoutManager를 세로 방향으로 설정
        binding.foodRcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.foodRcv.adapter = foodAdapter

        foodData.apply {
            add(foodData(food_img = R.drawable.yeoneo, food_name = "연어", food_info = " ", food_price = 0))
            add(foodData(food_img = R.drawable.hodugwaja, food_name = "호두과자", food_info = " ", food_price = 0))
            add(foodData(food_img = R.drawable.origogi, food_name = "오리고기", food_info = " ", food_price = 0))
            add(foodData(food_img = R.drawable.chokocake, food_name = "초코케이크", food_info = " ", food_price = 0))
            add(foodData(food_img = R.drawable.nokcha, food_name = "녹차", food_info = " ", food_price = 0))

            foodAdapter.datas = foodData
            foodAdapter.notifyDataSetChanged()
        }
    }
}