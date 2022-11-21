package com.application.rssreader.presentation.di

import com.application.rssreader.presentation.adapter.RSSFeedsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideRSSFeedsAdapter(): RSSFeedsAdapter {
        return RSSFeedsAdapter()
    }
}