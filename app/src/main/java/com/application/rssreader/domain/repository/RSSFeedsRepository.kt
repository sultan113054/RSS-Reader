package com.application.rssreader.domain.repository

import com.application.rssreader.DataState
import com.application.rssreader.data.model.RSSFeedModel
import kotlinx.coroutines.flow.Flow


interface RSSFeedsRepository {
    suspend fun getRSSFeedsList(): Flow<DataState<List<RSSFeedModel>>>
}