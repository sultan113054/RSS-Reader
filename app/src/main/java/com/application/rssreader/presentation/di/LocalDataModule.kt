package com.application.rssreader.presentation.di

import com.application.rssreader.data.repository.dataSource.local.RSSFeedsLocalDataSource
import com.application.rssreader.data.repository.dataSourceImpl.local.RSSFeedsLocalDataSourceImpl
import com.sevenpeakssoftware.sultan.data.db.RSSFeedsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideRSSFeedsLocalDataSource(rssFeedsDAO: RSSFeedsDAO): RSSFeedsLocalDataSource {
        return RSSFeedsLocalDataSourceImpl(rssFeedsDAO)
    }

}













