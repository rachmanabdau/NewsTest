package com.rachman.newstest.di

import com.rachman.newstest20.source.network.INewsRepo
import com.rachman.newstest20.source.network.NewsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AccessModule {
    @Binds
    abstract fun bindAuthAccess(authenticationAccess: NewsRepo): INewsRepo
}