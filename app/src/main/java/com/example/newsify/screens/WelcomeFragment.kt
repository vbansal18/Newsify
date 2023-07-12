package com.example.newsify.screens

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsify.MainActivity
import com.example.newsify.R
import com.example.newsify.databinding.ActivityMainBinding
import com.example.newsify.databinding.FragmentVisionBinding
import com.example.newsify.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    lateinit var binding : FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val uploadFragment = UploadFragment()
        val feedFragment = FeedFragment()

        binding.btnUploadImage.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_activity, uploadFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.btnFeeds.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_activity, feedFragment)
                addToBackStack(null)
                commit()
            }
        }


        return binding.root
    }


}