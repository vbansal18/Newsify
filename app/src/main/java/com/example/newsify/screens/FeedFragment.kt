package com.example.newsify.screens

import RvAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsify.Network.NewsService
import com.example.newsify.databinding.FragmentFeedBinding
import com.example.newsify.model.api.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date


class FeedFragment : Fragment() {

    lateinit var binding: FragmentFeedBinding
    lateinit var adapter: RvAdapter
    lateinit var newsResponse: NewsResponse
    var page: Int = 1
    var isLatestNews = 1
    var to: String = "2023-07-11"

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)

        getNews(page++)

        binding.chipLatest.setOnClickListener {
            isLatestNews = 1
            getNews(page++)
        }

        binding.chipOldest.setOnClickListener {
            isLatestNews = 0
            getNews(page++)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            getNews(page++)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        return binding.root
    }


    private fun getNews(page: Int) {
        if(page>20) this.page =1
        if (isLatestNews == 0) {
            to = "2023-06-18"
        } else if (isLatestNews == 1) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            to = sdf.format(Date())
        }
        Log.d("RETROFIT", "Got news from api, Page : $page , date : $to")
        binding.progressBar.visibility = View.VISIBLE
        val news = NewsService.newsInstance.getNewsItem(page, to)
        news.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                newsResponse = response.body()!!
                binding.progressBar.visibility = View.GONE
                adapter = RvAdapter(context, newsResponse.articles)
                binding.rvNews.adapter = adapter
                binding.rvNews.layoutManager = LinearLayoutManager(context)
                Log.d("RETROFIT", "Got news from api, Page : $page , date : $to")
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("RETROFIT", "Error in fetching news.")
            }
        })

    }
}