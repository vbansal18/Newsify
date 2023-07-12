package com.example.newsify.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.newsify.R
import com.example.newsify.databinding.FragmentAboutBinding
import com.example.newsify.databinding.FragmentMissionBinding
import com.example.newsify.databinding.FragmentVisionBinding
import com.example.newsify.databinding.FragmentWelcomeBinding

class VisionFragment : Fragment() {
    lateinit var binding : FragmentVisionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVisionBinding.inflate(inflater, container, false)
        val welcomeFragment = WelcomeFragment()

        binding.btnReady.setOnClickListener{

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_activity, welcomeFragment)
                commit()
            }
        }
        return binding.root
    }


}