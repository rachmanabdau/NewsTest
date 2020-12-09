package com.rachman.newstest20.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rachman.newstest.model.News
import com.rachman.newstest20.R
import com.rachman.newstest20.databinding.NewsItemBinding

class NewsAdapter(private val actionDetail: (News.Article) -> Unit) :
    PagingDataAdapter<News.Article, ShowViewHolder>(DiffUtilCallback) {

    companion object DiffUtilCallback : DiffUtil.ItemCallback<News.Article>() {
        override fun areItemsTheSame(oldItem: News.Article, newItem: News.Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: News.Article, newItem: News.Article): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val data = getItem(position)
        holder.onBind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(view, actionDetail)
    }
}

class ShowViewHolder(
    private val binding: NewsItemBinding,
    private val actionDetail: (News.Article) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: News.Article?) {

        val context = binding.root.context

        data?.also { news ->
            binding.newsTitleItem.text = news.title
            binding.newsSourceItem.text = context.getString(R.string.source_news, news.source.name)
            binding.newsDateItem.text = news.publishedAt

            Glide.with(context).load(data.urlToImage)
                .error(R.drawable.ic_baseline_error_24)
                .into(binding.newsThumbnailItem)

            binding.parentNewsItem.setOnClickListener {
                actionDetail(news)
            }
        }
    }
}