package com.application.rssreader.presentation.di

import android.content.Context
import com.application.rssreader.data.repository.dataSource.remote.RssFeedsRemoteDataSource
import com.application.rssreader.data.repository.dataSourceImpl.remote.RssFeedsRemoteDataSourceImpl
import com.prof.rssparser.Parser
import com.application.rssreader.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRSSFeedsRemoteDataSource(
        parser: Parser,
    ): RssFeedsRemoteDataSource {
        return RssFeedsRemoteDataSourceImpl(parser, BuildConfig.BASE_URL)
    }

    @Singleton
    @Provides
    fun provideParser(
        context: Context,
    ): Parser {
        return Parser.Builder()
            .context(context)
            .build()
    }
}












