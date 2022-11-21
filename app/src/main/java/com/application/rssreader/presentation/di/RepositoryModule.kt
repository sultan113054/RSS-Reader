package com.application.rssreader.presentation.di

import com.application.rssreader.core.platform.NetworkHandler
import com.application.rssreader.data.repository.RSSFeedsRepositoryImpl
import com.application.rssreader.data.repository.dataSource.local.RSSFeedsLocalDataSource
import com.application.rssreader.data.repository.dataSource.remote.RssFeedsRemoteDataSource
import com.application.rssreader.domain.repository.RSSFeedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRSSFeedsRepository(
        rssFeedsRemoteDataSource: RssFeedsRemoteDataSource,
        rssFeedsLocalDataSource: RSSFeedsLocalDataSource,
        networkHandler: NetworkHandler,
    ): RSSFeedsRepository {
        return RSSFeedsRepositoryImpl(
            rssFeedsRemoteDataSource,
            rssFeedsLocalDataSource,
            networkHandler
        )
    }

}














