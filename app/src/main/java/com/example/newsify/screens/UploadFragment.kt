package com.example.newsify.screens

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsify.R
import com.example.newsify.databinding.FragmentUploadBinding


class UploadFragment : Fragment() {
    private var binding: FragmentUploadBinding? = null
    val PICK_IMAGE = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUploadBinding.inflate(inflater, container, false)
        val welcomeFragment = WelcomeFragment()

        binding!!.btnGoBack.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.main_activity, welcomeFragment)
                commit()
            }
        }

        binding!!.btnSelectImage.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, PICK_IMAGE)
        }
        val view = binding!!.root
        return view
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            val imageUri = data?.data
            binding?.imgUpload?.setImageURI(imageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}