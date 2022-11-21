package com.application.rssreader.presentation.di

import com.application.rssreader.domain.usecase.GetRSSFeedsUseCase
import com.application.rssreader.presentation.viewmodel.RSSFeedsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideRSSFeedsViewModelFactory(
        getRSSFeedsUseCase: GetRSSFeedsUseCase,
    ): RSSFeedsViewModelFactory {
        return RSSFeedsViewModelFactory(
            getRSSFeedsUseCase
        )
    }

}








