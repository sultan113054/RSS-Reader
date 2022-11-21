package com.application.rssreader.presentation.di

import com.application.rssreader.domain.repository.RSSFeedsRepository
import com.application.rssreader.domain.usecase.GetRSSFeedsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideRSSFeedsUseCase(
        rssFeedsRepository: RSSFeedsRepository,
    ): GetRSSFeedsUseCase {
        return GetRSSFeedsUseCase(rssFeedsRepository)
    }


}


















