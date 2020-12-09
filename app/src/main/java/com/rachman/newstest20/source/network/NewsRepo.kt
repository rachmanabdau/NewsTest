package com.rachman.newstest20.source.network

import com.rachman.newstest.model.News
import com.rachman.newstest.model.ResponseError
import com.rachman.newstest.model.Result
import com.rachman.newstest20.BuildConfig
import retrofit2.Response
import javax.inject.Inject

interface INewsRepo {
    suspend fun getTopHeadlines(
        page: Int,
        apiKey: String = BuildConfig.API_KEY,
        country: String = "id",
        category: String = "health"
    ): Result<News?>
}

class NewsRepo @Inject constructor(private val networkService: NetworkService) : INewsRepo {

    override suspend fun getTopHeadlines(
        page: Int,
        apiKey: String,
        country: String,
        category: String
    ): Result<News?> {
        return try {
            val result =
                networkService.getTopHeadLinesAsync(page, apiKey, country, category).await()

            if (result.isSuccessful) {
                Result.Success(result.body())
            } else {
                returnError(result)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

fun returnError(result: Response<*>): Result.Error {
    val errorAdapter = moshi.adapter(ResponseError::class.java)
    val errorMessage = result.errorBody()?.string() ?: "Unknown error has occured"
    val errorJson =
        errorAdapter.fromJson(errorMessage)

    return Result.Error(Exception(errorJson?.message))
}