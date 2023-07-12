package com.example.newsify.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsify.R
import com.example.newsify.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        val missionFragment = MissionFragment()

        binding.btnNext.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_layout, missionFragment)
                commit()
            }
        }

        return binding.root
    }


}