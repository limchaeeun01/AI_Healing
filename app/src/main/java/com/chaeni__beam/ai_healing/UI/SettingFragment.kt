package com.chaeni__beam.ai_healing.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chaeni__beam.ai_healing.R
import com.chaeni__beam.ai_healing.databinding.FragmentHomeBinding
import com.chaeni__beam.ai_healing.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    lateinit var binding: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.changePasswordBtn.setOnClickListener{
            binding.changePasswordLayout.visibility = View.VISIBLE
        }

        return binding.root
    }

}