package com.rachman.newstest20.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rachman.newstest20.R
import com.rachman.newstest20.databinding.BannerItemBinding

class BannerAdapter : RecyclerView.Adapter<BannerViewholder>() {

    val bannerList = mutableListOf<Int>()

    init {
        repeat(6) {
            bannerList.add(R.drawable.banner_placeholder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewholder {
        val viewBinding =
            BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewholder(viewBinding)
    }

    override fun onBindViewHolder(holder: BannerViewholder, position: Int) {
        holder.bind(bannerList[position])
    }

    override fun getItemCount(): Int {
        return bannerList.size
    }
}

class BannerViewholder(private val binding: BannerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(@DrawableRes item: Int) {
        val context = binding.root.context
        binding.bannerThumbnail.setImageDrawable(ContextCompat.getDrawable(context, item))
    }
}