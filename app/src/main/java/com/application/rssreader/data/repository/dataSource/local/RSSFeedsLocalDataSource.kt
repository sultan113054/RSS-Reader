package com.application.rssreader.data.repository.dataSource.local

import com.application.rssreader.data.model.RSSFeedEntity
import kotlinx.coroutines.flow.Flow

interface RSSFeedsLocalDataSource {
    suspend fun saveRSSFeeds(rssFeeds: List<RSSFeedEntity>)
    suspend fun getSavedRSSFeeds(): Flow<List<RSSFeedEntity>>
    suspend fun deleteRSSFeeds()
}