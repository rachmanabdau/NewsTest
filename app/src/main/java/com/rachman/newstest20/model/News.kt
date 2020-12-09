package com.rachman.newstest.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class News(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
) : Parcelable {
    @JsonClass(generateAdapter = true)
    @Parcelize
    data class Article(
        @Json(name = "author")
        val author: String?,
        @Json(name = "content")
        val content: String?,
        @Json(name = "description")
        val description: String?,
        @Json(name = "publishedAt")
        val publishedAt: String,
        @Json(name = "source")
        val source: Source,
        @Json(name = "title")
        val title: String,
        @Json(name = "url")
        val url: String,
        @Json(name = "urlToImage")
        val urlToImage: String?
    ) : Parcelable {
        @JsonClass(generateAdapter = true)
        @Parcelize
        data class Source(
            @Json(name = "id")
            val id: String?,
            @Json(name = "name")
            val name: String
        ) : Parcelable
    }
}