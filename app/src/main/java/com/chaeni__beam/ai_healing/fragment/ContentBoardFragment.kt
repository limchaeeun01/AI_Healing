package com.chaeni__beam.ai_healing.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chaeni__beam.ai_healing.R


class ContentBoardFragment(val image : Int) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: ImageView = view.findViewById(R.id.content_board_img)
        imageView.setImageResource(image)

        imageView.setOnClickListener {
            when(image){
//                R.drawable.food_menu_rcm ->{
//                    val intent = Intent(requireContext(), FoodRankActivity::class.java)
//                    startActivity(intent)
//                }

            }
        }
    }


}