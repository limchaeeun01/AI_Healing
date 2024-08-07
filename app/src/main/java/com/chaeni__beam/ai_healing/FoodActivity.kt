package com.chaeni__beam.ai_healing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chaeni__beam.ai_healing.Adapter.ViewPagerAdapter
import com.chaeni__beam.ai_healing.databinding.ActivityFoodBinding
import com.chaeni__beam.ai_healing.databinding.ActivityLoginBinding
import com.chaeni__beam.ai_healing.fragment.FoodBoardFragment

class FoodActivity : AppCompatActivity() {

    lateinit var binding : ActivityFoodBinding

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val numPages = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener{
            finish()
        }

        binding.foodBoard.adapter = ScreenSlidePagerAdapter(this)

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val currentItem = binding.foodBoard.currentItem
                val nextItem = (currentItem + 1) % numPages
                binding.foodBoard.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 3000)
            }
        }

        // Start the automatic sliding
        handler.postDelayed(runnable, 3000)

    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable) // Remove callbacks to prevent memory leaks
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = numPages

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> FoodBoardFragment(R.drawable.food_menu_rcm)
                1 -> FoodBoardFragment(R.drawable.food_pizza_ad)
                2 -> FoodBoardFragment(R.drawable.food_ade_ad)
                else -> FoodBoardFragment(R.drawable.food_ice_ad)
            }
        }
    }

}