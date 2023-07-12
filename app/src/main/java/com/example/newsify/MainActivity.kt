package com.example.newsify

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newsify.databinding.ActivityMainBinding
import com.example.newsify.local_db.PreferencesDatastore
import com.example.newsify.screens.AboutFragment
import com.example.newsify.screens.WelcomeFragment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var preferencesDatastore: PreferencesDatastore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aboutFragment = AboutFragment()
        val welcomeFragment = WelcomeFragment()

        preferencesDatastore = PreferencesDatastore(this)

        lifecycleScope.launch {
            preferencesDatastore.timesFlow.take(1).collect {

                Log.d("DATASTORE", "$it")
                if (it == true) {

                    preferencesDatastore.storeData(false)

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, aboutFragment)
                        commit()
                    }
                } else {

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, welcomeFragment)
                        commit()
                    }
                }
            }
        }

        binding.btnSkipIntro.setOnClickListener {

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.main_activity, welcomeFragment)
                commit()
            }
        }

    }

    fun emptyBackStack() {
        binding.frameLayout.removeAllViews()
        binding.btnSkipIntro.visibility = View.GONE
    }
}
