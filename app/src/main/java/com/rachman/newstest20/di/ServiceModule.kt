package com.rachman.newstest.di

import com.rachman.newstest20.source.network.NetworkAPI
import com.rachman.newstest20.source.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): NetworkService {
        return NetworkAPI.retrofitService
    }

}

@InstallIn(ActivityComponent::class)
@Module
object DispatchersModule {

    @Provides
    fun provideDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }

}
