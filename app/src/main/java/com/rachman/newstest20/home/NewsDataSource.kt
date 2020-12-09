package com.rachman.newstest20.home

import androidx.paging.PagingSource
import com.rachman.newstest.model.News
import com.rachman.newstest.model.Result
import com.rachman.newstest20.source.network.INewsRepo

class NewsDataSource(
    private val repo: INewsRepo
) : PagingSource<Int, News.Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News.Article> {
        return try {
            val page = params.key ?: 1
            val newsResult: Result<News?> = repo.getTopHeadlines(page)


            if (newsResult is Result.Success) {
                val articleList = newsResult.data?.articles ?: emptyList()
                LoadResult.Page(data = articleList, prevKey = null, nextKey = page + 1)
            } else {
                val message = if (newsResult is Result.Error) {
                    newsResult.exception
                } else {
                    Exception("Unknown error occured.")
                }

                LoadResult.Error(message)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}