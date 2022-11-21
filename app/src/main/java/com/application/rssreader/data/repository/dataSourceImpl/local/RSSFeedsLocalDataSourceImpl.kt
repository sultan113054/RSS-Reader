package com.application.rssreader.data.repository.dataSourceImpl.local

import com.sevenpeakssoftware.sultan.data.db.RSSFeedsDAO
import com.application.rssreader.data.model.RSSFeedEntity
import com.application.rssreader.data.repository.dataSource.local.RSSFeedsLocalDataSource
import kotlinx.coroutines.flow.Flow


class RSSFeedsLocalDataSourceImpl(private val rssFeedsDAO: RSSFeedsDAO) : RSSFeedsLocalDataSource {
    override suspend fun saveRSSFeeds(rssFeeds: List<RSSFeedEntity>) {
        rssFeedsDAO.insert(rssFeeds)
    }

    override suspend fun getSavedRSSFeeds(): Flow<List<RSSFeedEntity>> {
     return rssFeedsDAO.getRSSFeeds()
    }

    override suspend fun deleteRSSFeeds() {
        rssFeedsDAO.deleteRSSFeeds()
    }
}