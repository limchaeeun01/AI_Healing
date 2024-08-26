package com.chaeni__beam.ai_healing.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaeni__beam.ai_healing.Adapter.FoodVerticalAdapter
import com.chaeni__beam.ai_healing.Data.foodData
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.ActivityFoodRankBinding


class FoodRcmActivity : AppCompatActivity(), FoodVerticalAdapter.OnItemClickListener {

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
        foodAdapter = FoodVerticalAdapter(this, this)

        // RecyclerView에 LinearLayoutManager를 세로 방향으로 설정
        binding.foodRcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.foodRcv.adapter = foodAdapter

        foodData.apply {
            add(foodData(food_img = R.drawable.food1, food_name = "마라탕", food_info = "얼얼한 국물과 다양한 재료의 강렬한 만남", food_price = 13500))
            add(foodData(food_img = R.drawable.food2, food_name = "딸기 그릭 요거트", food_info = "상큼한 딸기와 부드러운 요거트의 기분 좋은 조화", food_price = 6500))
            add(foodData(food_img = R.drawable.food3, food_name = "매콤 닭발", food_info = "쫄깃한 닭발과 매운 소스의 스트레스 해소 콤보", food_price = 12000))
            add(foodData(food_img = R.drawable.chokocake, food_name = "밀크 초코 케이크", food_info = "부드러운 초콜릿과 달콤함의 완벽한 만남으로 엔드로핀 폭발!", food_price = 6000))

            foodAdapter.datas = foodData
            foodAdapter.notifyDataSetChanged()
        }
    }

    override fun onItemClick(item: foodData) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("음식 주문")

        // 메시지 설정
        builder.setMessage("${item.food_name}을(를) 주문하시겠습니까? \n(가격 : ${item.food_price}원)")

        // "예" 버튼 클릭 시 처리
        builder.setPositiveButton("예") { dialog, which ->
            // 주문 확인 로직을 여기에 추가할 수 있습니다.
            Toast.makeText(this, "${item.food_name}을(를) 주문했습니다.", Toast.LENGTH_SHORT).show()
        }

        // "아니오" 버튼 클릭 시 처리
        builder.setNegativeButton("아니오") { dialog, which ->
            // 아무 것도 하지 않고 대화상자 닫기
            dialog.dismiss()
        }

        // 다이얼로그 생성 및 표시
        val alertD = builder.create()
        alertD.show()
    }

}