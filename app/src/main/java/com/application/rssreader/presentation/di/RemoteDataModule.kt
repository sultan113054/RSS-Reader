package com.application.rssreader.presentation.di

import android.content.Context
import com.application.rssreader.BuildConfig
import com.application.rssreader.core.asAndroidX
import com.application.rssreader.data.repository.dataSource.remote.RssFeedsRemoteDataSource
import com.application.rssreader.data.repository.dataSourceImpl.remote.RssFeedsRemoteDataSourceImpl
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.prof.rssparser.Parser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

var client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .build()

val idlingResource = OkHttp3IdlingResource.create("okhttp", client).asAndroidX()

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
            .okHttpClient(client)
            .build()
    }
}












