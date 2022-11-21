package com.application.rssreader.data.repository

import com.application.rssreader.DataState
import com.application.rssreader.core.exception.Failure
import com.application.rssreader.core.platform.NetworkHandler
import com.application.rssreader.data.model.RSSFeedModel
import com.application.rssreader.data.repository.dataSource.local.RSSFeedsLocalDataSource
import com.application.rssreader.data.repository.dataSource.remote.RssFeedsRemoteDataSource
import com.application.rssreader.data.state.DataErrorResponse
import com.application.rssreader.data.state.DataSuccessResponse
import com.application.rssreader.data.util.asEntity
import com.application.rssreader.data.util.asModel
import com.application.rssreader.domain.repository.RSSFeedsRepository
import com.application.rssreader.presentation.util.RSSFeedsFailure
import com.prof.rssparser.Channel
import kotlinx.coroutines.flow.*

class RSSFeedsRepositoryImpl(
    private val rssFeedsRemoteDataSource: RssFeedsRemoteDataSource,
    private val rssFeedsLocalDataSource: RSSFeedsLocalDataSource,
    private val networkHandler: NetworkHandler,
) : RSSFeedsRepository {
    override suspend fun getRSSFeedsList(): Flow<DataState<List<RSSFeedModel>>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> responseToResource(rssFeedsRemoteDataSource.getRssFeeds())
            false -> {
                getSavedData(DataState.Error(DataErrorResponse(reason = Failure.NetworkConnection)))
            }
        }
    }

    private suspend fun responseToResource(response: DataState<Channel>): Flow<DataState<List<RSSFeedModel>>> {

        return when (response) {
            is DataState.Success -> {
                var data = response.dataResponse.data?.articles?.map {
                    it.asEntity()
                }
                data?.let {
                    rssFeedsLocalDataSource.deleteRSSFeeds()
                    rssFeedsLocalDataSource.saveRSSFeeds(data)
                    getSavedData(DataState.Error(DataErrorResponse()))
                } ?: emitError(DataState.Error(DataErrorResponse(
                    reason = RSSFeedsFailure.NoDataAvailable())))

            }
            is DataState.Error -> {
                getSavedData(DataState.Error(DataErrorResponse(statusCode = response.dataResponse.statusCode,
                    reason = response.dataResponse.reason,
                    errorMessage = response.dataResponse.errorMessage)))
            }
            else -> getSavedData(DataState.Error(DataErrorResponse()))
        }
    }

    private fun emitError(dataState: DataState<List<RSSFeedModel>>): Flow<DataState<List<RSSFeedModel>>> =
        flow {
            emit(dataState)
        }

    private suspend fun getSavedData(dataState: DataState<List<RSSFeedModel>>): Flow<DataState<List<RSSFeedModel>>> =
        flow {
            rssFeedsLocalDataSource.getSavedRSSFeeds().map {
                if (it.isNotEmpty()) {
                    emit(DataState.Success(DataSuccessResponse(it.map { it.asModel()})))
                } else {
                    emit(dataState)
                }
            }.catch { emit(DataState.Error(DataErrorResponse(reason = Failure.DBError))) }.collect()

        }


}