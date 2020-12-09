package com.rachman.newstest.beranda

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rachman.newstest.getOrAwaitValue
import com.rachman.newstest20.FakeSource
import com.rachman.newstest20.home.HomeViewModel
import com.rachman.newstest20.source.network.INewsRepo
import com.rachman.newstest20.source.network.NewsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    private lateinit var fakeRemoteSource: INewsRepo
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var coroutineDispatchers: CoroutineDispatcher

    @Before
    fun setupViewModel() {
        coroutineDispatchers = TestCoroutineDispatcher()
        fakeRemoteSource = NewsRepo(FakeSource())
        homeViewModel = HomeViewModel(fakeRemoteSource, coroutineDispatchers)
    }

    @Test
    fun `get headline with result success`() = runBlockingTest {
        homeViewModel.getNewsList()
        val result = homeViewModel.newsList.getOrAwaitValue()

        MatcherAssert.assertThat(result, CoreMatchers.notNullValue())
    }
}