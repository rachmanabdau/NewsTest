package com.rachman.newstest20.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rachman.newstest.model.News
import com.rachman.newstest20.source.network.INewsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val source: INewsRepo,
    private val dispatchers: CoroutineDispatcher
) : ViewModel() {

    private val _newsList = MutableLiveData<PagingData<News.Article>>()
    val newsList: LiveData<PagingData<News.Article>> = _newsList

    fun getNewsList() {
        viewModelScope.launch(dispatchers) {
            Pager(
                // Configure how data is loaded by passing additional properties to
                // PagingConfig, such as prefetchDistance.
                PagingConfig(pageSize = 20, prefetchDistance = 5)
            ) {
                NewsDataSource(source)
            }.flow
                .cachedIn(this).collectLatest {
                    _newsList.value = it
                }
        }
    }
}