package com.rachman.newstest20.source.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rachman.newstest.model.News
import com.rachman.newstest20.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl("https://newsapi.org/v2/")
    .build()

interface NetworkService {

    @GET("top-headlines")
    fun getTopHeadLinesAsync(
        @Query("page") page: Int,
        @Header("Authorization") apiKey: String = BuildConfig.API_KEY,
        @Query("country") country: String = "id",
        @Query("category") category: String = "health"
    ): Deferred<Response<News>>

}

object NetworkAPI {
    val retrofitService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }
}