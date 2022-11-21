package com.application.rssreader.domain.usecase

import com.application.rssreader.DataState
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.domain.repository.RSSFeedsRepository
import kotlinx.coroutines.flow.Flow

class GetRSSFeedsUseCase(private val rssFeedsRepository: RSSFeedsRepository) {

    suspend fun getRSSFeedsList(): Flow<DataState<List<RSSFeedModel>>> {
        return rssFeedsRepository.getRSSFeedsList()
    }

}