package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.chaeni__beam.ai_healing.Adapter.ViewPagerAdapter
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.databinding.ActivityLoginBinding

class FoodActivity : AppCompatActivity() {

    lateinit var binding : ActivityFoodBinding

    var currentPosition=0

    //핸들러 설정
    //ui 변경하기
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }

        val adapter= ViewPagerAdapter()
        binding.foodRankingPage.adapter=adapter

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()
    }

    //페이지 변경하기
    fun setPage(){
        if(currentPosition==5) currentPosition=0
        binding.foodRankingPage.setCurrentItem(currentPosition,true)
        currentPosition+=1
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                Thread.sleep(4000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}