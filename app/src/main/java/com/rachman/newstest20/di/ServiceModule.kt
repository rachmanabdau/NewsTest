package com.rachman.newstest.di

import com.rachman.newstest20.source.network.NetworkAPI
import com.rachman.newstest20.source.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
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
