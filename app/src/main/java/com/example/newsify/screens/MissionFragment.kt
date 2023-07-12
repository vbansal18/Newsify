package com.example.newsify.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsify.R
import com.example.newsify.databinding.FragmentAboutBinding
import com.example.newsify.databinding.FragmentMissionBinding
import com.example.newsify.databinding.FragmentWelcomeBinding

class MissionFragment : Fragment() {
    lateinit var binding : FragmentMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMissionBinding.inflate(inflater, container, false)
        val visionFragment = VisionFragment()

        binding.btnNext.setOnClickListener{

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout, visionFragment)
                commit()
            }
        }

        return binding.root
    }


}