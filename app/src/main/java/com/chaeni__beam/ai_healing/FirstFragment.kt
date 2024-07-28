package com.chaeni__beam.ai_healing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaeni__beam.ai_healing.databinding.FragmentFirstBinding


class FirstFragment(val image : Int) : Fragment() {

    lateinit var binding:FragmentFirstBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.fragmentImage.setImageResource(image)

        return binding.root
    }


}