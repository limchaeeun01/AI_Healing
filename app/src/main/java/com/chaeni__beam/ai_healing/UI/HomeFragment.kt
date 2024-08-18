package com.chaeni__beam.ai_healing.UI

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.chaeni__beam.ai_healing.Adapter.FoodListAdapter
import com.chaeni__beam.ai_healing.Adapter.ProductData
import com.chaeni__beam.ai_healing.Adapter.ProductListAdapter
import com.chaeni__beam.ai_healing.Adapter.foodData
import com.chaeni__beam.ai_healing.EntranceActivity
import com.chaeni__beam.ai_healing.FoodActivity
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.SongActivity
import com.chaeni__beam.ai_healing.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    lateinit var foodAdapter: FoodListAdapter
    lateinit var productAdapter : ProductListAdapter

    val foodData = mutableListOf<foodData>()
    val productData = mutableListOf<ProductData>()


    var emotion: String = ""

    var name = "김수원"

    val sadnessMentList = listOf("늘 행복할 수는 없지만\n" +
            "내일은 또 괜찮은 하루가 될 수 있어요.\n", "너무 밝지 않아도 돼요.\n" +
            "환할수록 그림자도 짙은 법이니까요.\n")

    val angerMentList = listOf("화는 순간이지만 그 여파는 오래갑니다.\n" +
            "차분히 숨을 고르고 마음을 가라앉혀 보세요.\n", "당신의 마음이 지치지 않도록,\n" +
            "잠시 멈추고 깊게 호흡해 보세요.\n")

    val gentlenessMentList = listOf("현재의 순간을 즐기세요.\n" +
            "이 평온함은 당신의 내면의 힘을 보여줍니다.\n", "당신의 차분한 마음이\n" +
            "주변에 긍정적인 에너지를 전파하고 있습니다.\n")

    val happinessMentList = listOf("기쁨으로 가득 찬 하루를 보내고 계시네요.\n" +
            "그 웃음이 계속되길 바랄게요.\n", "행복한 순간을 소중히 여기세요.\n" +
            "그 기억이 앞으로도 많은 기쁨을 줄 거예요.\n")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            emotion = it.getString("emotion") ?: ""
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initFoodRecycler()

        initProductRecycler()

        setEmotion()

        //임시
        binding.button1.setOnClickListener{
            val intent = Intent(requireContext(), SongActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener{
            val intent = Intent(requireContext(), FoodActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener{
            //짜야함
        }

        binding.imageEmotion.setOnClickListener{
            val intent = Intent(requireContext(), EntranceActivity::class.java)
            intent.putExtra("emotion", emotion)
            startActivity(intent)
        }

        return binding.root
    }

    fun setEmotion(){
        when(emotion){
            "sadness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#52BBE3"))
                binding.titleEmotion.setText(" 슬픔, 우울 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#8045496E"))
                binding.imageEmotion.setImageResource(R.drawable.sadness_bg)
                binding.mentEmotion.setText(sadnessMentList[Random.nextInt(2)] + "${name}님에게 따뜻한 위로가 될 수 있는\n컨텐츠를 추천해드릴게요.")}

            "anger" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#F26154"))
                binding.titleEmotion.setText(" 화남, 스트레스 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80713737"))
                binding.imageEmotion.setImageResource(R.drawable.anger_bg)
                binding.mentEmotion.setText(angerMentList[Random.nextInt(2)] + "${name}님의 마음을 가라앉힐 수 있는\n컨텐츠를 추천해드릴게요.")}

            "gentleness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#FCD533"))
                binding.titleEmotion.setText(" 평온, 차분 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80806B36"))
                binding.imageEmotion.setImageResource(R.drawable.gentleness_bg)
                binding.mentEmotion.setText(gentlenessMentList[Random.nextInt(2)] + "${name}님의 차분한 마음을 유지하는 데\n도움이 될 만한 컨텐츠를 추천해드릴게요.")}

            "happiness" ->{
                binding.bgEmotion.setBackgroundColor(Color.parseColor("#FE999F"))
                binding.titleEmotion.setText(" 행복, 기쁨 ")
                binding.titleEmotion.setBackgroundColor(Color.parseColor("#80AF2B59"))
                binding.imageEmotion.setImageResource(R.drawable.happiness_bg)
                binding.mentEmotion.setText(happinessMentList[Random.nextInt(2)] + "${name}님이 이 행복한 순간을 더 오래 지속할 수 있도록\n맞춤 컨텐츠를 추천해드릴게요.")}

        }
    }

    fun initFoodRecycler() {
        foodAdapter = FoodListAdapter(requireContext())
        binding.foodRv.adapter = foodAdapter

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

    fun initProductRecycler() {
        productAdapter = ProductListAdapter(requireContext())
        binding.productRv.adapter = productAdapter

        productData.apply {
            add(ProductData(product_img = R.drawable.product1, product_name = "LED 무드등", product_url = "https://url.kr/49rmxq"))

            productAdapter.datas = productData
            productAdapter.notifyDataSetChanged()
        }

        productAdapter.listener = object : ProductListAdapter.OnItemClickListener {
            override fun onItemClick(item: ProductData) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.product_url)
                startActivity(intent)
            }
        }
    }

}