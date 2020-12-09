package com.rachman.newstest20.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rachman.newstest20.databinding.RetryItemBinding

class ErrorAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ErrorViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ErrorViewHolder {
        val viewbinding =
            RetryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ErrorViewHolder(viewbinding, retry)
    }

    override fun onBindViewHolder(
        holder: ErrorViewHolder,
        loadState: LoadState
    ) = holder.onBind(loadState)
}

class ErrorViewHolder(
    private val binding: RetryItemBinding, private val retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {


    fun onBind(state: LoadState) {
        if (state is LoadState.Error) {
            binding.errorMessage.text = state.error.localizedMessage
        }

        binding.errorMessage.isVisible = state is LoadState.Error
        binding.tryAgainButton.isVisible = state is LoadState.Error
        binding.loadProgress.isVisible = state is LoadState.Loading

        binding.tryAgainButton.setOnClickListener {
            retry()
        }
    }
}