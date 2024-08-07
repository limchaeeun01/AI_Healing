package com.chaeni__beam.ai_healing.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.chaeni__beam.ai_healing.JoinActivity
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.UI.FoodRankActivity


class FoodBoardFragment(val image : Int) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find ImageView and set the image resource
        val imageView: ImageView = view.findViewById(R.id.food_board_img)
        imageView.setImageResource(image)

        imageView.setOnClickListener {
            when(image){
                R.drawable.food_menu_rcm ->{
                    val intent = Intent(requireContext(), FoodRankActivity::class.java)
                    startActivity(intent)
                }

            }
        }
    }


}