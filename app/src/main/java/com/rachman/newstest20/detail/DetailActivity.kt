package com.rachman.newstest20.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rachman.newstest.model.News
import com.rachman.newstest20.R
import com.rachman.newstest20.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        setupToolbar()
        val newsIntent = intent.getParcelableExtra<News.Article>(NEWS_DETAIL_ID)

        newsIntent?.apply {
            binding.newsTitleDetail.text = title
            binding.newsContentDetail.text = content
            binding.newsSourceItem.text = getString(R.string.source_news, source.name)
            binding.newsDateDetail.text = publishedAt

            Glide.with(this@DetailActivity).load(urlToImage)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.newsThumbnailDetail)
        }
    }

    companion object {
        const val NEWS_DETAIL_ID = "com.rachman.newstest.NEWS_DETAIL_ID"
    }

    fun setupToolbar() {
        // my_child_toolbar is defined in the layout file
        setSupportActionBar(binding.detailToolbar)

        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}