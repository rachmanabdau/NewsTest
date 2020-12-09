package com.rachman.newstest20.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.rachman.newstest20.R
import com.rachman.newstest20.adapters.BannerAdapter
import com.rachman.newstest20.adapters.ErrorAdapter
import com.rachman.newstest20.databinding.ActivityMainBinding
import com.rachman.newstest20.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val homeViewModel by viewModels<HomeViewModel>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupBanner()

        val newsAdapter = NewsAdapter {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.NEWS_DETAIL_ID, it)
            startActivity(intent)
        }.apply {
            lifecycleScope.launch {
                loadStateFlow.collectLatest { loadState ->
                    showError(loadState.refresh) { retry() }
                }
            }
        }
        binding.newsRvHome.adapter = newsAdapter.withLoadStateHeaderAndFooter(
            header = ErrorAdapter(newsAdapter::retry), footer = ErrorAdapter(newsAdapter::retry)
        )

        homeViewModel.getNewsList()
        homeViewModel.newsList.observe(this) {
            lifecycleScope.launch {
                newsAdapter.submitData(it)
            }
        }
    }

    private fun showError(loadState: LoadState, retry: () -> Unit) {
        // show error message and try agian button when in error state
        if (loadState is LoadState.Error) {
            binding.errorPlaceholder.errorMessage.text =
                (loadState).error.localizedMessage
        }

        binding.errorPlaceholder.root.isVisible = loadState is LoadState.Error
        binding.errorPlaceholder.tryAgainButton.setOnClickListener {
            retry()
        }
    }

    private fun setupBanner() {
        binding.bannerRv.adapter = BannerAdapter()
        binding.dotsIndicator.setViewPager2(binding.bannerRv)
    }
}