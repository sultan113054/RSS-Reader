package com.application.rssreader.presentation.di

import android.content.Context
import com.application.rssreader.data.db.AppDatabase
import com.sevenpeakssoftware.sultan.data.db.RSSFeedsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideRSSFeedsDao(appDatabase: AppDatabase): RSSFeedsDAO {
        return appDatabase.getRSSFeedsDAO()
    }


}