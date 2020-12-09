package com.rachman.newstest.source.network

import com.rachman.newstest.model.News
import com.rachman.newstest.model.ResponseError
import com.rachman.newstest.model.Result
import com.rachman.newstest20.FakeSource
import com.rachman.newstest20.source.network.INewsRepo
import com.rachman.newstest20.source.network.NetworkService
import com.rachman.newstest20.source.network.NewsRepo
import com.rachman.newstest20.source.network.moshi
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class NewsRepoTest {

    private lateinit var fakeRemoteSource: NetworkService
    private lateinit var errorConverter: JsonAdapter<ResponseError>
    private lateinit var access: INewsRepo

    @Before
    fun setupViewModel() {
        fakeRemoteSource = FakeSource()
        access = NewsRepo(fakeRemoteSource)
        errorConverter = moshi.adapter(ResponseError::class.java)
    }

    @Test
    fun `get top headlines with valid api key result success`() = runBlockingTest {
        when (val result = access.getTopHeadlines(page = 1)) {
            is Result.Success -> {
                MatcherAssert.assertThat(
                    result.data,
                    CoreMatchers.`is`(CoreMatchers.notNullValue(News::class.java))
                )
            }

            is Result.Loading -> { /*Do nothing just wait for the result*/
            }

            is Result.Error -> {
                Assert.fail(result.exception.localizedMessage)
            }
        }
    }

    @Test
    fun `get top headlines with invalid api key result failed`() = runBlockingTest {
        when (val result = access.getTopHeadlines(page = 1, apiKey = "invalidaapikey")) {
            is Result.Success -> {
                Assert.fail("result expected to be failed but success")
            }

            is Result.Loading -> { /*Do nothing just wait for the result*/
            }

            is Result.Error -> {
                MatcherAssert.assertThat(
                    result.exception.localizedMessage,
                    CoreMatchers.`is`(CoreMatchers.containsString("Unauthorized"))
                )
            }
        }
    }

}